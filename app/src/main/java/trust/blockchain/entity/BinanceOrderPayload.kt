package trust.blockchain.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics

/* compiled from: BinanceOrderPayload.kt */
class BinanceOrderPayload (val quantity: BigInteger,
                           val trade: Asset,
                           val sideQuantity: String,
                           val price: BigInteger,
                           val side: BinanceOrderSide) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.quantity.toString())
        parcel.writeParcelable(this.trade, i)
        parcel.writeString(this.sideQuantity)
        parcel.writeString(this.price.toString())
        parcel.writeString(this.side.name)
    }

    constructor(parcel: Parcel) : this(
            BigInteger(parcel.readString()),
            parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset,
            parcel.readString()!!,
            BigInteger(parcel.readString()),
            BinanceOrderSide.valueOf(parcel.readString()!!)
    ){
    }

    companion object CREATOR : Creator<BinanceOrderPayload> {
        override fun createFromParcel(parcel: Parcel): BinanceOrderPayload {
            return BinanceOrderPayload(parcel)
        }

        override fun newArray(size: Int): Array<BinanceOrderPayload?> {
            return arrayOfNulls(size)
        }
    }
}
