package trust.blockchain.blockchain.wan

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.blockchain.ethereum.EthLikeAddress

/* compiled from: WanchainAddress.kt */
class WanchainAddress : EthLikeAddress {

    constructor(address: String) : super(address){
    }

    override fun display(): String {
        return data()
    }

    constructor(parcel: Parcel) : super(parcel){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<WanchainAddress> {
        override fun createFromParcel(parcel: Parcel): WanchainAddress {
            return WanchainAddress(parcel)
        }

        override fun newArray(size: Int): Array<WanchainAddress?> {
            return arrayOfNulls(size)
        }
    }
}
