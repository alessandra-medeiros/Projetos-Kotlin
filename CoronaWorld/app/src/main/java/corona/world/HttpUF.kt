package corona.world

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object HttpUF{
    val url ="https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/"

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info =  cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadEstado(uf:String): ArrayList<Estados> {
        val estado = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(url+uf)
            .build()

        val response = estado.newCall(request).execute()
        val jsonString = response.body?.string()

        val jsonObject = JSONObject(jsonString)

        return lerEstado(jsonObject)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun lerEstado(json: JSONObject) : ArrayList<Estados> {
        val estados = arrayListOf<Estados>()
        try {
                val dia = formatarData(json.getString("datetime").substring(0,10))
                val hora = json.getString("datetime").substring(11,16)

                var states = Estados(
                    json.getString("state"),
                    json.getInt("cases"),
                    json.getInt("deaths"),
                    json.getInt("suspects"),
                    json.getInt("discards"),
                    dia,
                    hora
                )
                estados.add(states)
        }catch (e: IOException) {
            Log.e("Erro", "Não foi possível ler.")
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