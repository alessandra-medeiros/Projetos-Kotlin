package cripto.`as`

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_adicionar_moeda.*

class AdicionarMoeda : AppCompatActivity() {
    var sigla : String ="BTC"
    var nome: String ="Bitcoin"
    private var asyncTask: CoinTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_moeda)

        sigla = intent.getStringExtra("sigla")
        nome = intent.getStringExtra("nome")
        txtNome.text = nome

        carregarDados()

        btnAdd.setOnClickListener(View.OnClickListener {
            var ativo = Ativo(null,txtNome.text.toString(),sigla,txtValor.text.toString().toDouble(),edtQdt.text.toString().toDouble(),
                total(txtValor.text.toString().toDouble(), edtQdt.text.toString().toDouble()),edtData.text.toString())
            var ativoDao = DaoAtivo(this)
            ativoDao.insert(ativo)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }

    fun carregarDados(){
        if(asyncTask==null){
            if (HttpMoeda.hasConnetcion(this)){
                if(asyncTask?.status!=AsyncTask.Status.RUNNING){
                    asyncTask = CoinTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    inner class CoinTask: AsyncTask<Void, Void, Moedas?>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): Moedas? {
            return HttpMoeda.loadCoin(sigla)
        }

        private fun update(result: Moedas?){
            if(result != null){
                txtValor.text = result.high.toString()
            }
            asyncTask = null
        }

        override fun onPostExecute(result: Moedas?) {
            super.onPostExecute(result)
            update(result as Moedas?)
        }
    }

    fun total(valor: Double, qtd: Double): Double {
        val total = valor*qtd
        return total
    }
}