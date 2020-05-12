package corona.world

import android.os.Parcel
import android.os.Parcelable

class Paises(
    var country: String?,
    var cases: Int=0,
    var confirmed: Int=0,
    var deaths: Int=0,
    var recovered: Int=0,
    var date: String?,
    var hour: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeInt(cases)
        parcel.writeInt(confirmed)
        parcel.writeInt(deaths)
        parcel.writeInt(recovered)
        parcel.writeString(date)
        parcel.writeString(hour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paises> {
        override fun createFromParcel(parcel: Parcel): Paises {
            return Paises(parcel)
        }

        override fun newArray(size: Int): Array<Paises?> {
            return arrayOfNulls(size)
        }
    }
}