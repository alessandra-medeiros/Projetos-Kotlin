package corona.world

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_load_paises.*

class LoadPaises : AppCompatActivity() {

    private var listaPaises = arrayListOf<Paises>()
    private var adapter = AdapterPaises(listaPaises)
    private var asyncTask : CountryTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_paises)
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
        listaPaises.clear()
        if(listaPaises.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HttpPaises.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = CountryTask()
                        asyncTask?.execute()
                    }
                }else{
                    progressBar.visibility = View.GONE
                }
            }else if(asyncTask?.status== AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    inner class CountryTask: AsyncTask<Void, Void, List<Paises?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Paises>? {
            return HttpPaises.loadCountrys()
        }

        private fun update(result: List<Paises>?){
            if(result != null){
                listaPaises.clear()
                listaPaises.addAll(result)
            }else{
                txtMsg.text = "Erro no carregamento"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<Paises?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<Paises>?)
        }
    }
}