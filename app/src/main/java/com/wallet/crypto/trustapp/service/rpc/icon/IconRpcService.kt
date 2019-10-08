package com.wallet.crypto.trustapp.service.rpc.icon

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.service.rpc.icon.entity.IconBlock
import com.wallet.crypto.trustapp.service.rpc.icon.entity.IconResponse
import com.wallet.crypto.trustapp.service.rpc.icon.entity.IconTransactionResult
import io.reactivex.Single
import java.io.IOException
import java.lang.reflect.Type
import java.math.BigInteger
import java.util.Arrays
import java.util.HashMap
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.RequestBody
import org.json.JSONObject
import org.web3j.abi.datatypes.Address
import trust.blockchain.Slip
import trust.blockchain.entity.*
import trust.blockchain.util.Numbers

/* compiled from: IconRpcService.kt */
class IconRpcService @Inject
constructor(private val gson: Gson, private val httpClient: OkHttpClient) : trust.blockchain.blockchain.icon.IconRpcService() {

    private fun buildRequestBody(str: String, hashMap: HashMap<String, String>): RequestBody {
        val jSONObject = JSONObject()
        jSONObject.put("jsonrpc", "2.0")
        jSONObject.put("method", str)
        jSONObject.put("id", Math.abs(ThreadLocalRandom.current().nextInt()))
        jSONObject.put("params", JSONObject(hashMap as Map<String, String>))
        return RequestBody.create(C.f16599b, jSONObject.toString())
    }

    private fun loadTxInfo(str: String): IconTransactionResult {
        val request = Builder().url(C.rpcUrl(Slip.ICX)).post(buildRequestBody("icx_getTransactionByHash", hashMapOf("txHash".to(str)))).build()
        return request<IconTransactionResult>(request, object : TypeToken<IconResponse<IconTransactionResult>>() {}.type).result
    }

    private fun loadTxResult(str: String): IconTransactionResult {
        val request = Builder().url(C.rpcUrl(Slip.ICX)).post(buildRequestBody("icx_getTransactionResult", hashMapOf("txHash".to(str)))).build()
        return request<IconTransactionResult>(request, object : TypeToken<IconResponse<IconTransactionResult>>() {}.type).result
    }

    private fun <T> request(request: Request, type: Type): IconResponse<T> {
        val response = this.httpClient.newCall(request).execute()
        val body = response?.body() ?: throw IOException("Network Error")
        try {
            return gson.fromJson<IconResponse<T>>(body.charStream(), type)
        } catch (unused: Exception) {
            throw IOException("Response Error")
        }
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            try {
                val r0 = loadTxInfo(hash)
                val r2 = loadTxResult(hash)
                val r18 = if ("0x1" == r2.status) Transaction.Status.COMPLETED else Transaction.Status.PENDING

                val r3 = Numbers.INSTANCE.hexToBigInteger(r2.stepUsed) ?: BigInteger.ZERO
                val r6 = Numbers.INSTANCE.hexToBigInteger(r2.stepPrice) ?: BigInteger.ZERO

                val r14 = try {
                    val r10 = account.coin.coinAsset(account, true)
                    Fee(r6, r3.toLong(), r10, r10).calculateNetworkFee().toString()
                } catch (e: Exception) {
                    "0"
                }

                Transaction(hash,
                        r2.failure?.message ?: "",
                        Numbers.INSTANCE.hexToBigInteger(r0.blockHeight).toString(),
                        Numbers.INSTANCE.hexToBigInteger(r0.timestamp)?.toLong() ?: 0,
                        Numbers.INSTANCE.hexToBigInteger(r0.nonce)?.toInt() ?: 0,
                        Slip.ICX.toAddress(r0.from),
                        Slip.ICX.toAddress(r0.to),
                        SubunitValue(Value(Numbers.INSTANCE.hexToBigInteger(r0.value)), account.coin.unit()),
                        r14,
                        null,
                        Slip.ICX,
                        Transaction.Type.TRANSFER,
                        r18,
                        Transaction.Direction.OUT
                )
            } catch (e : Exception) {
                throw ServiceErrorException(-1, e.message, e)
            }
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable<BlockInfo> {
            val request = Builder().url(C.rpcUrl(Slip.ICX)).post(buildRequestBody("icx_getLastBlock", HashMap())).build()
            val iconBlock = request<IconBlock>(request, object : TypeToken<IconResponse<IconBlock>>() {}.type).getResult() as IconBlock
            BlockInfo(iconBlock.block_hash, BigInteger.valueOf(iconBlock.height), iconBlock.time_stamp.toLong(), iconBlock.merkle_tree_root_hash, iconBlock.prev_block_hash, "", 2, -1)
        }
    }

    override fun getChainId(tx: TransactionUnsigned): String {
        return BigInteger.valueOf(tx.asset().coin().chainId().toLong()).toString(16).toLowerCase()
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val assetArr2 = Arrays.copyOf(assets, assets.size)
        val length = assetArr2.size
        for (i in 0 until length) {
            val asset = assetArr2[i]
            assetArr2[i] = Asset(asset, try {
                val request = Builder().url(C.rpcUrl(Slip.ICX)).post(buildRequestBody("icx_getBalance", hashMapOf(Address.TYPE_NAME.to(asset.account.address().toString())))).build()
                var hexToBigInteger: BigInteger? = Numbers.INSTANCE.hexToBigInteger(request<String>(request, object : TypeToken<IconResponse<String>>() {}.type).result)
                Value(hexToBigInteger?:BigInteger.ZERO)
            } catch (unused: Exception) {
                asset.balance ?: Value(BigInteger.ZERO)
            })
        }
        return assetArr2
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val request = Builder().url(C.rpcUrl(Slip.ICX)).post(RequestBody.create(C.f16599b, signedMessage)).build()
            val response = request<String>(request, object : TypeToken<IconResponse<String>>() {}.type)
            if (response.getError() != null) {
                throw ServiceErrorException(1, response.getError().getMessage())
            }
            response.result
        }
    }
}
