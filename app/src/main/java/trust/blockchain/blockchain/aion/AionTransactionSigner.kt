package trust.blockchain.blockchain.aion

import com.google.protobuf.ByteString
import java.math.BigInteger
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
//import wallet.core.jni.AionSigner
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Aion.SigningInput

/* compiled from: AionTransactionSigner.kt */
class AionTransactionSigner : TransactionSigner {
    override val maintainedCoins: Array<Slip>
        get() =  arrayOf(Slip.AION)

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val signInput = SigningInput.newBuilder()
//        val weiAmount = unsignedTx.weiAmount()
//        val valueOf = BigInteger.valueOf(unsignedTx.nonce())
//        val price = unsignedTx.fee().price()
//        val valueOf2 = BigInteger.valueOf(unsignedTx.fee().limit())
//        signInput.amount = ByteString.copyFrom(weiAmount.toByteArray())
//        signInput.nonce = ByteString.copyFrom(valueOf.toByteArray())
//        signInput.gasPrice = ByteString.copyFrom(price.toByteArray())
//        signInput.gasLimit = ByteString.copyFrom(valueOf2.toByteArray())
//        signInput.toAddress = unsignedTx.toAddress().display()
//        signInput.payload = ByteString.copyFrom(ByteArray(0))
//        signInput.privateKey = ByteString.copyFrom(privateKey.data())
//        val toByteArray = AionSigner.sign(signInput.build()).encoded.toByteArray()
//        return TransactionSign("", toByteArray, unsignedTx)
//    }
}
