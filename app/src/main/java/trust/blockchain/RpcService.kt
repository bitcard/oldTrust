package trust.blockchain

import java.io.IOException
import io.reactivex.Single
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned

interface RpcService : CoinService {
    fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray>

    fun estimateFee(tx: TransactionUnsigned): Single<Fee>

    fun estimateNonce(account: Account): Single<Long>

    fun findTransaction(account: Account, hash: String): Single<Transaction>

    @Throws(IOException::class)
    fun getBlockNumber(coin: Slip): Single<BlockInfo>

    fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset>

    fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String>

    fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String>
}
