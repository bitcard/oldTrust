package trust.blockchain.blockchain.tezos

import com.google.protobuf.ByteString
import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.blockchain.tezos.entity.TezosSendType
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.TezosSigner
import wallet.core.jni.proto.Tezos.Operation
import wallet.core.jni.proto.Tezos.Operation.OperationKind
import wallet.core.jni.proto.Tezos.OperationList
import wallet.core.jni.proto.Tezos.RevealOperationData
import wallet.core.jni.proto.Tezos.SigningInput
import wallet.core.jni.proto.Tezos.TransactionOperationData

/* compiled from: TezosTransactionSigner.kt */
class TezosTransactionSigner(private val tezosRpcService: TezosService) : TransactionSigner {
    val feeCalculator = TezosFeeCalculator()

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.TEZOS)

    private fun revealOperation(bArr: ByteArray, j: Long, transactionUnsigned: TransactionUnsigned): Operation {
        val newBuilder = RevealOperationData.newBuilder()
        newBuilder.publicKey = ByteString.copyFrom(bArr)
        val build = newBuilder.build()
        val newBuilder2 = Operation.newBuilder()
        newBuilder2.source = transactionUnsigned.from().address().data()
        newBuilder2.fee = this.feeCalculator.defaultFee()
        newBuilder2.counter = j
        newBuilder2.gasLimit = this.feeCalculator.limitMax()
        newBuilder2.storageLimit = 0
        newBuilder2.kind = OperationKind.REVEAL
        newBuilder2.revealOperationData = build
        val build2 = newBuilder2.build()
        Intrinsics.checkExpressionValueIsNotNull(build2, "Tezos.Operation.newBuild…lOpData\n        }.build()")
        return build2
    }

    private fun transferOperation(j: Long, transactionUnsigned: TransactionUnsigned): Operation {
        val newBuilder = TransactionOperationData.newBuilder()
        newBuilder.amount = transactionUnsigned.weiAmount().toLong()
        newBuilder.destination = transactionUnsigned.toAddress().data()
        val build = newBuilder.build()
        val newBuilder2 = Operation.newBuilder()
        newBuilder2.source = transactionUnsigned.from().address().data()
        newBuilder2.fee = this.feeCalculator.defaultFee()
        newBuilder2.counter = j
        newBuilder2.gasLimit = if (transactionUnsigned.shouldMaxAmount()) this.feeCalculator.limitMax() else this.feeCalculator.limitDef(transactionUnsigned.type())
        newBuilder2.storageLimit = 257
        newBuilder2.kind = OperationKind.TRANSACTION
        newBuilder2.transactionOperationData = build
        val build2 = newBuilder2.build()
        Intrinsics.checkExpressionValueIsNotNull(build2, "Tezos.Operation.newBuild…OpData\n\n        }.build()")
        return build2
    }

//    override fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
//        return sign(hDWallet, unsignedTx)
//    }
//
//    override fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
//        return sign(privateKey, data)
//    }

    override fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
        val listOf: List<Operation>
        val asset = unsignedTx.asset()
        val publicKeyEd25519 = privateKey.publicKeyEd25519
        val tezosService = this.tezosRpcService
        val address = asset.account.address()
        Intrinsics.checkExpressionValueIsNotNull(address, "asset.account.address()")
        val counter = tezosService.getAccountData(address).counter
        val tezosService2 = this.tezosRpcService
        Intrinsics.checkExpressionValueIsNotNull(asset, "asset")
        val fetchSendType = tezosService2.fetchSendType(asset)
        val fetchHead = this.tezosRpcService.fetchHead()
        unsignedTx.blockInfo(BlockInfo("", BigInteger.valueOf(fetchHead.header.level)))

        when (fetchSendType) {
            TezosSendType.TRANSFER -> listOf = listOf(transferOperation(counter + 1, unsignedTx))
            TezosSendType.REVEAL_AND_TRANSFER -> {
                listOf = listOf(revealOperation(publicKeyEd25519.data(), 1 + counter, unsignedTx), transferOperation(counter + 2, unsignedTx))
            }
        }
        val newBuilder = OperationList.newBuilder()
        newBuilder.addAllOperations(listOf)
        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this")
        newBuilder.branch = fetchHead.hash
        val build = newBuilder.build()
        val signInput = SigningInput.newBuilder()
        signInput.operationList = build
        Intrinsics.checkExpressionValueIsNotNull(signInput, "this")
        signInput.privateKey = ByteString.copyFrom(privateKey.data())
        val toByteArray = TezosSigner.sign(signInput.build()).signedBytes.toByteArray()
        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "sign")
        return TransactionSign("", toByteArray, unsignedTx)
    }
}
