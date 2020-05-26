package com.example.clientesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var comprasList = mutableListOf<Compras>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter()

        btnAdd.setOnClickListener(View.OnClickListener {
            val it = Intent(this, SaveActivity::class.java)
            startActivity(it)
        })
        initRecyclerView()

        btnSearch.setOnClickListener {
            findAdapter(edtSearch.text.toString())
        }
    }

    override fun onRestart() {
        super.onRestart()
        updateAdapter()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        val comprasDao = ComprasDao(this)
        comprasList.clear()
        comprasList = comprasDao.getAll()
        if (comprasList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Sem dados para exibir")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun findAdapter(produto: String) {
        val comprasDao = ComprasDao(this)
        comprasList.clear()
        comprasList = comprasDao.getByName(produto)
        if (comprasList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("$produto não encontrado")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
            initRecyclerView()
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        val adapter2 = ComprasAdapter(comprasList)
        rvDados.adapter = adapter2
        val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}