package cripto.`as`

import android.os.Parcel
import android.os.Parcelable

data class Ativo(val id: Int?, val nome: String?, val sigla: String?,
                 val valor: Double, val quantidade: Double, val total: Double, val data: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nome)
        parcel.writeString(sigla)
        parcel.writeDouble(valor)
        parcel.writeDouble(quantidade)
        parcel.writeDouble(total)
        parcel.writeString(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ativo> {
        override fun createFromParcel(parcel: Parcel): Ativo {
            return Ativo(parcel)
        }

        override fun newArray(size: Int): Array<Ativo?> {
            return arrayOfNulls(size)
        }
    }
}