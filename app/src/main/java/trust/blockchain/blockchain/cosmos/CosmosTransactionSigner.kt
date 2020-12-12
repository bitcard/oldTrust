package trust.blockchain.blockchain.cosmos

import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.cosmos.entity.CosmosAccount
import trust.blockchain.blockchain.cosmos.entity.CosmosDenom
import trust.blockchain.blockchain.cosmos.entity.CosmosNodeInfo
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
//import wallet.core.jni.CosmosSigner
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Cosmos.Amount
import wallet.core.jni.proto.Cosmos.Amount.Builder
import wallet.core.jni.proto.Cosmos.Fee
//import wallet.core.jni.proto.Cosmos.SendCoinsMessage
import wallet.core.jni.proto.Cosmos.SigningInput
import wallet.core.jni.proto.Cosmos.SigningOutput

/* compiled from: CosmosTransactionSigner.kt */
class CosmosTransactionSigner(private val rpcService: CosmosService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.COSMOS)

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val display = unsignedTx.from().address().display()
//        val display2 = unsignedTx.recipientAddress()!!.display()
//        val cosmosService = this.rpcService
//        Intrinsics.checkExpressionValueIsNotNull(display, "from")
//        val accountData = cosmosService.getAccountData(display)
//        val nodeInfo = this.rpcService.nodeInfo
//        val newBuilder = Amount.newBuilder()
//        newBuilder.amount = unsignedTx.weiAmount().toLong()
//        newBuilder.denom = CosmosDenom.UATOM.value
//        val build = newBuilder.build()
//        val newBuilder2 = Amount.newBuilder()
//        newBuilder2.amount = unsignedTx.fee()!!.calculateNetworkFee().toLong()
//        newBuilder2.denom = CosmosDenom.UATOM.value
//        val build2 = newBuilder2.build()
//        val newBuilder3 = SendCoinsMessage.newBuilder()
//        newBuilder3.fromAddress = display
//        newBuilder3.toAddress = display2
//        newBuilder3.addAllAmounts(listOf(build))
//        val newBuilder4 = Fee.newBuilder()
//        newBuilder4.gas = unsignedTx.fee()!!.limit()
//        newBuilder4.addAllAmounts(listOf(build2))
//        val build3 = newBuilder4.build()
//        val newBuilder5 = SigningInput.newBuilder()
//        newBuilder5.accountNumber = java.lang.Long.parseLong(accountData.value.accountNumber)
//        newBuilder5.chainId = nodeInfo.network
//        newBuilder5.sequence = java.lang.Long.parseLong(accountData.value.sequence)
//        newBuilder5.fee = build3
//        newBuilder5.memo = unsignedTx.memo()
//        newBuilder5.setSendCoinsMessage(newBuilder3)
//        Intrinsics.checkExpressionValueIsNotNull(newBuilder5, "this")
//        newBuilder5.privateKey = ByteString.copyFrom(privateKey.data())
//        val sign = CosmosSigner.sign(newBuilder5.build())
//        Intrinsics.checkExpressionValueIsNotNull(sign, "sign")
//        val toByteArray = sign.encoded.toByteArray()
//        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "sign.encoded.toByteArray()")
//        val toByteArray2 = sign.jsonBytes.toByteArray()
//        Intrinsics.checkExpressionValueIsNotNull(toByteArray2, "sign.jsonBytes.toByteArray()")
//        return TransactionSign("", toByteArray, unsignedTx, toByteArray2)
//    }
}
