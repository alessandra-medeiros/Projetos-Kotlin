package corona.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_paises.*

class InfoPaises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_paises)

        val arrayPais = this.intent.getParcelableExtra<Paises>("País")

        paisDia.text = arrayPais.date.toString().substring(0,2)
        paisMes.text = selectMes(arrayPais.date.toString())
        paisHora.text = arrayPais.hour
        pais.text = arrayPais.country
        casos.text = arrayPais.cases.toString()
        mortes.text = arrayPais.deaths.toString()
        confirmados.text = arrayPais.confirmed.toString()
        curados.text = arrayPais.recovered.toString()
    }

    fun selectMes(mes: String): String? {
        var newString: String? = ""
        when {
            mes.substring(3,5) == "01" -> {
                newString = "Janeiro"
            }
            mes.substring(3,5) == "02" -> {
                newString = "Fevereiro"
            }
            mes.substring(3,5) == "03" -> {
                newString = "Março"
            }
            mes.substring(3,5) == "04" -> {
                newString = "Abril"
            }
            mes.substring(3,5) == "05" -> {
                newString = "Maio"
            }
            mes.substring(3,5) == "06" -> {
                newString = "Junho"
            }
            mes.substring(3,5) == "07" -> {
                newString = "Julho"
            }
            mes.substring(3,5) == "08" -> {
                newString = "Agosto"
            }
            mes.substring(3,5) == "09" -> {
                newString = "Setembro"
            }
            mes.substring(3,5) == "10" -> {
                newString = "Outubro"
            }
            mes.substring(3,5) == "11" -> {
                newString = "Novembro"
            }
            mes.substring(3,5) == "12" -> {
                newString = "Dezembro"
            }
        }
        return newString
    }
}