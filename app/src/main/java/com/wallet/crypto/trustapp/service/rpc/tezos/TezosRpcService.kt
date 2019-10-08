package com.wallet.crypto.trustapp.service.rpc.tezos

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wallet.crypto.trustapp.C
import io.reactivex.Single

import java.io.IOException
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import trust.blockchain.Slip
import trust.blockchain.blockchain.tezos.TezosFeeCalculator
import trust.blockchain.blockchain.tezos.TezosService
import trust.blockchain.blockchain.tezos.entity.*
import trust.blockchain.entity.Account
import trust.blockchain.entity.Address
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt

/* compiled from: TezosRpcService.kt */
class TezosRpcService @Inject
constructor(private val tezosRpcClient: TezosRpcClient, private val httpClient: OkHttpClient, private val gson: Gson) : TezosService() {

    private fun getTezosAccountBalance(address: Address): Long {
        return java.lang.Long.parseLong(getAccountData(address).balance)
    }

    private fun getTransactionStatus(r6: String, r7: Long): TezosResultPair {
        for (i in 0 .. 4) {
            val operations = tezosRpcClient.getBlockAtLevel(i + r7).execute().body()?.flatten() ?: throw IOException("RPC Error")
            for (operation in operations) {
                if (operation.hash == r6) {
                    var r0 = true
                    val contents = operation.contents
                    if (contents == null || !contents.isEmpty()) {
                        for (it in contents) {
                            val result = it.metadata.operationResult
                            if (result.status == "failed" || !result.errors.isNullOrEmpty()) {
                                r0 = false
                                break
                            }
                        }
                    }
                    return TezosResultPair(true, if (r0) "" else "FAILED")
                }
            }
        }
        return TezosResultPair(false, "")
    }

    private fun loadBalance(asset: Asset): Value {
        try {
            return Value(getTezosAccountBalance(asset.account.address()).toBigInteger())
        } catch (unused: Exception) {
            return asset.balance ?: Value(BigInteger.ZERO)
        }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            try {
                val asset = tx.asset()
                val feeCalculator = asset.coin().feeCalculator()
                val r0 = updateDefaultFee(feeCalculator, fetchSendType(tx.asset()), getTezosAccountBalance(tx.toAddress()) == 0L)
                if (r0 == 0L) {
                    throw IllegalStateException()
                }
                Fee(r0.toBigInteger(), true, 1, true, asset, asset)
            } catch (e : java.lang.Exception) {
                Fee((TezosFeeCalculator().defaultFee() * 2L + 277000).toBigInteger(), true, 1, true, tx.asset(), tx.asset())
            }
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun fetchHead(): TezosHead {
        return this.tezosRpcClient.ledgerHead.execute().body() ?: throw IOException("RPC Error")
    }

    override fun fetchSendType(asset: Asset): TezosSendType {
        val body = tezosRpcClient.getManageKey(asset.account.address().data()).execute().body() ?: throw IOException("RPC Error")
        return if (body.key.isNullOrEmpty()) TezosSendType.REVEAL_AND_TRANSFER else TezosSendType.TRANSFER
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable{
            val splite = hash.split("-")
            val status = getTransactionStatus(splite.get(0), java.lang.Long.parseLong(splite.get(1)))
            Transaction(hash,
                    status.getMessage(),
                    "0",
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    null,
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (status.getConfirmed()) Transaction.Status.COMPLETED else Transaction.Status.PENDING,
                    Transaction.Direction.OUT)
        }
    }

    override fun getAccountData(address: Address): TezosAccount {
        return tezosRpcClient.getAccountData(address.data()).execute().body() ?: throw IOException("RPC Error")
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable{
            val header = tezosRpcClient.getLedgerHead().execute().body()?.header ?: throw IOException("RPC Error")
            val blockNumber = header.level
            BlockInfo("", BigInteger.valueOf(blockNumber))
        }
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
        return Single.fromCallable{
            try {
                var path = '\"' + ExtensionsKt.toHex(signedMessage) + '\"' ?: throw TypeCastException("null cannot be cast to non-null type java.lang.String")
                val requestBody = RequestBody.create(C.f16600c, path.toByteArray(Charsets.UTF_8))
                val response = httpClient.newCall(Request.Builder().url(C.rpcUrl(account.coin) + "/injection/operation?chain=main").post(requestBody).build()).execute()
                val body = response.body()?.string()
                if (response == null || response.code() != 200) {
                    val errorList = try {
                        gson.fromJson<List<TezosError>>(body, object : TypeToken<List<TezosError>>(){}.type)
                    } catch (e : java.lang.Exception) {
                        throw IOException("Network error occurred")
                    }
                    throw Exception((errorList.first()).error)
                }

                body?.replace("\"", "")?.trim()
            } catch (e: Exception) {
                throw Exception(e.message)
            }

        }
    }
}
