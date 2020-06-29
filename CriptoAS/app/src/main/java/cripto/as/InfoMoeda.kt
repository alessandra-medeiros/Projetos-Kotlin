package cripto.`as`

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_moeda.*
import kotlinx.android.synthetic.main.activity_info_moeda.txtNome
import kotlinx.android.synthetic.main.activity_info_moeda.txtValor
import java.math.RoundingMode
import java.text.DecimalFormat

class InfoMoeda : AppCompatActivity() {
    private lateinit var ativo : Ativo
    private var asyncTask: AtivoTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_moeda)

        ativo = intent.getParcelableExtra<Ativo>("ativo")
        carregarDados()
        txtNome.setText(ativo.nome.toString())
        txtValor.setText(total(ativo.total).toString())
        txtQuantidade.setText(ativo.quantidade.toString())
        txtData.setText(ativo.data.toString())
        txtSigla.setText(ativo.sigla.toString())

        btnRemove.setOnClickListener{
            val ativoDao = DaoAtivo(this)
            ativoDao.remove(ativo)
            onBackPressed()
        }
    }

    fun carregarDados(){
        if(asyncTask==null){
            if (HttpMoeda.hasConnetcion(this)){
                if(asyncTask?.status!= AsyncTask.Status.RUNNING){
                    asyncTask = AtivoTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    inner class AtivoTask: AsyncTask<Void, Void, Moedas?>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground (vararg params: Void?): Moedas? {
            return HttpMoeda.loadCoin(ativo.sigla.toString())
        }

        private fun update (result: Moedas?){
            if(result != null){
                result.last.toString()
                val variacao = variacao(ativo.valor, result.high.toString().toDouble())
                txtVariacao.text = "$variacao"+"%"
                val lucro = lucro(ativo.quantidade, ativo.total, result.high)
                txtLucro.text = lucro
            }
            asyncTask = null
        }

        override fun onPostExecute (result: Moedas?) {
            super.onPostExecute(result)
            update(result as Moedas?)
        }
    }

    fun variacao (vf: Double, vi: Double ): CharSequence? {
        val result = ((vf/vi)-1)*100
        val variacao = result*100
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(variacao)
    }

    fun total (total: Double): String {
        val result = total
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(result)
    }

    fun lucro(qtd: Double, total: Double, preco:Double):String{
        val result = qtd*preco
        val lucro = total-result
        if(lucro<=total){
            lucro*-1
            return lucro.toString()
        }else{
            return lucro.toString()
        }
    }
}