package corona.world

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnTodosOsEstados.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoadEstados::class.java)
            this.startActivity(intent)
        })

        btnTodosOsPaises.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoadPaises::class.java)
            this.startActivity(intent)
        })

        btnProcurar.setOnClickListener(View.OnClickListener {
            val txt = selectUF.text
            val intent = Intent(this, InfoEstados::class.java)
            intent.putExtra("UF",txt)
            this.startActivity(intent)
        })
    }
}