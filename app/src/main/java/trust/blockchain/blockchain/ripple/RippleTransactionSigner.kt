package trust.blockchain.blockchain.ripple

import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
//import wallet.core.jni.RippleSigner
import wallet.core.jni.proto.Ripple.SigningInput

/* compiled from: RippleTransactionSigner.kt */
class RippleTransactionSigner(private val rippleRpcService: RippleService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.RIPPLE)

    private fun getFee(transactionUnsigned: TransactionUnsigned): Long {
        try {
            return transactionUnsigned.fee().calculateNetworkFee().toLong()
        } catch (unused: Exception) {
            return transactionUnsigned.account().coin.feeCalculator().defaultFee()
        }

    }

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val rippleService = this.rippleRpcService
//        val accountData = rippleService.getAccountData( unsignedTx.account().address().data())
//        val longValue = unsignedTx.weiAmount().toLong()
//        val signingInput = SigningInput.newBuilder()
//        signingInput.account = unsignedTx.from().address().data()
//        signingInput.amount = longValue
//        signingInput.destination = unsignedTx.toAddress().data()
//        signingInput.fee = getFee(unsignedTx)
//        signingInput.sequence = accountData.sequence.toInt()
//        signingInput.destinationTag = unsignedTx.tag()
//        signingInput.privateKey = ByteString.copyFrom(privateKey.data())
//
//        val signBytes = RippleSigner.sign(signingInput.build()).encoded.toByteArray()
//        return TransactionSign("", signBytes, unsignedTx)
//    }
}
