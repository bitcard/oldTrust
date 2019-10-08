package com.wallet.crypto.trustapp.service.rpc.binance

import com.wallet.crypto.trustapp.service.rpc.entity.RpcExtensions;
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderStatus

import io.reactivex.Single
import javax.inject.Inject

import okhttp3.RequestBody
import trust.blockchain.Slip
import trust.blockchain.blockchain.binance.BinanceFeeCalculator
import trust.blockchain.blockchain.binance.BinanceRpcService
import trust.blockchain.blockchain.binance.entity.BinanceAccount
import trust.blockchain.blockchain.binance.entity.NodeInfo
import trust.blockchain.entity.*
import trust.blockchain.entity.Transaction.Direction
import trust.blockchain.entity.Transaction.Status
import trust.blockchain.entity.Transaction.Type
import trust.blockchain.util.ExtensionsKt
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger

/* compiled from: BinanceChainRpcService.kt */
class BinanceChainRpcService @Inject
constructor(private val binanceRpcClient: BinanceRpcClient) : BinanceRpcService() {
    override fun getNodeInfo(): NodeInfo {
        try {
            return RpcExtensions.handleResponse(this.binanceRpcClient.getNodeInfo().execute())
        } catch (unused : Exception) {
            throw IOException("Unknown error");
        }
    }

    private fun loadBalance(r11: Array<Asset>): Array<Asset> {
        try {
            val balances = this.getAccountData(r11.first().account.address().toString()).balances
            if (balances == null || balances.isEmpty()) {
                val r0 = r11.copyOf()
                r11.forEachIndexed { i, _ ->
                    r0[i] = Asset(r0[i], Value(BigDecimal.ZERO))
                }
                return r0
            }

            val index = HashMap<String, Asset>()
            val r4 = ArrayList<Asset>()
            r11.forEach {
                if (it.coin() == Slip.BINANCE)
                    r4.add(it)
            }

            r4.forEach {
                index.put(this.getAssetDenom(it), Asset(it, Value(BigInteger.ZERO)))
            }

            balances.forEach {
                val asset = index.get(it.symbol)
                if (asset != null) {
                    index.put(it.symbol, Asset(asset, Value(BigDecimal(it.free).multiply(BigDecimal.TEN.pow(asset.coin().unit().decimals)))))
                }
            }
            return index.values.toTypedArray()
        } catch (e : Exception) {

        }
        return r11
    }

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable{
            val asset = tx.asset()
            val feeCalculator = asset.coin().feeCalculator()
            val energy = feeCalculator.energyAsset(asset.account)
            val limit = feeCalculator.limitDef(tx.type())
            Fee(feeCalculator.priceDef(), limit, energy, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(0)
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.just(
                Transaction(hash, "", "0", 0, 0, account.address(), null, null, BinanceFeeCalculator().defaultFee().toString(), "", account.coin, Type.TRANSFER, Status.COMPLETED, Direction.OUT)
        )
    }

    override fun getAccountData(address: String): BinanceAccount {
        try {
            val response = this.binanceRpcClient.getAccountData(address).execute();
            return RpcExtensions.handleResponse(response)
        } catch (e: Exception) {
            throw IOException(e.message);
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable{ BlockInfo("", 0) }
    }

    fun getOrderState(hash: String): TradeOrderStatus {
        val body = RpcExtensions.handleResponse(this.binanceRpcClient.getTransactionByHash(hash).execute());
        val orderId = body?.tx?.value?.msg?.first()?.value?.id?:throw IOException("Transaction not found");
        return RpcExtensions.handleResponse(this.binanceRpcClient.getOrder(orderId).execute());
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        return loadBalance(assets)
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable<String> {
            val requestBody = RequestBody.create(C.f16601d, ExtensionsKt.toHex(signedMessage))
            try {
                val body = RpcExtensions.handleResponse(binanceRpcClient.broadcastTransaction(requestBody, true).execute())
                if (body.size == 0 || body.first().hash.isNullOrEmpty()) {
                    throw Exception("Failed to relay transaction")
                }
                body.first().getHash()
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
