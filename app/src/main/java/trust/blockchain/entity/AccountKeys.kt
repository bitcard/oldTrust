package trust.blockchain.entity

import trust.blockchain.util.ExtensionsKt
import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics

/* compiled from: AccountKeys.kt */
class AccountKeys {
    val data: ByteArray
    val extendedPrivateKey: String
    val extendedPublicKey: String
    val privateKey: BigInteger

    constructor(data: ByteArray, extendedPrivateKey: String, extendedPublicKey: String) {
        this.data = data
        this.extendedPrivateKey = extendedPrivateKey
        this.extendedPublicKey = extendedPublicKey
        this.privateKey = BigInteger(1, this.data)
    }

    constructor(data: ByteArray) : this(data, "", ""){
    }

    constructor(privateKey: BigInteger) : this(ExtensionsKt.toBytes(privateKey)) {
    }
}
