package trust.blockchain.blockchain.nimiq

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.entity.PlainAddress
import trust.blockchain.util.ExtensionsKt

/* compiled from: NimiqAddress.kt */
class NimiqAddress : PlainAddress {

    constructor(address: String) : super(address) {
    }

//    fun hexValue(): String {
//        return ExtensionsKt.toHex(wallet.core.jni.NimiqAddress(data()).keyHash())
//    }

    constructor(parcel: Parcel) : super(parcel){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<NimiqAddress> {
        override fun createFromParcel(parcel: Parcel): NimiqAddress {
            return NimiqAddress(parcel)
        }

        override fun newArray(size: Int): Array<NimiqAddress?> {
            return arrayOfNulls(size)
        }
    }
}
