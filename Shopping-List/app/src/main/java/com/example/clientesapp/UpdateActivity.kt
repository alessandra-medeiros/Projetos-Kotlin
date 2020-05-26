package com.example.clientesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val produto = intent.getParcelableExtra<Compras>("compra")
        edtNome.setText(produto.quantidade.toString())
        edtQuantidade.setText(produto.quantidade.toString())

        btnRemove.setOnClickListener {
            val comprasDao = ComprasDao(this)
            comprasDao.remove(produto)
            onBackPressed()
        }

        btnSave.setOnClickListener {
            val compras = Compras(produto.id,edtNome.text.toString(),edtQuantidade.text.toString())
            val comprasDao = ComprasDao(this)
            comprasDao.update(compras)
            onBackPressed()
        }

        btnBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }





}
