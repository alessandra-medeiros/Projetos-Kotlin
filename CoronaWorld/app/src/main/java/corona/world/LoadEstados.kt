package corona.world

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_load_estados.*

class LoadEstados : AppCompatActivity() {
    private var listaEstados = arrayListOf<Estados>()
    private var adapter = AdapterEstados(listaEstados)
    private var asyncTask : taskEstados? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_estados)
        carregaDados()

        rvDados.layoutManager = LinearLayoutManager(this)
        rvDados.itemAnimator = DefaultItemAnimator()
        rvDados.adapter = adapter
    }

    fun showProgress(show: Boolean){
        if(show){
            txtMsg.text = "Carregando..."
        }else{
            txtMsg.visibility = if(show) View.VISIBLE else View.GONE
            progressBar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun carregaDados(){
        listaEstados.clear()
        if(listaEstados.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HttpEstados.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = taskEstados()
                        asyncTask?.execute()
                    }
                }else{
                    progressBar.visibility = View.GONE
                }
            }else if(asyncTask?.status==AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class taskEstados: AsyncTask<Void, Void, List<Estados?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Estados>? {
            return HttpEstados.loadStates()
        }

        private fun update(result: List<Estados>?){
            if(result != null){
                listaEstados.clear()
                listaEstados.addAll(result.reversed())
            }else{
                txtMsg.text = "Erro no carregamento"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<Estados?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<Estados>?)
        }
    }
}