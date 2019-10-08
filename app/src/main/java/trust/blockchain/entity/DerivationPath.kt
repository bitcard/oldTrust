package trust.blockchain.entity

import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import wallet.core.jni.CoinType
import wallet.core.jni.Purpose

/* compiled from: DerivationPath.kt */
class DerivationPath {
    private val indexCount: Int = 5
    private var indices: MutableList<DerivationIndex> = ArrayList(this.indexCount)

    constructor(path: String) {
        this.indices = ArrayList(this.indexCount)
        for (str2 in path.split("/")) {
            if (!Intrinsics.areEqual(str2, "m")) {
                if (str2.endsWith("'", false)) {
                    this.indices.add(DerivationIndex(Integer.parseInt(str2.dropLast(1)), true))
                } else {
                    this.indices.add(DerivationIndex(Integer.parseInt(str2), false))
                }
            }
        }
        if (this.indices.size != this.indexCount) {
            throw IllegalStateException("Indices size should equal index count")
        }
    }

    constructor(purpose: Purpose, coinType: CoinType, i: Int = 0, i2: Int = 0, i3: Int = 0) {
        this.indices = ArrayList(this.indexCount)
        this.purpose = purpose
        this.coinType = coinType
        this.account = i
        this.changeIndex = i2
        this.addressIndex = i3
    }

    var account: Int
        get() = this.indices[2].value
        set(i) = this.indices.add(2, DerivationIndex(i, true))

    var addressIndex: Int
        get() = this.indices[4].value
        set(i) = this.indices.add(4, DerivationIndex(i, false))

    var changeIndex: Int
        get() = this.indices[3].value
        set(i) = this.indices.add(3, DerivationIndex(i, false))

    var coinType: CoinType
        get() {
            return CoinType.createFromValue(this.indices[1].value)
        }
        set(coinType) {
            this.indices.add(1, DerivationIndex(coinType.value(), true))
        }

    var purpose: Purpose
        get() {
            return Purpose.createFromValue(this.indices[0].value)
        }
        set(purpose) {
            this.indices.add(0, DerivationIndex(purpose.value(), true))
        }

    fun description(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("m/")
        stringBuilder.append(this.indices.joinToString("/"){
            it.description()
        })
        return stringBuilder.toString()
    }
//
//    fun getIndices(): List<DerivationIndex>? {
//        return this.indices
//    }

    fun incrementAddressIndexBy(i: Int) {
        addressIndex = addressIndex + i
    }
//
//    fun setIndices(list: MutableList<DerivationIndex>) {
//        Intrinsics.checkParameterIsNotNull(list, "<set-?>")
//        this.indices = list
//    }

    companion object {
        @JvmStatic
        fun toDerivationPath(str: String): DerivationPath {
            return DerivationPath(str)
        }
    }
}
