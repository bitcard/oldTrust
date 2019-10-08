package trust.blockchain.blockchain.tezos

import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import trust.blockchain.FeeCalculator
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.tezos.entity.TezosAccount
import trust.blockchain.blockchain.tezos.entity.TezosHead
import trust.blockchain.blockchain.tezos.entity.TezosSendType
import trust.blockchain.entity.Address
import trust.blockchain.entity.Asset
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: TezosService.kt */
abstract class TezosService : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.TEZOS)

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    abstract fun fetchHead(): TezosHead

    abstract fun fetchSendType(asset: Asset): TezosSendType

    abstract fun getAccountData(address: Address): TezosAccount

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }

    fun updateDefaultFee(feeCalculator: FeeCalculator, sendType: TezosSendType, z: Boolean): Long {
        var defaultFee = feeCalculator.defaultFee()
        when (sendType) {
            TezosSendType.REVEAL_AND_TRANSFER -> defaultFee *= 2.toLong()
            TezosSendType.TRANSFER -> {
            }
        }
        return if (z) defaultFee + activationFee else defaultFee
    }

    companion object {
        val activationFee: Long = 277000
    }
}
