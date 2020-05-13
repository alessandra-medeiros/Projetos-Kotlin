package corona.world

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import corona.world.Inicio
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_inicio.*

object HttpUF{
    val url ="https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/"

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info =  cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadState(uf:String): ArrayList<Estados> {
        val state = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(url+"rs")
            .build()

        val response = state.newCall(request).execute()
        val jsonString = response.body?.string()

        val jsonObject = JSONObject(jsonString)

        return readState(jsonObject)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readState(json: JSONObject) : ArrayList<Estados> {
        val estados = arrayListOf<Estados>()
        try {
                val dia = formatarData(json.getString("datetime").substring(0,10))
                val hora = json.getString("datetime").substring(11,16)

                var states = Estados(
                    json.getString("state"),
                    json.getInt("cases"),
                    json.getInt("deaths"),
                    json.getInt("suspects"),
                    json.getInt("refuses"),
                    dia,
                    hora
                )
                estados.add(states)
        }catch (e: IOException) {
            Log.e("Erro", "Impossivel ler JSON")
        }
        return estados
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatarData(data: String): String {
        val diaString = data
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(diaString)
        var formattedDate = date.format(formatter)
        return formattedDate
    }
}