package trust.blockchain.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: TransactionSign.kt */
class TransactionSign(val txId: String = "",
                      val sign: ByteArray = ByteArray(0),
                      val unsignedTx: TransactionUnsigned,
                      val encodedRequest: ByteArray = ByteArray(0)){
}
