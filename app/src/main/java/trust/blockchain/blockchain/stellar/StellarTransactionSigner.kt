package trust.blockchain.blockchain.stellar

import android.text.TextUtils
import com.google.protobuf.ByteString
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.entity.Account
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey
import wallet.core.jni.StellarPassphrase
//import wallet.core.jni.StellarSigner
import wallet.core.jni.proto.Stellar.MemoText
import wallet.core.jni.proto.Stellar.SigningInput
import wallet.core.jni.proto.Stellar.SigningInput.Builder
import wallet.core.jni.proto.Stellar.SigningInput.OperationType

/* compiled from: StellarTransactionSigner.kt */
class StellarTransactionSigner(private val stellarRpcService: StellarService) : TransactionSigner {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.STELLAR, Slip.KIN)


    private fun getFee(transactionUnsigned: TransactionUnsigned): Int {
        try {
            return transactionUnsigned.fee().calculateNetworkFee().toInt()
        } catch (unused: Exception) {
            return 1000
        }

    }

    private fun getOperationType(transactionUnsigned: TransactionUnsigned): OperationType {
        try {
            val coin = transactionUnsigned.asset().coin()
            val account = Account(coin, "", transactionUnsigned.toAddress().data())
            val stellarService = this.stellarRpcService
            val balances = stellarService.loadAccountData(coin.coinAsset(account, true)).balances
            if (balances != null) {
                if (!balances.isEmpty()) {
                    return OperationType.PAYMENT
                }
            }
            return OperationType.CREATE_ACCOUNT
        } catch (unused: Exception) {
            return OperationType.CREATE_ACCOUNT
        }

    }

//    private fun getSigningBytes(unsignedTx: TransactionUnsigned, privateKey: PrivateKey, operationType: OperationType): ByteArray {
//        val stellarPassphrase: StellarPassphrase
//        val slip = unsignedTx.account().coin
//        val longValue = unsignedTx.weiAmount().toLong()
//        val stellarService = this.stellarRpcService
//        val parseLong = java.lang.Long.parseLong(stellarService.loadAccountData(unsignedTx.asset()).sequence) + 1
//        if (slip == Slip.STELLAR) {
//            stellarPassphrase = StellarPassphrase.STELLAR
//        } else {
//            stellarPassphrase = StellarPassphrase.KIN
//        }
//        val signInput = SigningInput.newBuilder()
//        signInput.passphrase = stellarPassphrase.toString()
//        signInput.account = unsignedTx.from().address().data()
//        signInput.amount = longValue
//        signInput.destination = unsignedTx.toAddress().data()
//        signInput.fee = getFee(unsignedTx)
//        signInput.sequence = parseLong
//        signInput.privateKey = ByteString.copyFrom(privateKey.data())
//        setMemo(signInput, unsignedTx.memo())
//        signInput.operationType = operationType
//        signInput.operationTypeValue = operationType.number
//
//        return StellarSigner.sign(signInput.build()).signatureBytes.toByteArray()
//    }

    private fun setMemo(builder: Builder, str: String) {
        if (TextUtils.isEmpty(str)) {
            builder.setMemoVoid(builder.memoVoidBuilder.build())
        } else {
            builder.memoText = MemoText.newBuilder().setText(str).build()
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
//        return TransactionSign("", getSigningBytes(unsignedTx, privateKey, getOperationType(unsignedTx)), unsignedTx)
//    }
}
