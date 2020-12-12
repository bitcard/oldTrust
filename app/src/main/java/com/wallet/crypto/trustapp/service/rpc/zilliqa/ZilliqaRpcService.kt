package com.wallet.crypto.trustapp.service.rpc.zilliqa

import android.text.TextUtils
import com.google.gson.Gson
import com.wallet.crypto.trustapp.interact.nonce.TransactionsNonceInteract
import com.wallet.crypto.trustapp.service.rpc.entity.JSONRequestData

import io.reactivex.Single

import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.zilliqa.ZilliqaAddress
import trust.blockchain.blockchain.zilliqa.ZilliqaFeeCalculator
import trust.blockchain.blockchain.zilliqa.entity.*
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt
import java.io.IOException
import java.nio.charset.StandardCharsets

/* compiled from: ZilliqaRpcService.kt */
class ZilliqaRpcService @Inject
constructor(private val rpcClient: ZilliqaRpcClient, private val transactionsNonceInteract: TransactionsNonceInteract) : RpcService {

    val gasPrice: BigInteger
        get() {
            try {
                val result = this.rpcClient.getGasPrice(JSONRequestData(method = Methods.GET_MINIMUM_PRICE.value, params = arrayOfNulls(0))).execute().body()?.result ?: throw IOException("RPC Error")
                return BigInteger(result)
            } catch (unused: Exception) {
                return ZilliqaFeeCalculator().defaultFee().toBigInteger()
            }

        }

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ZILLIQA)

    /* compiled from: ZilliqaRpcService.kt */
    enum class Methods private constructor(val value: String) {
        GET_BALANCE("GetBalance"),
        GET_MINIMUM_PRICE("GetMinimumGasPrice"),
        CREATE_TRANSACTION("CreateTransaction"),
        GET_TRANSACTION("GetTransaction")
    }

//    private fun getAccountData(account: Account): ZilliqaBalanceResult {
//        val zilliqaAddress = account.address() as ZilliqaAddress
//        return this.rpcClient.getBalance(JSONRequestData(method = Methods.GET_BALANCE.value, params = arrayOf(ExtensionsKt.drop0x(zilliqaAddress.hexValue())))).execute().body()?.result ?: throw Exception("Could not load account data")
//    }

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val feeCalculator = tx.asset().coin().feeCalculator()
            val asset = tx.asset()
            Fee(gasPrice, feeCalculator.limitMax(), asset, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.fromCallable {
//            var nonce =
//            try {
//                getAccountData(account).getNonce() + 1
//            } catch (unused: Exception) {
//                0L
//            }

            var nonce = 0L;

            val longValue = (transactionsNonceInteract.estimate(account).blockingGet() as Number).toLong()
            if (nonce > longValue) nonce else longValue
        }
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val result = rpcClient.getTransaction(JSONRequestData(method = Methods.GET_TRANSACTION.value, params = arrayOf(ExtensionsKt.drop0x(hash)))).execute().body()?.result ?: throw IOException("RPC Error")
            var calc =
            try {
                account.coin.feeCalculator().calc(Fee(BigInteger(result.gasPrice), java.lang.Long.parseLong(result.gasLimit), account.coin))
            } catch (unused: Exception) {
                BigInteger.ZERO
            }
            Transaction(hash, "", "", 0, 0, account.address(), null, null, calc.toString(), "", account.coin, Transaction.Type.TRANSFER, Transaction.Status.getState(!result.receipt.success), Transaction.Direction.OUT)
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable {
            BlockInfo("", 0);
        }
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val copyOf = Arrays.copyOf(assets, assets.size)
        val length = copyOf.size
        for (i in 0 until length) {
//            val asset = copyOf[i]
//            if (asset.type == 1 && asset.coin() === coin) {
//                copyOf[i] = Asset(asset, try {
//                    Value(BigInteger(getAccountData(asset.account).balance))
//                } catch (unused: Exception) {
//                    asset.balance ?: Value(BigInteger.ZERO)
//                })
//            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val params = ExtensionsKt.toJsonObject(Gson().fromJson(String(signedMessage, StandardCharsets.UTF_8), ZilliqaTransaction::class.java))
            val body = rpcClient.sendTransaction(JSONRequestData(method = Methods.CREATE_TRANSACTION.value, params = arrayOf(params))).execute().body() ?:throw IOException("RPC Error")
            if (body.error == null && !TextUtils.isEmpty((body.result).hash)) {
                ExtensionsKt.add0x((body.result).hash)
            } else {
                throw Exception(body.error?.message ?: throw Exception("Failed to relay transaction"))
            }

        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
