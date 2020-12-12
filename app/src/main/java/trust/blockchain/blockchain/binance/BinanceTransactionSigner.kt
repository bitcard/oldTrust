package trust.blockchain.blockchain.binance

import com.google.protobuf.ByteString
import java.nio.charset.StandardCharsets
import kotlin.jvm.internal.Intrinsics
import org.json.JSONObject
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.binance.entity.BinanceAccount
import trust.blockchain.blockchain.binance.entity.NodeInfo
import trust.blockchain.entity.SwapDirection
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
//import wallet.core.jni.BinanceSigner
import wallet.core.jni.CoinType
//import wallet.core.jni.CosmosAddress
import wallet.core.jni.HDWallet
import wallet.core.jni.Hash
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Binance.SendOrder
import wallet.core.jni.proto.Binance.SendOrder.Input
import wallet.core.jni.proto.Binance.SendOrder.Output
import wallet.core.jni.proto.Binance.SendOrder.Token
import wallet.core.jni.proto.Binance.SigningInput
import wallet.core.jni.proto.Binance.TradeOrder

/* compiled from: BinanceTransactionSigner.kt */
class BinanceTransactionSigner(private val binanceRpcService: BinanceRpcService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.BINANCE)

//    private fun buildCoinAndTokenSign(unsignedTx: TransactionUnsigned, binanceAccount: BinanceAccount, privateKey: PrivateKey, nodeInfo: NodeInfo): ByteArray {
//        val binanceRpcService = this.binanceRpcService
//        val assetDenom = binanceRpcService.getAssetDenom(unsignedTx.asset())
//
//        val signingInput = SigningInput.newBuilder()
//        signingInput.chainId = nodeInfo.nodeInfo.chainId
//        signingInput.accountNumber = binanceAccount.account_number
//        signingInput.sequence = binanceAccount.sequence
//        signingInput.source = 2
//        signingInput.privateKey = ByteString.copyFrom(privateKey.data())
//        signingInput.memo = unsignedTx.memo()
//
//        val token = Token.newBuilder()
//        token.denom = assetDenom
//        token.amount = unsignedTx.weiAmount().toLong()
//        val sendInput = Input.newBuilder()
//        sendInput.address = ByteString.copyFrom(CosmosAddress(unsignedTx.from().address().data()).keyHash())
//        sendInput.addAllCoins(listOf(token.build()))
//
//        val output = Output.newBuilder()
//        output.address = ByteString.copyFrom(CosmosAddress(unsignedTx.recipientAddress().data()).keyHash())
//        output.addAllCoins(listOf(token.build()))
//
//        val newBuilder5 = SendOrder.newBuilder()
//        newBuilder5.addAllInputs(listOf(sendInput.build()))
//        newBuilder5.addAllOutputs(listOf(output.build()))
//        signingInput.sendOrder = newBuilder5.build()
//        return BinanceSigner.sign(signingInput.build()).encoded.toByteArray()
//    }
//
//    private fun buildTradeOrderSign(transactionUnsigned: TransactionUnsigned, binanceAccount: BinanceAccount, privateKey: PrivateKey, nodeInfo: NodeInfo): ByteArray {
//        val keyHash = CosmosAddress(transactionUnsigned.from().address().data()).keyHash()
//        var stringBuilder2: String? = ExtensionsKt.toHex(keyHash) + '-' + (binanceAccount.sequence + 1)
//        if (stringBuilder2 != null) {
//            stringBuilder2 = stringBuilder2.toUpperCase()
//            val swapPayload = transactionUnsigned.swapPayload()
//            val tradeOrder = TradeOrder.newBuilder()
//            tradeOrder.id = stringBuilder2
//            tradeOrder.quantity = (if (swapPayload.direction == SwapDirection.BUY) swapPayload.value else transactionUnsigned.weiAmount()).toLong()
//            tradeOrder.price = swapPayload.price.toLong()
//            tradeOrder.side = swapPayload.direction.value()
//            tradeOrder.ordertype = 2
//            tradeOrder.symbol = swapPayload.tradeSymbol
//            tradeOrder.timeinforce = 3
//            tradeOrder.sender = ExtensionsKt.toByteString(keyHash)
//
//            val signInput = SigningInput.newBuilder()
//            signInput.chainId = nodeInfo.nodeInfo.chainId
//            signInput.accountNumber = binanceAccount.account_number
//            signInput.sequence = binanceAccount.sequence
//            signInput.source = 2
//            Intrinsics.checkExpressionValueIsNotNull(signInput, "this")
//            signInput.privateKey = ExtensionsKt.toByteString(privateKey)
//            signInput.tradeOrder = tradeOrder.build()
//            return BinanceSigner.sign(signInput.build()).encoded.toByteArray()
//        }
//        throw TypeCastException("null cannot be cast to non-null type java.lang.String")
//    }

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return super.sign(hDWallet, unsignedTx)
//    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        val nodeInfo = this.binanceRpcService.getNodeInfo()
//        val accountData = this.binanceRpcService.getAccountData(unsignedTx.account().address().toString())
//        if (accountData != null) {
//            val buildTradeOrderSign: ByteArray
//            if (unsignedTx.type() != 1) {
//                if (unsignedTx.type() != 2) {
//                    if (unsignedTx.type() == 5) {
//                        buildTradeOrderSign = buildTradeOrderSign(unsignedTx, accountData, privateKey, nodeInfo)
//                        return TransactionSign(sign = buildTradeOrderSign, unsignedTx = unsignedTx)
//                    }
//                    throw IllegalArgumentException("Type not supported yet")
//                }
//            }
//            buildTradeOrderSign = buildCoinAndTokenSign(unsignedTx, accountData, privateKey, nodeInfo)
//            return TransactionSign(sign = buildTradeOrderSign, unsignedTx = unsignedTx)
//        }
//        throw IllegalStateException("Could not find account information")
//    }

    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
        val bArr = privateKey.sign(Hash.sha256(data), CoinType.BINANCE.curve()).dropLast(1).toByteArray()
        var publicKey = privateKey.getPublicKeySecp256k1(false)
        return JSONObject().put("privateKey", ExtensionsKt.toHex(publicKey.data())).put("signature", ExtensionsKt.toHex(bArr)).toString().toByteArray(StandardCharsets.UTF_8)
    }

    companion object {
        @JvmField val BINANCE_CHAIN_SOURCE: Long = 2
    }
}
