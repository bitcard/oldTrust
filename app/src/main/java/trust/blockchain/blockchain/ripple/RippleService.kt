package trust.blockchain.blockchain.ripple

import java.math.BigDecimal
import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.ripple.entity.RippleAccountData
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: RippleService.kt */
abstract class RippleService : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.RIPPLE)

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    abstract fun getAccountData(str: String): RippleAccountData

    abstract fun isAccountActivated(str: String): Boolean

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }

    companion object {
        val reserveBalance: BigDecimal

        init {
            reserveBalance = BigDecimal.valueOf(20).multiply(BigDecimal.TEN.pow(Slip.RIPPLE.unit().decimals))!!
        }
    }
}
