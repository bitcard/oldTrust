package com.wallet.crypto.trustapp.service.rpc.stellar

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientEnum
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientType
import io.reactivex.Single
import java.util.Arrays
import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import trust.blockchain.Slip
import trust.blockchain.blockchain.stellar.StellarService
import trust.blockchain.blockchain.stellar.entity.StellarAccount
import trust.blockchain.entity.*
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URLEncoder

/* compiled from: StellarRpcService.kt */
open class StellarRpcService @Inject
constructor(@param:StellarClientType(StellarClientEnum.STELLAR) private val stellarRpcClient: StellarRpcClient, private val httpClient: OkHttpClient, private val gson: Gson) : StellarService() {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.STELLAR)

    private fun loadBalance(r6: Asset): Value {
        try {
            val b = loadAccountData(r6).balances?.first {
                it.assetType == "native"
            }?.balance

            return Value(getFinalBalance(r6, BigDecimal(b!!)))
        } catch (e : Exception) {
            return r6.balance ?: Value(BigInteger.ZERO)
        }
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val asset = tx.asset()
            Fee(try {
                        BigInteger(stellarRpcClient.getFee().execute().body()!!.acceptedFee)
                    } catch (unused: Exception) {
                        BigInteger.valueOf(100)
                    }
                    , true, 1, true, asset, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val response = stellarRpcClient.getTransactionByHash(hash).execute()
            val body = response.body() ?: throw IOException("RPC Error. Code ${response.code()}")
            Transaction(body.hash,
                    "",
                    body.ledger.toString(),
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    body.feePaid.toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (account.coin === Slip.STELLAR && !body.successful) Transaction.Status.PENDING else Transaction.Status.COMPLETED,
                    Transaction.Direction.OUT)
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable{
            BlockInfo("", BigInteger.ZERO)
        }
    }

    override fun loadAccountData(account: Asset): StellarAccount {
        val response = httpClient.newCall(Request.Builder().url(C.rpcUrl(account.coin()) + "/accounts/" + account.account.address().data()).get().build()).execute()
        val body = response.body() ?: throw IOException("RPC Error. ${response.code()}")

        val account = try {
             gson.fromJson<StellarAccount>(body.charStream(), object : TypeToken<StellarAccount>() {}.type)
        } catch (e : Exception) {
            throw IOException("RPC Error. " + response.code())
        }

        if (!response.isSuccessful && account.detail.isNullOrEmpty()) {
            throw IOException("RPC Error. " + response.code())
        }

        if (response.isSuccessful) {
            return account
        }
        throw IOException(account.detail)
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val copyOf = Arrays.copyOf(assets, assets.size)
        val length = copyOf.size
        for (i in 0 until length) {
            val asset = copyOf[i]
            if (asset.type == 1 && asset.coin() === coin) {
                copyOf[i] = Asset(asset, loadBalance(asset))
            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val requestBody = RequestBody.create(C.f16602e, "tx=" + URLEncoder.encode(String(signedMessage, Charsets.UTF_8), "UTF-8"))
            try {
                val response = stellarRpcClient.broadcastTransaction(requestBody).execute()
                val body = response.body()
                val r2 = response.errorBody()?.string() ?: ""

                if (response.isSuccessful && (body == null || body.hash.isNullOrEmpty())) {
                    throw IOException("RPC Error")
                }

                if (!response.isSuccessful && r2.isNotEmpty()) {
                    throw IOException(try {
                        JSONObject(r2).getString("detail")
                    } catch (e: Exception) {
                        "RPC Error. Code: " + response.code()
                    })
                }

                if (!response.isSuccessful) {
                    throw IOException("RPC Error. Code: " + response.code())
                }

                if (body == null)
                    null
                else
                    body.hash
            } catch (e : Exception) {
                throw Exception(e.message)
            }
        }
    }
}
