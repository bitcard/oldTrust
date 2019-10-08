package com.wallet.crypto.trustapp.service.rpc.nimiq

import com.google.gson.Gson
import io.reactivex.Single

import java.io.IOException
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import trust.blockchain.Slip
import trust.blockchain.blockchain.nimiq.NimiqService
import trust.blockchain.blockchain.nimiq.entity.NimiqModelsKt
import trust.blockchain.blockchain.nimiq.entity.NimiqRequestData
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt

/* compiled from: NimiqRpcService.kt */
class NimiqRpcService @Inject
constructor(private val nimiqRpcClient: NimiqRpcClient, private val gson: Gson) : NimiqService() {

    private fun loadBalance(asset: Asset): Value {
        try {
            val result = this.nimiqRpcClient.getBalance(NimiqModelsKt.toJson(NimiqRequestData(method = "getBalance", params = listOf(asset.account.address().data())), this.gson)).execute().body()?.result ?: throw IOException("RPC Error")
            return Value(BigInteger.valueOf(result))
        } catch (unused: Exception) {
            return asset.balance ?: Value(BigInteger.ZERO)
        }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val energy = tx.asset().coin().feeCalculator().energyAsset(tx.from())
            Fee(BigInteger.valueOf(tx.account().coin.feeCalculator().defaultFee()), true, 1, true, energy, energy)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val result = nimiqRpcClient.getTransactionById(NimiqModelsKt.toJson(NimiqRequestData(method = "getTransactionByHash", params = listOf(hash)), gson)).execute().body()?.result?: throw IOException("RPC Error")
            Transaction(hash,
                    "",
                    result.blockNumber.toString(),
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    result.fee.toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (result.blockNumber > 0) Transaction.Status.COMPLETED else Transaction.Status.PENDING,
                    Transaction.Direction.OUT)
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable {
            BlockInfo("", BigInteger.valueOf(currentBlock))
        }
    }

    override fun getCurrentBlock(): Long {
        try {
            return this.nimiqRpcClient.currentBlock(NimiqModelsKt.toJson(NimiqRequestData(method = "blockNumber"), this.gson)).execute().body()!!.result.toLong()
        } catch (unused: Exception) {
            return 0
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
            val r0 = nimiqRpcClient.broadCastTransaction(NimiqModelsKt.toJson(NimiqRequestData(method = "sendRawTransaction", params = listOf(ExtensionsKt.toHex(signedMessage))), this.gson)).execute().body()
            val r1 = r0?.error?.message ?: ""
            r0?.result?: throw java.lang.Exception (r1)
        }
    }
}
