package trust.blockchain.blockchain.tron

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import org.web3j.utils.Numeric
import trust.blockchain.entity.PlainAddress
import wallet.core.jni.Base58
import wallet.core.jni.Hash

/* compiled from: TronAddress.kt */
class TronAddress : PlainAddress {
    constructor(address: String) : super(address){
    }

    fun hexValue(): String {
        val decodeNoCheck = Base58.decodeNoCheck(data())
        if (decodeNoCheck.size <= 4) {
            return ""
        }
        val toHexString: String
        val decodeData = ByteArray(decodeNoCheck.size - 4)
        System.arraycopy(decodeNoCheck, 0, decodeData, 0, decodeData.size)
        val sha256 = Hash.sha256(Hash.sha256(decodeData))
        if (sha256[0] == decodeNoCheck[decodeData.size] && sha256[1] == decodeNoCheck[decodeData.size + 1] && sha256[2] == decodeNoCheck[decodeData.size + 2] && sha256[3] == decodeNoCheck[decodeData.size + 3]) {
            toHexString = Numeric.toHexString(decodeData, 0, decodeData.size, false)
            Intrinsics.checkExpressionValueIsNotNull(toHexString, "Numeric.toHexString(deco…, decodeData.size, false)")
        } else {
            toHexString = ""
        }
        return toHexString
    }

    constructor(parcel: Parcel) : super(parcel){
    }

    companion object CREATOR : Creator<TronAddress> {
        override fun createFromParcel(parcel: Parcel): TronAddress {
            return TronAddress(parcel)
        }

        override fun newArray(size: Int): Array<TronAddress?> {
            return arrayOfNulls(size)
        }
    }
}
