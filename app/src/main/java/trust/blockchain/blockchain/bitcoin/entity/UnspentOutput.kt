package trust.blockchain.blockchain.bitcoin.entity

import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address

/* compiled from: BitcoinLikeModels.kt */
class UnspentOutput (val address: String = "",
                     var txid: String = "",
                     val value: String = "",
                     val vout: Int = 0,
                     val path: String = "",
                     val height: Long = 0,
                     val confirmations: Long = 0
                     ){
}
