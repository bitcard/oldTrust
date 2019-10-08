package trust.blockchain.blockchain.ontology

import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.ontology.entity.OntologyGasPriceResult
import trust.blockchain.entity.Account
import trust.blockchain.entity.TransactionUnsigned
import java.security.SecureRandom

/* compiled from: OntologyService.kt */
abstract class OntologyService : RpcService {

    abstract val gasPrice: OntologyGasPriceResult

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ONTOLOGY)

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        val just = Single.just(ByteArray(0))
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(ByteArray(0))")
        return just
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.fromCallable<Long> {
            Math.abs(SecureRandom().nextInt()).toLong();
        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
