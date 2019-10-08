package trust.blockchain.entity

import android.os.Parcel
import android.os.Parcelable

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip


/* compiled from: SwapPayload.kt */
class SwapPayload (val coin: Slip,
                   val tokenId: String,
                   val symbol: String,
                   val tradeSymbol: String,
                   val decimals: Int,
                   val price: BigInteger,
                   val value: BigInteger,
                   val direction: SwapDirection,
                   val fromAsset: Asset = defaultAsset(coin),
                   val toAsset: Asset = defaultAsset(coin),
                   val gasLimit: Long = 0,
                   val tradeContract: String = ""): Parcelable {


    constructor(coin: Slip, str: String, str2: String, str3: String, i: Int, bigInteger: BigInteger, bigInteger2: BigInteger, swapDirection: SwapDirection) : this(coin, str, str2, str3, i, bigInteger, bigInteger2, swapDirection, defaultAsset(coin), defaultAsset(coin), 0, "") {}
//
//    constructor(slip: Slip, str: String, str2: String, str3: String, i: Int, bigInteger: BigInteger, bigInteger2: BigInteger, swapDirection: SwapDirection, asset: Asset) : this(slip, str, str2, str3, i, bigInteger, bigInteger2, swapDirection, asset, null, 0, null, 3584, null) {}
//
//    constructor(slip: Slip, str: String, str2: String, str3: String, i: Int, bigInteger: BigInteger, bigInteger2: BigInteger, swapDirection: SwapDirection, asset: Asset, asset2: Asset) : this(slip, str, str2, str3, i, bigInteger, bigInteger2, swapDirection, asset, asset2, 0, null, 3072, null) {}

    override fun describeContents(): Int {
        return 0
    }

    fun unit(): Unit {
        return Unit(this.decimals, this.symbol)
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel")
        parcel.writeInt(this.coin.coinType().value())
        parcel.writeString(this.tokenId)
        parcel.writeString(this.symbol)
        parcel.writeString(this.tradeSymbol)
        parcel.writeInt(this.decimals)
        parcel.writeString(this.price.toString())
        parcel.writeString(this.value.toString())
        parcel.writeString(this.direction.name)
        parcel.writeParcelable(this.fromAsset, i)
        parcel.writeParcelable(this.toAsset, i)
        parcel.writeLong(this.gasLimit)
        parcel.writeString(this.tradeContract)
    }
    constructor(parcel: Parcel) :this(
            Slip.find(parcel.readInt()),
            parcel.readString() as String,
            parcel.readString() as String,
            parcel.readString() as String,
            parcel.readInt(),
            BigInteger(parcel.readString()),
            BigInteger(parcel.readString()),
            SwapDirection.valueOf(parcel.readString() as String),
            parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset,
            parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset,
            parcel.readLong(),
            parcel.readString() as String)

    companion object CREATOR : Parcelable.Creator<SwapPayload> {
        override fun createFromParcel(parcel: Parcel): SwapPayload {
            return SwapPayload(parcel)
        }

        override fun newArray(size: Int): Array<SwapPayload?> {
            return arrayOfNulls(size)
        }
    }
}

fun defaultAsset(coin: Slip): Asset {
    return coin.coinAsset(Account(coin, "", ""), true)
}