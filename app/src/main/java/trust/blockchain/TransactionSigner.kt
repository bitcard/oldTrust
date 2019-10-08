package trust.blockchain

import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import wallet.core.jni.HDWallet
import wallet.core.jni.PrivateKey

/* compiled from: TransactionSigner.kt */
interface TransactionSigner : CoinService {

    fun sign(hDWallet: HDWallet, unsignedTx: TransactionUnsigned): TransactionSign {
            val privateKey = hDWallet.getKeyForCoin(unsignedTx.account().coin.coinType())
            return sign(privateKey, unsignedTx)
    }

    fun sign(privateKey: PrivateKey, unsignedTx: TransactionUnsigned): TransactionSign {
        return TransactionSign(unsignedTx = unsignedTx)
    }

    fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
        return ByteArray(0)
    }
}
