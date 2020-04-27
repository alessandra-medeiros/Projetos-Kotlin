package com.example.covid19

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_item.view.*

class Adapter(private val boletins: List<Boletim>): RecyclerView.Adapter<Adapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_item,parent,false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val intent = Intent(v.context, Info::class.java)
            var arrayBoletim = boletins[vh.adapterPosition]
            intent.putExtra("Boletim", arrayBoletim)
            v.context.startActivity(intent)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return boletins.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var boletim = boletins[position]
        holder.iDataBoletim.text = boletim.data
        holder.iNumSuspeitos.text = boletim.suspeitos.toString()
    }

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        var iDataBoletim:TextView = itemView.iDataBoletim
        var iNumSuspeitos:TextView = itemView.iNumSuspeitos
    }
}
