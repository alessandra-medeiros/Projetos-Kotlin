package conversor.moedas

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    var a = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun USDtoBRL() {
        val usd = findViewById<View>(R.id.USD) as EditText
        usd.inputType = InputType.TYPE_CLASS_NUMBER
        val brl = findViewById<View>(R.id.BRL) as EditText
        brl.inputType = InputType.TYPE_CLASS_NUMBER
        val a = usd.text.toString().toInt().toDouble()
        val result = round(a * 5.04)
        brl.setText(result.toString())
    }

    fun BRLtoUSD() {
        val usd = findViewById<View>(R.id.USD) as EditText
        usd.inputType = InputType.TYPE_CLASS_NUMBER
        val brl = findViewById<View>(R.id.BRL) as EditText
        brl.inputType = InputType.TYPE_CLASS_NUMBER
        val a = brl.text.toString().toInt().toDouble()
        val result = round(a / 5.04)
        usd.setText(result.toString())
    }

    fun click(view: View?) {
        USDtoBRL()
        val b2 =
            findViewById<View>(R.id.convert2) as Button
        b2.isEnabled = false
    }

    fun reset(view: View?) {
        val b1 =
            findViewById<View>(R.id.convert1) as Button
        b1.isEnabled = true
        val b2 =
            findViewById<View>(R.id.convert2) as Button
        b2.isEnabled = true
        val usd = findViewById<View>(R.id.USD) as EditText
        val brl = findViewById<View>(R.id.BRL) as EditText
        usd.setText("")
        brl.setText("")
    }

    fun click2(view: View?) {
        val b1 =
            findViewById<View>(R.id.convert1) as Button
        b1.isEnabled = false
        BRLtoUSD()
    }
}