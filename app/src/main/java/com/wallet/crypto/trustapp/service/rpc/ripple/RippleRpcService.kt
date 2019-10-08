package com.wallet.crypto.trustapp.service.rpc.ripple

import com.google.gson.Gson
import com.google.gson.JsonObject

import io.reactivex.Single

import java.util.Arrays
import javax.inject.Inject

import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.Slip
import trust.blockchain.blockchain.ripple.RippleService
import trust.blockchain.blockchain.ripple.entity.RippleAccountData
import trust.blockchain.blockchain.ripple.entity.RippleModelsKt
import trust.blockchain.blockchain.ripple.entity.RippleRequestData
import trust.blockchain.entity.*
import trust.blockchain.util.ExtensionsKt
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger

/* compiled from: RippleRpcService.kt */
class RippleRpcService @Inject
constructor(private val rippleRpcClient: RippleRpcClient, private val gson: Gson) : RippleService() {

    private fun buildAccountRequestData(str: String): JsonObject {
        return RippleModelsKt.toJson(RippleRequestData("account_info", listOf(mapOf("account" to str, "strict" to true, "ledger_index" to "current", "queue" to true))), this.gson)
    }

    private fun buildBroadcastData(str: String): JsonObject {
        return RippleModelsKt.toJson(RippleRequestData("submit", listOf(mapOf("tx_blob" to str))), this.gson)
    }

    private fun buildFeeRequestData(): JsonObject {
        return RippleModelsKt.toJson(RippleRequestData("fee", listOf(emptyMap())), this.gson)
    }

    private fun buildLedgerQueryData(): JsonObject {
        return RippleModelsKt.toJson(RippleRequestData("ledger", listOf(mapOf("ledger_index" to "validated"))), this.gson)
    }

    private fun buildTxQueryData(str: String): JsonObject {
        return RippleModelsKt.toJson(RippleRequestData("tx", listOf(mapOf("transaction" to str))), this.gson)
    }

    private fun loadBalance(asset: Asset): Value {
        try {
            return Value(BigDecimal(getAccountData(asset.account.address().data()).balance).subtract(reserveBalance).max(BigDecimal(0)))
        } catch (unused: Exception) {
            return asset.balance ?: Value(BigInteger.ZERO)
        }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val asset = tx.asset()
            Fee(try {
                    val result = rippleRpcClient.getFeeEstimate(buildFeeRequestData()).execute().body()?.result ?: throw IOException()
                    BigInteger(result.drops.medianFee)
                } catch (unused: Exception) {
                    BigInteger.valueOf(5000)
                },
                    true, 1, true, asset, asset)

        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val result = rippleRpcClient.getTransactionById(buildTxQueryData(hash)).execute().body()?.result ?: throw IOException()
            Transaction(hash,
                    "",
                    result.ledgerIndex.toString(),
                    0,
                    0,
                    account.address(),
                    account.coin.toAddress(result.destination),
                    null,
                    result.fee,
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (result.validated) Transaction.Status.COMPLETED else Transaction.Status.PENDING,
                    Transaction.Direction.OUT)
        }
    }

    override fun getAccountData(r4: String): RippleAccountData {
        val body = rippleRpcClient.getAccountData((buildAccountRequestData(r4))).execute().body() ?: throw IOException("RPC Error")

        if (!body.error.isNullOrEmpty()) {
            throw IOException(body.error)
        }

        if (!body.result.errorMessage.isNullOrEmpty()) {
            throw IOException(body.result.errorMessage)
        }

        return body.result.accountData
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable {
            val body = rippleRpcClient.getLedgerInfo(buildLedgerQueryData()).execute().body() ?: throw IOException("RPC Error")

            if (!body.error.isNullOrEmpty()) {
                throw IOException(body.error)
            }

            if (!body.result.errorMessage.isNullOrEmpty()) {
                throw IOException(body.result.errorMessage)
            }

            val index = body.result.ledgerIndex
            BlockInfo("", BigInteger.valueOf(index))
        }
    }

    override fun isAccountActivated(address: String): Boolean {
        try {
            getAccountData(address).balance
            return true
        } catch (unused: Exception) {
            return false
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
        return Single.fromCallable {
            try {
                val body = rippleRpcClient.broadCastTransaction(buildBroadcastData(ExtensionsKt.toHex(signedMessage))).execute().body() ?: throw IOException("RPC Error")

                if (!body.error.isNullOrEmpty()) {
                    throw IOException(body.error)
                }

                if (!body.result.errorMessage.isNullOrEmpty()) {
                    throw IOException(body.result.errorMessage)
                }

                val result = body.result
                val hash = result.transactionJson.hash
                if (result.engineResultCode == 0 && hash.isNotEmpty()) {
                    throw IOException(result.engineResultMessage)
                }

                hash
            } catch (e : java.lang.Exception) {
                throw IOException(e.message)
            }
        }
    }
}
