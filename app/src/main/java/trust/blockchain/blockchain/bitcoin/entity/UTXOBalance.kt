package trust.blockchain.blockchain.bitcoin.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: BitcoinLikeModels.kt */
class UTXOBalance(
        val balance: String,
        val tokens: List<UTXOToken>,
        val txids: List<String>) {
}
