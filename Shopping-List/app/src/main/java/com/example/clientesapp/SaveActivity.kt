package com.example.clientesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_save.*

class SaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        btnBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        btnSave.setOnClickListener(View.OnClickListener {
            val compras = Compras(null,edtNome.text.toString(),edtQuantidade.text.toString())
            val comprasDao = ComprasDao(this)
            comprasDao.insert(compras)
            onBackPressed()
        })
    }
}
