package trust.blockchain.blockchain.zcash

import com.google.protobuf.ByteString
import java.math.BigInteger
import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import org.spongycastle.util.encoders.Hex
import org.web3j.utils.Numeric
import trust.blockchain.SigHashType
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.blockchain.bitcoin.BitcoinRpcService
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.BitcoinScript
import wallet.core.jni.HDWallet
import wallet.core.jni.Hash
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Bitcoin.OutPoint
import wallet.core.jni.proto.Bitcoin.SigningInput
import wallet.core.jni.proto.Bitcoin.SigningInput.Builder
import wallet.core.jni.proto.Bitcoin.SigningOutput
import wallet.core.jni.proto.Bitcoin.TransactionPlan
import wallet.core.jni.proto.Bitcoin.UnspentTransaction

/* compiled from: ZcashTransactionSigner.kt */
class ZcashTransactionSigner(private val bitcoinRpcService: BitcoinRpcService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ZCASH, Slip.ZELCASH)

//    private fun buildSignInput(unsignedTx: TransactionUnsigned, hDWallet: HDWallet, bitcoinRpcService: BitcoinRpcService): Pair<Builder, TransactionPlan.Builder> {
//        val unspentOutput: UnspentOutput
//        var buildForAddress: BitcoinScript
//        val unspentOutputs = bitcoinRpcService.getUnspentOutputs(unsignedTx)
//        val coinType = unsignedTx.asset().coin().coinType()
//        val newBuilder = SigningInput.newBuilder()
//        newBuilder.amount = unsignedTx.weiAmount().toLong()
//        newBuilder.hashType = SigHashType.ALL.value
//        newBuilder.toAddress = unsignedTx.toAddress().data()
//        newBuilder.changeAddress = bitcoinRpcService.getCurrentChangeAddress(unsignedTx.from())
//        newBuilder.byteFee = unsignedTx.fee().price().toLong()
//        newBuilder.coinType = coinType.value()
//        var arrayList: MutableCollection<ByteString> = ArrayList(unspentOutputs.size)
//        var i = 0
//        for (unspentOutput2 in unspentOutputs) {
//            arrayList.add(ByteString.copyFrom(hDWallet.getKey(unspentOutput2.path).data()))
//        }
//        newBuilder.addAllPrivateKey(arrayList as List<ByteString>)
//
//        val arrayList2 = ArrayList<UnspentTransaction>(unspentOutputs.size)
//        var length = unspentOutputs.size
//        var i2 = 0
//        var i3 = i2
//        while (i2 < length) {
//            var unspentOutput2 = unspentOutputs[i2]
//            val i4 = i3 + 1
//            val length2 = Integer.MAX_VALUE - unspentOutputs.size + i3
//            buildForAddress = BitcoinScript.buildForAddress(unspentOutput2.address, coinType)
//            val newBuilder2 = OutPoint.newBuilder()
//            newBuilder2.hash = ByteString.copyFrom(Hex.decode(unspentOutput2.txid))
//            newBuilder2.index = unspentOutput2.vout
//            newBuilder2.sequence = length2
//            val build = newBuilder2.build()
//            val newBuilder3 = UnspentTransaction.newBuilder()
//            newBuilder3.amount = java.lang.Long.parseLong(unspentOutput2.value)
//            newBuilder3.outPoint = build
//            newBuilder3.script = ByteString.copyFrom(buildForAddress.data())
//            arrayList2.add(newBuilder3.build())
//            i2++
//            i3 = i4
//        }
//
//        val list = arrayList2 as List<UnspentTransaction>
//        var arrayList3 = ArrayList<BigInteger>(unspentOutputs.size)
//        for (unspentOutput22 in unspentOutputs) {
//            arrayList3.add(BigInteger(unspentOutput22.value))
//        }
//        val it = (arrayList3 as List<*>).iterator()
//        if (it.hasNext()) {
//            var next = it.next()
//            while (it.hasNext()) {
//                next = (next as BigInteger).add(it.next() as BigInteger)
//                Intrinsics.checkExpressionValueIsNotNull(next, "sum.add(item)")
//            }
//            Intrinsics.checkExpressionValueIsNotNull(next, "unspentOutputs.map { Big…, item -> sum.add(item) }")
//            val bigInteger = next as BigInteger
//            length = unspentOutputs.size
//            while (i < length) {
//                buildForAddress = BitcoinScript.buildForAddress(unspentOutputs[i].address, coinType)
//                newBuilder.putScripts(Numeric.toHexString(buildForAddress.matchPayToPubkeyHash()), ByteString.copyFrom(buildForAddress.data()))
//                i++
//            }
//            val longValue = unsignedTx.weiAmount().toLong()
//            val longValue2 = unsignedTx.fee().calculateNetworkFee().toLong()
//            return Pair(newBuilder, TransactionPlan.newBuilder().addAllUtxos(list).setAvailableAmount(bigInteger.toLong()).setAmount(longValue).setFee(longValue2).setChange(bigInteger.toLong() - (longValue + longValue2)))
//        }
//        throw UnsupportedOperationException("Empty collection can't be reduced.")
//    }
//
////    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
////        return sign(privateKey, unsignedTx)
////    }
////
////    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
////        return sign(privateKey, data)
////    }
//
//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        SimpleFeeCalculator.checkMinimumFee(unsignedTx.fee().price().toLong())
//        val buildSignInput = buildSignInput(unsignedTx, hDWallet, this.bitcoinRpcService)
//        val output = wallet.core.jni.ZcashTransactionSigner(buildSignInput.component1().build(), buildSignInput.component2().build()).sign().getObjects(0).unpack(SigningOutput::class.java) as SigningOutput
//        val sign = output.encoded.toByteArray()
//        var sha256SHA256 = Hash.sha256SHA256(sign).reversedArray()
//        val txId = Numeric.toHexString(sha256SHA256, 0, sha256SHA256.size, false)
//        return TransactionSign(txId, sign, unsignedTx)
//    }
}
