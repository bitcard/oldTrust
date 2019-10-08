package trust.blockchain.blockchain.theta

import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.theta.entity.ThetaAccount
import trust.blockchain.entity.Address
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: ThetaService.kt */
abstract class ThetaService : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.THETA)

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    abstract fun getAccountData(address: Address): ThetaAccount

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
