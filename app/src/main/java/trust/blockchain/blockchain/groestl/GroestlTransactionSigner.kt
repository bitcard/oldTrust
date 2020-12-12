package trust.blockchain.blockchain.groestl

import com.google.protobuf.ByteString
import java.math.BigInteger
import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.SigHashType
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.blockchain.bitcoin.BitcoinRpcService
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
import wallet.core.jni.BitcoinScript
//import wallet.core.jni.GroestlcoinTransactionSigner
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Bitcoin.SigningInput
import wallet.core.jni.proto.Bitcoin.SigningOutput
import wallet.core.jni.proto.Bitcoin.TransactionPlan
import wallet.core.jni.proto.Bitcoin.UnspentTransaction

/* compiled from: GroestlTransactionSigner.kt */
class GroestlTransactionSigner(private val rpcService: BitcoinRpcService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.GROESTL)

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(privateKey, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        val unspentOutput: UnspentOutput
//        var length: Int
//        val unspentOutputs = this.rpcService.getUnspentOutputs(unsignedTx)
//        SimpleFeeCalculator.checkMinimumFee(unsignedTx.fee().price().toLong())
//
//        val newBuilder = SigningInput.newBuilder()
//        newBuilder.hashType = SigHashType.ALL.value
//        newBuilder.amount = unsignedTx.weiAmount().toLong()
//        newBuilder.byteFee = unsignedTx.fee().price().toLong()
//        newBuilder.toAddress = unsignedTx.recipientAddress().data()
//        val bitcoinRpcService = this.rpcService
//        newBuilder.changeAddress = bitcoinRpcService.getCurrentChangeAddress(unsignedTx.from())
//        newBuilder.coinType = unsignedTx.asset().coin().coinType().value()
//        val arrayList = ArrayList<ByteString>(unspentOutputs.size)
//        for (unspentOutput2 in unspentOutputs) {
//            arrayList.add(ByteString.copyFrom(hDWallet.getKey(unspentOutput2.path).data()))
//        }
//        newBuilder.addAllPrivateKey(arrayList as List<ByteString>)
//        val arrayList2 = ArrayList<UnspentTransaction>(unspentOutputs.size)
//        val length2 = unspentOutputs.size
//        var i = 0
//        var i2 = i
//        while (i < length2) {
//            var unspentOutput2 = unspentOutputs[i]
//            val i3 = i2 + 1
//            val newBuilder2 = UnspentTransaction.newBuilder()
//            length = Integer.MAX_VALUE - unspentOutputs.size + i2
//            val redeemScript = BitcoinScript.buildForAddress(unspentOutput2.address, unsignedTx.asset().coin().coinType()).data()
//            newBuilder2.script = ExtensionsKt.toByteString(redeemScript)
//            newBuilder2.amount = java.lang.Long.parseLong(unspentOutput2.value)
//            val outPointBuilder = newBuilder2.outPointBuilder
//            outPointBuilder.setHash(ExtensionsKt.hextoByteString(unspentOutput2.txid))
//            outPointBuilder.setIndex(unspentOutput2.vout)
//            outPointBuilder.setSequence(length)
//            val build = outPointBuilder.build()
//            Intrinsics.checkExpressionValueIsNotNull(newBuilder2, "this")
//            newBuilder2.setOutPoint(build)
//            arrayList2.add(newBuilder2.build())
//            i++
//            i2 = i3
//        }
//        val iterable = arrayList2 as List<UnspentTransaction>
//        newBuilder.addAllUtxo(iterable)
//        val longValue = unsignedTx.weiAmount().toLong()
//        val longValue2 = unsignedTx.fee().calculateNetworkFee().toLong()
//        val arrayList3 = ArrayList<BigInteger>(unspentOutputs.size)
//        for (value in unspentOutputs) {
//            arrayList3.add(BigInteger(value.value))
//        }
//        val it = (arrayList3 as List<BigInteger>).iterator()
//        if (it.hasNext()) {
//            var next = it.next()
//            while (it.hasNext()) {
//                next = next.add(it.next())
//                Intrinsics.checkExpressionValueIsNotNull(next, "sum.add(item)")
//            }
//            Intrinsics.checkExpressionValueIsNotNull(next, "unspentOutputs\n         …, item -> sum.add(item) }")
//            val bigInteger = next
//            val signingOutput = GroestlcoinTransactionSigner(newBuilder.build(), TransactionPlan.newBuilder().addAllUtxos(iterable).setAvailableAmount(bigInteger.toLong()).setAmount(longValue).setFee(longValue2).setChange(bigInteger.toLong() - (longValue + longValue2)).build()).sign().getObjects(0).unpack(SigningOutput::class.java) as SigningOutput
//            Intrinsics.checkExpressionValueIsNotNull(signingOutput, "output")
//            val transactionId = signingOutput.transactionId
//            val toByteArray = signingOutput.encoded.toByteArray()
//            Intrinsics.checkExpressionValueIsNotNull(transactionId, "txId")
//            Intrinsics.checkExpressionValueIsNotNull(toByteArray, "encoded")
//            return TransactionSign(transactionId, toByteArray, unsignedTx)
//        }
//        throw UnsupportedOperationException("Empty collection can't be reduced.")
//    }
}
