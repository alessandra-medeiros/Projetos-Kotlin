package com.example.covid19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*


class Info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val arrayBoletim = this.intent.getParcelableExtra<Boletim>("Boletim")

        tSuspeitos.text = arrayBoletim.suspeitos.toString()
        tConfirmados.text = arrayBoletim.confirmados.toString()
        tDescartados.text = arrayBoletim.descartados.toString()
        tMonitoramento.text = arrayBoletim.monitoramento.toString()
        tCurados.text = arrayBoletim.mortes.toString()
        tIsoDomiciliar.text = arrayBoletim.curados.toString()
        tIsoHospitalar.text = arrayBoletim.sDomiciliar.toString()
        tIsoHospitalarConf.text = arrayBoletim.sHospitalar.toString()
        tMortes.text = arrayBoletim.Chospitalar.toString()
        tData.text = arrayBoletim.data
        tHora.text = arrayBoletim.hora

        println(arrayBoletim.toString())
    }
}