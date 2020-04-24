package frases.coach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cliqueBotao (view: View) {
        var sortearFrase = Random.nextInt(0,10)
        Log.i("Sorteio","Número sorteado: $sortearFrase")

        var fraseSorteada = when (sortearFrase){
            1-> "“Ter sucesso é falhar repetidamente, mas sem perder o entusiasmo”"
            2-> "“Uma vez que você tenha aceitado suas falhas, ninguém poderá usá-las contra você”"
            3-> "“O que nos parece uma provação amarga pode ser uma bênção disfarçada”"
            4-> "“O grande segredo de uma boa vida é encontrar qual é o seu destino. E realizá-lo”"
            5-> "“Se você não gosta do que está sendo dito, mude a conversa”"
            6-> "“A lógica pode levar de um ponto A a um ponto B. A imaginação pode levar a qualquer lugar”"
            7-> "“Você se torna o que você acredita”"
            8-> "“Você deve lutar mais de uma batalha para se tornar um vencedor”"
            9-> "“Nosso maior medo não deve ser o fracasso, mas ser bem-sucedidos em algo que não importa”"
            10-> "“São as nossas decisões e nossas habilidades que demonstram quem somos na realidade.”"
            11-> "“Uma jornada de mil milhas começa com um simples passo.”"
            12-> "“Se você não está disposto a arriscar, esteja disposto a uma vida comum”"
            13-> "“Não é o mais forte que sobrevive, nem o mais inteligente. Quem sobrevive é o mais disposto à mudança”"
            14-> "“As pessoas costumam dizer que a motivação não dura sempre. Bem, nem o efeito do banho, por isso recomenda-se diariamente”"
            else-> "“Você não pode deixar de usar a criatividade. Quanto mais você usa, mais você tem”"
        }
        fraseGerada.text = fraseSorteada
    }
}