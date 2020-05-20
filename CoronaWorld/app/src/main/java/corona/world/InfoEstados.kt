package corona.world

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_info_estados.*
import kotlinx.android.synthetic.main.activity_info_estados.estadoHora
import kotlinx.android.synthetic.main.activity_info_estados.casos
import kotlinx.android.synthetic.main.activity_info_estados.mortes
import kotlinx.android.synthetic.main.activity_load_estados.*

class InfoEstados : AppCompatActivity() {
    private var asyncTask : InfoEstados.StatesTask? = null
    private var listaEstados = arrayListOf<Estados>()
    var uf="rs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_estados)

        try {
            val arrayEstados = this.intent.getParcelableExtra<Estados>("Estado")

            if (arrayEstados != null){
                estadoDia.text = arrayEstados.date.toString().substring(0,2)
                estadoMes.text = selectMes(arrayEstados.date.toString())
                estadoHora.text = arrayEstados.hour
                estadoNome.text = arrayEstados.state
                casos.text = arrayEstados.cases.toString()
                mortes.text = arrayEstados.deaths.toString()
                suspeitos.text = arrayEstados.suspects.toString()
                descartados.text = arrayEstados.discards.toString()
            }else{
                uf = intent.getStringExtra("UF")
                carregaDados()
            }
        }catch (e: Exception){
            e.message
        }
    }

    fun carregaDados(){
        listaEstados.clear()
        if(listaEstados.isNotEmpty()){
            Log.e("task","Tarefa rodando.")
        }else{
            if(asyncTask==null){
                if(HttpEstados.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = StatesTask()
                        asyncTask?.execute()
                    }
                }else{
                    progressBar.visibility = View.GONE
                }
            }else if(asyncTask?.status==AsyncTask.Status.RUNNING){
                Log.e("task","Tarefa rodando.")
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, List<Estados?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            Log.e("task","Buscando tarefa.")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Estados>? {
            return HttpUF.loadEstado(uf)
        }

        private fun update(result: List<Estados>?){
            if(result != null){
                val arrayEstados = result.get(0)
                estadoDia.text = arrayEstados.date.toString().substring(0,2)
                estadoMes.text = selectMes(arrayEstados.date.toString())
                estadoHora.text = arrayEstados.hour
                estadoNome.text = arrayEstados.state
                casos.text = arrayEstados.cases.toString()
                mortes.text = arrayEstados.deaths.toString()
                suspeitos.text = arrayEstados.suspects.toString()
                descartados.text = arrayEstados.discards.toString()
            }else{
                txtMsg.text = "Erro no carregamento"
            }
            asyncTask = null
        }

        override fun onPostExecute(result: List<Estados?>?) {
            super.onPostExecute(result)
            Log.e("task","Tarefa executada.")
            update(result as List<Estados>?)
        }
    }

    fun selectMes(mes: String): String? {
        var newString: String? = ""
        when {
            mes.substring(3,5) == "01" -> {
                newString = "Janeiro"
            }
            mes.substring(3,5) == "02" -> {
                newString = "Fevereiro"
            }
            mes.substring(3,5) == "03" -> {
                newString = "Março"
            }
            mes.substring(3,5) == "04" -> {
                newString = "Abril"
            }
            mes.substring(3,5) == "05" -> {
                newString = "Maio"
            }
            mes.substring(3,5) == "06" -> {
                newString = "Junho"
            }
            mes.substring(3,5) == "07" -> {
                newString = "Julho"
            }
            mes.substring(3,5) == "08" -> {
                newString = "Agosto"
            }
            mes.substring(3,5) == "09" -> {
                newString = "Setembro"
            }
            mes.substring(3,5) == "10" -> {
                newString = "Outubro"
            }
            mes.substring(3,5) == "11" -> {
                newString = "Novembro"
            }
            mes.substring(3,5) == "12" -> {
                newString = "Dezembro"
            }
        }
        return newString
    }
}