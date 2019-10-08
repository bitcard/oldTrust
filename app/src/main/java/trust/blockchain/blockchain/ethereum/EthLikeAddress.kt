package trust.blockchain.blockchain.ethereum

import android.os.Parcel
import android.os.Parcelable.Creator
import org.web3j.crypto.Hash
import org.web3j.utils.Numeric
import trust.blockchain.entity.Address
import trust.blockchain.entity.PlainAddress

open class EthLikeAddress : PlainAddress {
    override fun display(): String {
        val cleanHexPrefix = Numeric.cleanHexPrefix(this.data!!)
        val cleanHexPrefix2 = Numeric.cleanHexPrefix(Numeric.toHexString(Hash.sha3(cleanHexPrefix.toByteArray())))
        val length = cleanHexPrefix.length
        val stringBuilder = StringBuilder(length + 2)
        stringBuilder.append("0x")
        for (i in 0 until length) {
            if (cleanHexPrefix2[i] > '7') {
                stringBuilder.append(cleanHexPrefix[i].toString().toUpperCase())
            } else {
                stringBuilder.append(cleanHexPrefix[i])
            }
        }
        return stringBuilder.toString()
    }

    constructor(str: String) : super(normalizeAddressData(str)){}
    protected constructor(parcel: Parcel) : super(parcel) {}

    companion object {
        @JvmField val CREATOR = object : Creator<EthLikeAddress> {
            override fun createFromParcel(parcel: Parcel): EthLikeAddress {
                return EthLikeAddress(parcel)
            }

            override fun newArray(size: Int): Array<EthLikeAddress?> {
                return arrayOfNulls(size)
            }
        }

        @JvmField val EMPTY: Address = EthLikeAddress("0x0000000000000000000000000000000000000000")
        @JvmField val SIZE = 40

        @JvmStatic
        private fun normalizeAddressData(str: String): String {
            var str = str.toLowerCase()
            if (Numeric.containsHexPrefix(str)) {
                return str
            }
            return "0x" + str
        }
    }
}
