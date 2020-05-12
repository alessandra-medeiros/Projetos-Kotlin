package corona.world

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {
    private var listaEstados = arrayListOf<Estados>()
    private var asyncTask : StatesTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnTodosOsEstados.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoadEstados::class.java)
            this.startActivity(intent)
        })

        btnTodosOsPaises.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoadPaises::class.java)
            this.startActivity(intent)
        })

        btnProcurar.setOnClickListener(View.OnClickListener {
            carregaDados()
            val intent = Intent(this, LoadPaises::class.java)
            this.startActivity(intent)
        })
    }

    fun carregaDados(){
        listaEstados.clear()
        if(asyncTask==null){
            if(HttpUF.hasConnection(this)){
                if(asyncTask?.status != AsyncTask.Status.RUNNING){
                    asyncTask = StatesTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, List<Estados?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        @SuppressLint("WrongThread")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Estados>? {
            return HttpUF.loadState(selectUF.text.toString().toLowerCase())
        }

        private fun update(result: List<Estados>?){
            if(result != null) {
                listaEstados.clear()
                listaEstados.addAll(result.reversed())
            }
            asyncTask = null
        }

        override fun onPostExecute(result: List<Estados?>?) {
            super.onPostExecute(result)
            update(result as List<Estados>?)
        }
    }
}