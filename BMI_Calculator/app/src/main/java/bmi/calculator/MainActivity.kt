package bmi.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener(View.OnClickListener {
            val altura = edtAltura.text.toString().toDouble()
            val peso = edtPeso.text.toString().toDouble()

            val intent = Intent(this,Main2Activity::class.java)
            intent.putExtra("altura", altura)
            intent.putExtra("peso", peso)
            startActivity(intent)
        })
    }
}