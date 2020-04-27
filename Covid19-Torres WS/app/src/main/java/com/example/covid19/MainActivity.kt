package com.example.covid19

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private var listaBoletim = arrayListOf<Boletim>()
  private var adapter = Adapter(listaBoletim)
  private var asyncTask : boletimTask? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(findViewById(R.id.my_tool))

    carregaDados()
    listDados.layoutManager = LinearLayoutManager(applicationContext)
    listDados.itemAnimator = DefaultItemAnimator()
    listDados.adapter = adapter
  }

  fun showProgress(show: Boolean){
    if(show){
      txtMsg.text = "Carregando..."
    }else{
      txtMsg.visibility = if(show) View.VISIBLE else View.GONE
      progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }
  }

  private fun updateBoletins(result: List<Boletim>?){
    if(result != null){
      listaBoletim.clear()
      listaBoletim.addAll(result.reversed())
    }else{
      txtMsg.text = "Erro ao carregar"
    }
    adapter.notifyDataSetChanged()
    asyncTask = null
  }

  fun carregaDados(){
    listaBoletim.clear()
    if(listaBoletim.isNotEmpty()){
      showProgress(false)
    }else{
      if(asyncTask==null){
        if(BoletimHttp.hasConnection(this)){
          //startDownload()
          if(asyncTask?.status!= AsyncTask.Status.RUNNING){
            asyncTask = boletimTask()
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

  inner class boletimTask: AsyncTask<Void, Void, List<Boletim?>>(){
    override fun onPreExecute() {
      super.onPreExecute()
      showProgress(true)
    }
    override fun doInBackground(vararg p0: Void?): List<Boletim>? {
      return BoletimHttp.loadBoletim()
    }
    override fun onPostExecute(result: List<Boletim?>?) {
      super.onPostExecute(result)
      showProgress(false)
      updateBoletins(result as List<Boletim>?)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    R.id.menu_refresh -> {
      carregaDados()
      true
    }
    else -> {
      super.onOptionsItemSelected(item)
    }
  }
}