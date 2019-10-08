package trust.blockchain.blockchain.bitcoin.entity

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Bip32Path

/* compiled from: BitcoinLikeModels.kt */
object BitcoinLikeModelsKt {
    fun toBip32Path(uTXOToken: UTXOToken): Bip32Path {
        return Bip32Path(uTXOToken.name, uTXOToken.transfers.toInt(), uTXOToken.path)
    }
}
