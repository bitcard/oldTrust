package com.wallet.crypto.trustapp.service.rpc.ontology

import android.text.TextUtils
import com.wallet.crypto.trustapp.service.rpc.entity.JSONRequestData
import io.reactivex.Single
import java.io.IOException
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.blockchain.ontology.OntologyService
import trust.blockchain.blockchain.ontology.entity.OntologyBalance
import trust.blockchain.blockchain.ontology.entity.OntologyGasPriceResult
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.JsonResult
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt

/* compiled from: OntologyRpcService.kt */
class OntologyRpcService @Inject
constructor(private val rpcClient: OntologyRpcClient) : OntologyService() {

    override val gasPrice: OntologyGasPriceResult
        get() {
            try {
                return this.rpcClient.getGasPrice(JSONRequestData(method = "getgasprice", params = arrayOfNulls(0))).execute()?.body()?.result ?: throw IOException("Could not load gas price")
            } catch (unused: Exception) {
                return OntologyGasPriceResult(500)
            }

        }

    private fun loadBalance(asset: Asset): Value {
        try {
            val jsonResult = this.rpcClient.getBalance(JSONRequestData(method = "getbalance", params = arrayOf(asset.account.address().display()))).execute().body()?.result ?: throw Exception("Could not load balance")
            return Value(if (asset.isGas) BigInteger(jsonResult.ong) else BigInteger(jsonResult.ont))
        } catch (unused: Exception) {
            return asset.balance ?: Value(BigInteger.ZERO)
        }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val fee = tx.asset().coin().feeCalculator()
            val asset = tx.asset()
            val energy = fee.energyAsset(asset.account)
            val assets = loadBalances(tx.account().coin, arrayOf(energy, asset))
            Fee(BigInteger.valueOf(gasPrice.getGasprice()), fee.limitMax(), assets[0], assets[1])
        }
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val body = rpcClient.getTransaction(JSONRequestData(method = "getrawtransaction", params = arrayOf(hash, Integer.valueOf(1)))).execute().body()
            val result = body!!.result
            Transaction(hash,
                    "",
                    result.height.toString(),
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    try {
                        account.coin.feeCalculator().calc(Fee(BigInteger(result.gasPrice), java.lang.Long.parseLong(result.gasLimit), account.coin))
                    } catch (unused: Exception) {
                        BigInteger.ZERO
                    }.toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    if (result.height > 0) Transaction.Status.COMPLETED else Transaction.Status.PENDING,
                    Transaction.Direction.OUT)
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
            val asset = copyOf[i]
            if (ExtensionsKt.canLoadBalance(asset, coin)) {
                copyOf[i] = Asset(asset, loadBalance(asset))
            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            try {
                val body = rpcClient.sendTransaction(JSONRequestData(method = "sendrawtransaction", params = arrayOf<String>(ExtensionsKt.toHex(signedMessage)))).execute().body() ?: throw IOException("RPC Error")
                if (!TextUtils.isEmpty(body.result) && body.error == 0) {
                    throw IOException(body.result + " \n" + body.desc + " - " + body.error)
                }
                body.result
            } catch (e: Exception) {
                throw IOException(e)
            }

        }
    }
}
