package trust.blockchain.blockchain.bitcoincash

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.entity.PlainAddress

/* compiled from: BitcoinCashAddress.kt */
class BitcoinCashAddress : PlainAddress {
    val prefix = "bitcoincash:"

    constructor(address: String) : super(address){
    }

    override fun display(): String {
        val plainAddress = super.toString()
        return if (plainAddress.startsWith(this.prefix, false)) plainAddress.replace(this.prefix, "", false) else plainAddress
    }

    constructor(parcel: Parcel) : super(parcel) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<BitcoinCashAddress> {
        override fun createFromParcel(parcel: Parcel): BitcoinCashAddress {
            return BitcoinCashAddress(parcel)
        }

        override fun newArray(size: Int): Array<BitcoinCashAddress?> {
            return arrayOfNulls(size)
        }
    }
}
