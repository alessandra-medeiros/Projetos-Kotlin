package com.example.clientesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.compras_item.view.*

class ComprasAdapter (private val compras: List<Compras>):
    RecyclerView.Adapter<ComprasAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.compras_item,parent,false)
        val vh = VH(v)

        vh.itemView.setOnClickListener{
            val produto= compras[vh.adapterPosition]
            val it = Intent(parent.context,UpdateActivity::class.java)
            it.putExtra("compras",produto)
           parent.context.startActivity(it)

        }
        return vh
    }

    override fun getItemCount(): Int {
        return compras.size
    }

    override fun onBindViewHolder(holder: ComprasAdapter.VH, position: Int) {
        val compras = compras[position]
        holder.txtNomeProduto.text=compras.produto.toString()
        holder.txtQuantidade.text=compras.quantidade.toString()
    }

    class VH(itenView: View): RecyclerView.ViewHolder(itenView){
        var txtNomeProduto:TextView=itenView.txtNomeProduto
        var txtQuantidade:TextView=itenView.txtQuantidade
    }
}