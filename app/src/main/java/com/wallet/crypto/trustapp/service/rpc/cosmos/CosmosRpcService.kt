package com.wallet.crypto.trustapp.service.rpc.cosmos

import com.wallet.crypto.trustapp.C
import io.reactivex.Single
import okhttp3.RequestBody
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.Arrays
import javax.inject.Inject
import org.json.JSONObject
import retrofit2.Response
import trust.blockchain.Slip
import trust.blockchain.blockchain.cosmos.CosmosService
import trust.blockchain.blockchain.cosmos.entity.CosmosAccount
import trust.blockchain.blockchain.cosmos.entity.CosmosDenom
import trust.blockchain.blockchain.cosmos.entity.CosmosNodeInfo
import trust.blockchain.entity.*
import java.math.BigDecimal
import java.math.BigInteger

/* compiled from: CosmosRpcService.kt */
class CosmosRpcService @Inject
constructor(private val cosmosRpcClient: CosmosRpcClient) : CosmosService() {

    override val nodeInfo: CosmosNodeInfo
        get() {
            return this.cosmosRpcClient.nodeInfo.execute().body() ?: throw IOException("RPC Error get node info")

        }

    private fun getBalance(r5: Asset): Value {
        try {
            val r1 = this.getAccountData(r5.account.address().display()).value.coins;
            if (r1 == null || r1.isEmpty()) {
                return Value(BigDecimal.valueOf(0))
            }


            return Value(BigDecimal(r1.first {
                it.denom == CosmosDenom.UATOM.value
            }.amount))
        } catch (e : Exception) {
            return r5.balance ?: Value(BigInteger.ZERO)
        }
    }

    private fun getJsonField(str: String, str2: String): String? {
        try {
            return JSONObject(str).getString(str2)
        } catch (unused: Exception) {
            return null
        }

    }

    private fun <T> handleResponse(response: Response<T>?): T {
        if (response == null) {
            throw IOException("Request|Response error")
        } else if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body
            }
            throw IOException("Incorrect server response")
        } else {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                var string: String? = errorBody.string()
                if (string != null) {
                    string = getJsonField(string, "message")
                    if (string != null) {
                        throw IOException(string)
                    }
                }
            }
            throw IOException("Unknown network error")
        }
    }

    private fun setRelayMode(bArr: ByteArray): ByteArray {
        val jsonSign = JSONObject(String(bArr, StandardCharsets.UTF_8))
        jsonSign.put("mode", "async")
        return jsonSign.toString().toByteArray(StandardCharsets.UTF_8)
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val feeCalculator = tx.asset().coin().feeCalculator()
            val asset = tx.asset()
            Fee(feeCalculator.priceMax(), feeCalculator.limitMax(), asset, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val cosmosTransaction = handleResponse(cosmosRpcClient.getTransactionByHash(hash).execute())
            val logs = cosmosTransaction.logs
            var obj = true
            if (logs is Collection<*> && (logs as Collection<*>).isEmpty()) {
                for (success in logs) {
                    if (!success.success) {
                        obj = false
                        break
                    }
                }
            }

            Transaction(hash,
                    "", cosmosTransaction.height,
                    0,
                    0, account.address(),
                    null,
                    null,
                    account.coin.feeCalculator().defaultFee().toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (obj && java.lang.Long.parseLong(cosmosTransaction.height) > 0) Transaction.Status.COMPLETED else Transaction.Status.PENDING,
                    Transaction.Direction.OUT)
        }
    }

    override fun getAccountData(address: String): CosmosAccount {
        return handleResponse(this.cosmosRpcClient.getAccountData(address).execute())
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable {
            BlockInfo("", 0)
        }
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val copyOf = Arrays.copyOf(assets, assets.size)
        val length = copyOf.size
        for (i in 0 until length) {
            val asset = copyOf[i]
            if (asset.type == 1 && asset.coin() === coin) {
                copyOf[i] = Asset(asset, getBalance(asset))
            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            try {
                val requestBody = RequestBody.create(C.f16599b, setRelayMode(signedMessage))
                val response = handleResponse(cosmosRpcClient.broadcastTransaction(requestBody).execute())
                response.txhash ?: throw Exception(response.error)

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}
