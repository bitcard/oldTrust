package trust.blockchain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlin.jvm.internal.Intrinsics

/* compiled from: Bip32Path.kt */
class Bip32Path (val address: String ,
                 val txCount: Int = 0,
                 val path: String = ""): Parcelable {




    val derivationPath: DerivationPath
        get() = DerivationPath(this.path)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, i: Int) {
        dest.writeString(this.address)
        dest.writeInt(this.txCount)
        dest.writeString(this.path)
    }

    constructor(parcel: Parcel) : this (
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!
    ){
    }

    companion object CREATOR : Parcelable.Creator<Bip32Path> {
        override fun createFromParcel(parcel: Parcel): Bip32Path {
            return Bip32Path(parcel)
        }

        override fun newArray(size: Int): Array<Bip32Path?> {
            return arrayOfNulls(size)
        }
    }
}
