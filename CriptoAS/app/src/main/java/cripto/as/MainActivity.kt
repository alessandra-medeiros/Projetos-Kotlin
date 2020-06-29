package cripto.`as`

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cripto.`as`.SelecionarMoeda
import cripto.`as`.ListaCompras
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SelecionarMoeda::class.java)
            startActivity(intent)
        })

        btnListaAtivos.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListaCompras::class.java)
            startActivity(intent)
        })
    }
}