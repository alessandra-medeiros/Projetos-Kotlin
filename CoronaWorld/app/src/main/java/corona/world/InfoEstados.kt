package corona.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_estados.*
import kotlinx.android.synthetic.main.activity_info_estados.estadoHora
import kotlinx.android.synthetic.main.activity_info_estados.casos
import kotlinx.android.synthetic.main.activity_info_estados.mortes
import kotlinx.android.synthetic.main.activity_info_paises.*

class InfoEstados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_estados)

        val arrayEstados = this.intent.getParcelableExtra<Estados>("Estado")

        estadoDia.text = arrayEstados.date.toString().substring(0,2)
        estadoMes.text = selectMes(arrayEstados.date.toString())
        estadoHora.text = arrayEstados.hour
        estadoNome.text = arrayEstados.state
        casos.text = arrayEstados.cases.toString()
        mortes.text = arrayEstados.deaths.toString()
        suspeitos.text = arrayEstados.suspects.toString()
        descartados.text = arrayEstados.discards.toString()
    }

    fun selectMes(mes: String): String? {
        var newString: String? = ""
        when {
            mes.substring(3,5) == "01" -> {
                newString = "JANEIRO"
            }
            mes.substring(3,5) == "02" -> {
                newString = "FEVEREIRO"
            }
            mes.substring(3,5) == "03" -> {
                newString = "MARÇO"
            }
            mes.substring(3,5) == "04" -> {
                newString = "ABRIL"
            }
            mes.substring(3,5) == "05" -> {
                newString = "MAIO"
            }
            mes.substring(3,5) == "06" -> {
                newString = "JUNHO"
            }
            mes.substring(3,5) == "07" -> {
                newString = "JULHO"
            }
            mes.substring(3,5) == "08" -> {
                newString = "AGOSTO"
            }
            mes.substring(3,5) == "09" -> {
                newString = "SETEMBRO"
            }
            mes.substring(3,5) == "10" -> {
                newString = "OUTUBRO"
            }
            mes.substring(3,5) == "11" -> {
                newString = "NOVEMBRO"
            }
            mes.substring(3,5) == "12" -> {
                newString = "DEZEMBRO"
            }
        }
        return newString
    }
}