package trust.blockchain.blockchain.zilliqa

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.entity.PlainAddress
import trust.blockchain.util.ExtensionsKt

/* compiled from: ZilliqaAddress.kt */
class ZilliqaAddress : PlainAddress {

    constructor(address: String) : super(address){
    }

    private fun isLegacyZillAddress(str: String): Boolean {
        return str.startsWith("0x", false) || !str.startsWith("zil", true)
    }

//    override fun data(): String {
//        var plainAddress = super.toString()
//        Intrinsics.checkExpressionValueIsNotNull(plainAddress, "super.toString()")
//        if (!isLegacyZillAddress(plainAddress)) {
//            return plainAddress
//        }
//        plainAddress = wallet.core.jni.ZilliqaAddress(ExtensionsKt.toHexByteArray(plainAddress)).description()
//        Intrinsics.checkExpressionValueIsNotNull(plainAddress, "JNIZilliqaAddress(addres…yteArray()).description()")
//        return plainAddress
//    }

    override fun display(): String {
        return data()
    }

    override fun equals(obj: Any?): Boolean {
        val data = data()
        if (obj != null) {
            return data.equals((obj as PlainAddress).data(), true)
        }
        throw TypeCastException("null cannot be cast to non-null type trust.blockchain.entity.PlainAddress")
    }

    override fun hashCode(): Int {
        return data().hashCode()
    }

//    fun hexValue(): String {
//        var plainAddress = super.toString()
//        Intrinsics.checkExpressionValueIsNotNull(plainAddress, "super.toString()")
//        if (isLegacyZillAddress(plainAddress)) {
//            return plainAddress
//        }
//        plainAddress = wallet.core.jni.ZilliqaAddress(plainAddress).keyHash()
//        Intrinsics.checkExpressionValueIsNotNull(plainAddress, "JNIZilliqaAddress(address).keyHash()")
//        return plainAddress
//    }

    constructor(parcel: Parcel) : super(parcel){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ZilliqaAddress> {
        override fun createFromParcel(parcel: Parcel): ZilliqaAddress {
            return ZilliqaAddress(parcel)
        }

        override fun newArray(size: Int): Array<ZilliqaAddress?> {
            return arrayOfNulls(size)
        }
    }
}
