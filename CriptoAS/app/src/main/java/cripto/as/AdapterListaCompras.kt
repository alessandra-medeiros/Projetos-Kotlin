package cripto.`as`

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_lista_compras.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class AdapterListaCompras (private val ativos: List<Ativo>):
    RecyclerView.Adapter<AdapterListaCompras.VH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListaCompras.VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_lista_compras,parent,false)
        val vh = VH(v)

        vh.itemView.setOnClickListener{
            val ativo = ativos[vh.adapterPosition]
            val it = Intent(parent.context,InfoMoeda::class.java)
            it.putExtra("ativo",ativo)
            parent.context.startActivities(arrayOf(it))
        }
        return vh
    }

    override fun getItemCount(): Int {
        return ativos.size
    }

    override fun onBindViewHolder (holder: AdapterListaCompras.VH, position: Int) {
        var ativo = ativos[position]
        holder.txtNome.text = ativo.nome.toString()
        holder.txtTotal.text = total(ativo.total)
        holder.txtQuantidade.text = ativo.quantidade.toString()
        holder.txtData.text = ativo.data.toString()
    }

    class VH (itenView: View):RecyclerView.ViewHolder(itenView){
        var txtNome: TextView = itenView.txtNome
        var txtTotal: TextView = itenView.txtTotal
        var txtQuantidade: TextView = itenView.txtQuantidade
        var txtData: TextView = itenView.txtData
    }

    fun total (total: Double): String{
        val result = total
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(result)
    }
}