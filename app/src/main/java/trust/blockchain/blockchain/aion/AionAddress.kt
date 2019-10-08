package trust.blockchain.blockchain.aion

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.blockchain.ethereum.EthLikeAddress

/* compiled from: AionAddress.kt */
class AionAddress : EthLikeAddress {

    constructor(str:String) : super(str){}

    override fun display(): String {
        return data()
    }

    constructor(parcel: Parcel) : super(parcel){
    }

    companion object {
        @JvmField val CREATOR = object : Creator<AionAddress> {
            override fun createFromParcel(parcel: Parcel): AionAddress {
                return AionAddress(parcel)
            }

            override fun newArray(i: Int): Array<AionAddress?> {
                return arrayOfNulls(i)
            }
        }
    }
}
