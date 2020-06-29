package cripto.`as`

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_selecionar_moeda.*

class SelecionarMoeda : AppCompatActivity() {

    val listaMoedas = arrayListOf<ListaMoedas>(
        ListaMoedas("Bitcoin", "BTC"),
        ListaMoedas("Litecoin", "LTC"),
        ListaMoedas("BCash", "BCH"),
        ListaMoedas("XRP (Ripple)", "XRP"),
        ListaMoedas("Ethereum", "ETH"),
        ListaMoedas("USD Coin", "USDC")
    )
    val adapter = AdapterMoeda(listaMoedas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecionar_moeda)
        initRecycleView()
    }

    fun initRecycleView(){
        recycleCoin.adapter=adapter
        val layout = LinearLayoutManager(this)
        recycleCoin.layoutManager=layout
    }
}