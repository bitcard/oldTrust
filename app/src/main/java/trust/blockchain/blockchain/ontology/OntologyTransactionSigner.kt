package trust.blockchain.blockchain.ontology

import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
import wallet.core.jni.HDWallet
//import wallet.core.jni.OntologySigner
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Ontology.SigningInput

/* compiled from: OntologyTransactionSigner.kt */
class OntologyTransactionSigner(private val rpcService: OntologyService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ONTOLOGY)

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val gasprice = this.rpcService.gasPrice.gasprice
//        val limitMax = unsignedTx.account().coin.feeCalculator().limitMax()
//        val longValue = (this.rpcService.estimateNonce(unsignedTx.account())!!.blockingGet() as Number).toLong().toInt()
//        val signInput = SigningInput.newBuilder()
//        signInput.contract = unsignedTx.asset().contract.unit.symbol
//        signInput.method = "transfer"
//        signInput.ownerPrivateKey = ExtensionsKt.toByteString(privateKey)
//        signInput.payerPrivateKey = ExtensionsKt.toByteString(privateKey)
//        signInput.toAddress = unsignedTx.recipientAddress().data()
//        signInput.amount = unsignedTx.weiAmount().toLong()
//        signInput.gasPrice = gasprice
//        signInput.gasLimit = limitMax
//        signInput.nonce = longValue
//        val sign = OntologySigner.sign(signInput.build()).encoded.toByteArray()
//        return TransactionSign("", sign, unsignedTx)
//    }
}
