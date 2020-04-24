package priceml.calculator

class Calculo {
    companion object{
        fun mL(preco: Double, ml: Double): String {
            val result = preco/ml;
            return String.format("%.4f",result)
        }
    }
}