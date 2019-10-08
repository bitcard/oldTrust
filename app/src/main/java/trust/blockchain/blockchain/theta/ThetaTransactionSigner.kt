package trust.blockchain.blockchain.theta

import com.google.protobuf.ByteString
import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.ThetaSigner
import wallet.core.jni.proto.Theta.SigningInput

/* compiled from: ThetaTransactionSigner.kt */
class ThetaTransactionSigner(private val thetaRpcService: ThetaService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.THETA)

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

    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
        val thetaService = this.thetaRpcService
        val address = unsignedTx.from().address()
        Intrinsics.checkExpressionValueIsNotNull(address, "unsignedTx.from().address()")
        val accountData = thetaService.getAccountData(address)
        val valueOf = BigInteger.valueOf(getFee(unsignedTx))
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this)")
        val copyFrom = ByteString.copyFrom(unsignedTx.weiAmount().toByteArray())
        val newBuilder = SigningInput.newBuilder()
        newBuilder.chainId = "mainnet"
        newBuilder.sequence = accountData.sequence + 1
        newBuilder.fee = ByteString.copyFrom(valueOf.toByteArray())
        newBuilder.toAddress = unsignedTx.recipientAddress().display()
        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this")
        newBuilder.privateKey = ByteString.copyFrom(privateKey.data())
        if (unsignedTx.type() == 1) {
            newBuilder.thetaAmount = copyFrom
        } else {
            newBuilder.tfuelAmount = copyFrom
        }
        val signingOutput = ThetaSigner.sign(newBuilder.build())
        val signature = signingOutput.encoded.toByteArray()
        Intrinsics.checkExpressionValueIsNotNull(signature, "signature")
        return TransactionSign("", signature, unsignedTx)
    }
}
