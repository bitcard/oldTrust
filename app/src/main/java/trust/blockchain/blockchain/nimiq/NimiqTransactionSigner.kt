package trust.blockchain.blockchain.nimiq

import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
//import wallet.core.jni.NimiqSigner
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Nimiq.SigningInput

/* compiled from: NimiqTransactionSigner.kt */
class NimiqTransactionSigner(private val nimiqService: NimiqService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.NIMIQ)

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val display = unsignedTx.toAddress().display()
//        val slip = unsignedTx.account().coin
//        val longValue = unsignedTx.weiAmount().toLong()
//        val currentBlock = this.nimiqService.currentBlock.toInt()
//        val signInput = SigningInput.newBuilder()
//        signInput.value = longValue
//        signInput.destination = display
//        signInput.fee = slip.feeCalculator().defaultFee()
//        signInput.validityStartHeight = currentBlock
//        signInput.privateKey = ByteString.copyFrom(privateKey.data())
//        val signBytes = NimiqSigner.sign(signInput.build()).encoded.toByteArray()
//        return TransactionSign("", signBytes, unsignedTx)
//    }
}
