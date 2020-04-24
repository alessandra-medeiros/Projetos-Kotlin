package bmi.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.math.round

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val altura = intent.getDoubleExtra("altura", 0.0)
        val peso = intent.getDoubleExtra("peso", 0.0)

        calculaImc(altura, peso)
        edtResultado.text = calculaImc(altura, peso).toString()
    }

    fun calculaImc(altura: Double, peso: Double):String{
        val result = round(peso / (altura * altura)).toString()
        return "$result"
    }
}
