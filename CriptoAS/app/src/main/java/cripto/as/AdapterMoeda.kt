package cripto.`as`

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_nome_moedas.view.*

class AdapterMoeda (private val moedas: List<ListaMoedas>):
    RecyclerView.Adapter<AdapterMoeda.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_nome_moedas, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val moeda = moedas[vh.adapterPosition]
            val intent = Intent(v.context, AdicionarMoeda::class.java)
            intent.putExtra("nome", moeda.name)
            intent.putExtra("sigla", moeda.sigla)
            v.context.startActivities(arrayOf(intent))
        }
        return vh
    }

    override fun getItemCount(): Int {
        return moedas.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var moeda = moedas[position]
        holder.txtNomeMoeda.text = moeda.name
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtNomeMoeda: TextView = itemView.txtCoinName
    }
}