package trust.blockchain.blockchain.tron

import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import org.json.JSONArray
import org.json.JSONObject
import org.web3j.utils.Numeric
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
//import wallet.core.jni.TronSigner
import wallet.core.jni.proto.Tron.BlockHeader
import wallet.core.jni.proto.Tron.SigningInput
import wallet.core.jni.proto.Tron.SigningOutput
import wallet.core.jni.proto.Tron.Transaction
import wallet.core.jni.proto.Tron.TransferAssetContract
import wallet.core.jni.proto.Tron.TransferContract

/* compiled from: TronTransactionSigner.kt */
class TronTransactionSigner(private val tronRpcService: TronService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.TRX)

    private fun encodeJsonBytes(tx: TransactionUnsigned, output: SigningOutput, bArr: ByteArray, expiration: Long, timestamp: Long): ByteArray {
        var bArr = bArr
        val jsonObject = JSONObject()
        val raw_data = JSONObject()
        val jSONObject3 = JSONObject()
        val parameter = JSONObject()
        val value = JSONObject()
        var toHexStringNoPrefix = Numeric.toHexStringNoPrefix(bArr)

        val txID = ExtensionsKt.toHex(output.id.toByteArray())

        val signature = JSONArray()
        signature.put(toHexStringNoPrefix)
        jsonObject.put("signature", signature)
        jsonObject.put("txID", txID)
        jsonObject.put("raw_data", raw_data)
        value.put("amount", tx.weiAmount().toLong())

        value.put("owner_address", (tx.from().address() as TronAddress).hexValue())
        var bytes: ByteArray
        value.put("to_address", (tx.recipientAddress() as TronAddress).hexValue())
        parameter.put("value", value)
        jSONObject3.put("parameter", parameter)
        if (tx.type() == 2) {
            value.put("asset_name", Numeric.cleanHexPrefix(Numeric.toHexString(TronUtils.getTronTokenId(tx.asset()).toByteArray(Charsets.UTF_8))))
            parameter.put("type_url", "type.googleapis.com/protocol.TransferAssetContract")
            jSONObject3.put("type", "TransferAssetContract")
        } else if (tx.type() == 1) {
            parameter.put("type_url", "type.googleapis.com/protocol.TransferContract")
            jSONObject3.put("type", "TransferContract")
        } else {
            throw IllegalStateException("Not supported")
        }
        val contract = JSONArray()
        contract.put(jSONObject3)
        raw_data.put("contract", contract)
        raw_data.put("ref_block_bytes", ExtensionsKt.toHex(output.refBlockBytes.toByteArray()))
        raw_data.put("ref_block_hash", ExtensionsKt.toHex(output.refBlockHash.toByteArray()))
        raw_data.put("expiration", expiration)
        raw_data.put("timestamp", timestamp)
        bytes = jsonObject.toString().toByteArray(Charsets.UTF_8)
        return bytes
    }

    private fun getBlockHeader(blockInfo: BlockInfo): BlockHeader {
        val newBuilder = BlockHeader.newBuilder()
        newBuilder.number = blockInfo.number.toLong()
        var str = blockInfo.parentHash
        Intrinsics.checkExpressionValueIsNotNull(str, "blockInfo.parentHash")
        newBuilder.parentHash = ByteString.copyFrom(ExtensionsKt.toHexByteArray(str))
        newBuilder.timestamp = blockInfo.timestamp
        newBuilder.version = blockInfo.version
        str = blockInfo.witnessAddress
        Intrinsics.checkExpressionValueIsNotNull(str, "blockInfo.witnessAddress")
        newBuilder.witnessAddress = ByteString.copyFrom(ExtensionsKt.toHexByteArray(str))
        val str2 = blockInfo.txTrieRoot
        Intrinsics.checkExpressionValueIsNotNull(str2, "blockInfo.txTrieRoot")
        newBuilder.txTrieRoot = ByteString.copyFrom(ExtensionsKt.toHexByteArray(str2))
        return newBuilder.build()
    }

    private fun setTransferType(builder: Transaction.Builder, transactionUnsigned: TransactionUnsigned) {
        val longValue = transactionUnsigned.weiAmount().toLong()
        if (transactionUnsigned.type() == 1) {
            val newBuilder = TransferContract.newBuilder()
            Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this")
            newBuilder.amount = longValue
            newBuilder.ownerAddress = transactionUnsigned.from().address().display()
            newBuilder.toAddress = transactionUnsigned.toAddress().display()
            builder.transfer = newBuilder.build()
        } else if (transactionUnsigned.type() == 2) {
            val asset = transactionUnsigned.asset()
            Intrinsics.checkExpressionValueIsNotNull(asset, "unsignedTx.asset()")
            val tronTokenId = TronUtils.getTronTokenId(asset)
            val newBuilder2 = TransferAssetContract.newBuilder()
            Intrinsics.checkExpressionValueIsNotNull(newBuilder2, "this")
            newBuilder2.amount = longValue
            newBuilder2.ownerAddress = transactionUnsigned.from().address().display()
            newBuilder2.toAddress = transactionUnsigned.recipientAddress().data()
            newBuilder2.assetName = tronTokenId
            builder.transferAsset = newBuilder2.build()
        } else {
            throw IllegalStateException("Not supported")
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
//        val blockInfo = this.tronRpcService.blockInfo
//        Intrinsics.checkExpressionValueIsNotNull(blockInfo, "blockInfo")
//        val blockHeader = getBlockHeader(blockInfo)
//        val newBuilder = Transaction.newBuilder()
//        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this")
//        newBuilder.blockHeader = blockHeader
//        setTransferType(newBuilder, unsignedTx)
//        newBuilder.expiration = this.tronRpcService.getExpirationTime(blockInfo.timestamp)
//        newBuilder.timestamp = this.tronRpcService.getTransactionTimestamp(blockInfo.timestamp)
//        newBuilder.contractOneofCase
//        val build = newBuilder.build()
//        val newBuilder2 = SigningInput.newBuilder()
//        Intrinsics.checkExpressionValueIsNotNull(newBuilder2, "this")
//        newBuilder2.transaction = build
//        newBuilder2.privateKey = ByteString.copyFrom(privateKey.data())
//        val sign = TronSigner.sign(newBuilder2.build())
//        Intrinsics.checkExpressionValueIsNotNull(sign, "signingOutput")
//        val toByteArray = sign.signature.toByteArray()
//        val toByteArray2 = sign.id.toByteArray()
//        Intrinsics.checkExpressionValueIsNotNull(toByteArray2, "signingOutput.id.toByteArray()")
//        val toHex = ExtensionsKt.toHex(toByteArray2)
//        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "signBytes")
//        Intrinsics.checkExpressionValueIsNotNull(build, "transaction")
//        return TransactionSign(toHex, toByteArray, unsignedTx, encodeJsonBytes(unsignedTx, sign, toByteArray, build.expiration, build.timestamp))
//    }
}
