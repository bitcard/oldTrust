package trust.blockchain.blockchain.bitcoin

import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import org.spongycastle.util.encoders.Hex
import org.web3j.utils.Numeric
import trust.blockchain.SigHashType
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
import wallet.core.jni.BitcoinScript
import wallet.core.jni.HDWallet
import wallet.core.jni.Hash
import wallet.core.jni.PrivateKey
import wallet.core.jni.proto.Bitcoin.OutPoint
import wallet.core.jni.proto.Bitcoin.SigningInput
import wallet.core.jni.proto.Bitcoin.SigningOutput
import wallet.core.jni.proto.Bitcoin.UnspentTransaction

/* compiled from: BitcoinTransactionSigner.kt */
class BitcoinTransactionSigner(private val bitcoinRpcService: BitcoinRpcService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.BTC, Slip.LTC, Slip.BCH, Slip.DASH, Slip.ZCOIN, Slip.DOGECOIN, Slip.QTUM, Slip.VIACOIN, Slip.RAVEN)

    private fun retargetAmount(j: Long, j2: Long, transactionUnsigned: TransactionUnsigned): Long {
        try {
            val longValue = transactionUnsigned.fee().calculateNetworkFee().toLong()
            return if (j < j2 + longValue) j - longValue else j2
        } catch (unused: Exception) {
            return j2
        }

    }

//    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(privateKey, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
        val slip = unsignedTx.account().coin
        val longValue = unsignedTx.fee().price().toLong()
        SimpleFeeCalculator.checkMinimumFee(longValue)
        val longValue2 = unsignedTx.weiAmount().toLong()
        val filterOutDusts = ExtensionsKt.filterOutDusts(this.bitcoinRpcService.getUnspentOutputs(unsignedTx).toList(), longValue)
        var toArray: Array<UnspentOutput> = filterOutDusts.toTypedArray()
        if (toArray != null) {
            val value: Int
            var i: Int
            val retargetAmount = retargetAmount(ExtensionsKt.sumValue(toArray.toList()), longValue2, unsignedTx)
            if (slip == Slip.BCH) {
                value = SigHashType.FORK.value or SigHashType.ALL.value
            } else {
                value = SigHashType.ALL.value
            }
            val toAddress = SigningInput.newBuilder().setAmount(retargetAmount).setHashType(value).setToAddress(unsignedTx.toAddress().data())
            val bitcoinRpcService = this.bitcoinRpcService
            val coinType = toAddress.setChangeAddress(bitcoinRpcService.getCurrentChangeAddress(unsignedTx.from())).setByteFee(longValue).setCoinType(slip.coinType().value())
            for (path in toArray!!) {
                coinType.addPrivateKey(ByteString.copyFrom(hDWallet.getKey(path.path).data()))
            }
            for (unspentOutput2 in toArray) {
                val matchPayToWitnessPublicKeyHash: ByteArray
                val redeemScript = BitcoinScript.buildForAddress(unspentOutput2.address, slip.coinType())
                if (redeemScript.isPayToWitnessScriptHash) {
                    matchPayToWitnessPublicKeyHash = redeemScript.matchPayToWitnessPublicKeyHash()
                } else {
                    matchPayToWitnessPublicKeyHash = redeemScript.matchPayToPubkeyHash()
                }
                coinType.putScripts(Numeric.toHexString(matchPayToWitnessPublicKeyHash), ByteString.copyFrom(redeemScript.data()))
            }
            val length = toArray.size
            for (i in 0 until length) {
                var unspentOutput2 = toArray[i]
                coinType.addUtxo(UnspentTransaction.newBuilder()
                        .setAmount(java.lang.Long.parseLong(unspentOutput2.value))
                        .setOutPoint(OutPoint.newBuilder()
                                .setHash(ByteString.copyFrom(Hex.decode(unspentOutput2.txid)))
                                .setIndex(unspentOutput2.vout)
                                .setSequence((java.lang.Long.MAX_VALUE - toArray.size.toLong() + i.toLong()).toInt())
                                .build())
                        .setScript(ByteString.copyFrom(BitcoinScript.buildForAddress(toArray[i].address, slip.coinType()).data())).build())
            }
            val output = wallet.core.jni.BitcoinTransactionSigner(coinType.build()).sign().getObjects(0).unpack(SigningOutput::class.java) as SigningOutput
            val sign = output.encoded.toByteArray()
            var sha256SHA256 = Hash.sha256SHA256(sign).reversedArray()
            val txId = Numeric.toHexString(sha256SHA256, 0, sha256SHA256.size, false)
            Intrinsics.checkExpressionValueIsNotNull(txId, "txId")
            Intrinsics.checkExpressionValueIsNotNull(sign, "sign")
            return TransactionSign(txId, sign, unsignedTx)
        }
        throw TypeCastException("null cannot be cast to non-null type kotlin.Array<T>")
    }
}
