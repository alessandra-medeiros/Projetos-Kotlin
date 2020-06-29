package cripto.`as`

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cripto.`as`.AdapterListaCompras
import cripto.`as`.R
import cripto.`as`.Ativo
import cripto.`as`.DaoAtivo
import kotlinx.android.synthetic.main.activity_lista_compras.*

class ListaCompras : AppCompatActivity() {
    private var listaAtivos = mutableListOf<Ativo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_compras)
        updateAdapter()
        initRecycleView()
    }

    override fun onRestart() {
        super.onRestart()
        updateAdapter()
        initRecycleView()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecycleView()
    }

    private fun updateAdapter(){
        val ativoDao = DaoAtivo(this)
        listaAtivos.clear()
        listaAtivos = ativoDao.getAll()
        if (listaAtivos.isEmpty()){
            recycleListCompra.setVisibility(View.GONE)
            txtMsg.setVisibility(View.VISIBLE)
            txtMsg.setText("Nenhum dado.")
        }else{
            recycleListCompra.setVisibility(View.VISIBLE)
            txtMsg.setVisibility(View.GONE)
        }
        recycleListCompra.adapter?.notifyDataSetChanged()
    }

    private fun initRecycleView(){
        val adapter2 = AdapterListaCompras(listaAtivos)
        recycleListCompra.adapter = adapter2
        val layout = LinearLayoutManager(this)
        recycleListCompra.layoutManager = layout
    }
}