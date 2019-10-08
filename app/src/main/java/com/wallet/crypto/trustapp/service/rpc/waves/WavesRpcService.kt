package com.wallet.crypto.trustapp.service.rpc.waves

//import com.wallet.crypto.trustapp.service.rpc.entity.RpcExtensions;
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.service.rpc.entity.RpcExtensions
import com.wallet.crypto.trustapp.service.rpc.waves.entity.WavesBalance
import io.reactivex.Single
import okhttp3.RequestBody
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.waves.WavesFeeCalculator
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value

/* compiled from: WavesRpcService.kt */
class WavesRpcService @Inject
constructor(private val rpcClient: WavesRpcClient) : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.WAVES)

    private fun getBalance(str: String): WavesBalance {
        return RpcExtensions.handleResponse(this.rpcClient.getBalance(str).execute())
    }

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val feeCalculator = tx.asset().coin().feeCalculator()
            val asset = tx.asset()
            Fee(BigInteger.ONE, feeCalculator.limitDef(0), asset, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val limitDef = WavesFeeCalculator().limitDef(0)
            val wavesTransactionInfo = RpcExtensions.handleResponse(rpcClient.getTransactionByHash(hash).execute())
            Transaction(hash,
                    "",
                    "0",
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    limitDef.toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    Transaction.Status.getState(wavesTransactionInfo.height == 0L),
                    Transaction.Direction.OUT)
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.just(BlockInfo("", 0))
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val copyOf = Arrays.copyOf(assets, assets.size)
        val length = copyOf.size
        for (i in 0 until length) {
            val asset = copyOf[i]
            if (asset.type == 1 && asset.coin() === coin) {
                copyOf[i] = Asset(asset, try {
                    Value(BigInteger.valueOf(getBalance(asset.account.address().data()).balance))
                } catch (unused: Exception) {
                    asset.balance ?: Value(BigInteger.ZERO)
                })
            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val requestBody = RequestBody.create(C.f16599b, signedMessage)
            try {
                val response = RpcExtensions.handleResponse(rpcClient.broadcastTransaction(requestBody).execute())
                if (response.id.isNullOrEmpty())
                    throw Exception("Failed to relay transaction")
                response.id
            } catch (e : Exception) {
                throw java.lang.Exception(e.message)
            }
        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just(String())
    }
}
