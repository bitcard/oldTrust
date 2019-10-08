package trust.blockchain.blockchain.cosmos

import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.cosmos.entity.CosmosAccount
import trust.blockchain.blockchain.cosmos.entity.CosmosNodeInfo
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: CosmosService.kt */
abstract class CosmosService : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.COSMOS)

    abstract val nodeInfo: CosmosNodeInfo
    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    abstract fun getAccountData(str: String): CosmosAccount

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
