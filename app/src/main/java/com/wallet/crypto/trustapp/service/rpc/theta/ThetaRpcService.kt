package com.wallet.crypto.trustapp.service.rpc.theta

import com.google.gson.Gson
import io.reactivex.Single
import java.io.IOException
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.blockchain.theta.ThetaService
import trust.blockchain.blockchain.theta.entity.*
import trust.blockchain.entity.Account
import trust.blockchain.entity.Address
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.JSONRPCIDGenerator
import trust.blockchain.entity.JsonResult
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt

/* compiled from: ThetaRpcService.kt */
class ThetaRpcService @Inject
constructor(private val thetaRpcClient: ThetaRpcClient, private val gson: Gson) : ThetaService() {

    private fun loadBalance(asset: Asset): Value {
        try {
            val bigInteger: BigInteger
            val coins = getAccountData(asset.account.address()).coins
            if (asset.isGas) {
                bigInteger = BigInteger(coins.tfuelwei)
            } else {
                bigInteger = BigInteger(coins.thetawei)
            }
            return Value(bigInteger)
        } catch (unused: Exception) {
            return asset.balance ?: Value(BigInteger.ZERO)
        }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val feeCalculator = tx.asset().coin().feeCalculator()
            val asset = tx.asset()
            val energy = feeCalculator.energyAsset(asset.account)
            Fee(BigInteger.ONE, feeCalculator.defaultFee(), energy, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val result = thetaRpcClient.getTransaction(ThetaModelsKt.toJson(ThetaRequestData(id = JSONRPCIDGenerator.INSTANCE.nextID(), method = "theta.GetTransaction", params = listOf(mapOf("hash".to(hash)))), gson)).execute().body()?.result ?: throw IOException("RPC Error")
            Transaction(hash, "", result.blockHeight.toString(), 0, 0, account.address(), null, null, null, "", account.coin, Transaction.Type.TRANSFER, Transaction.Status.COMPLETED, Transaction.Direction.OUT)
        }
    }

    override fun getAccountData(address: Address): ThetaAccount {
        return this.thetaRpcClient.getAccountData(ThetaModelsKt.toJson(ThetaRequestData(id = JSONRPCIDGenerator.INSTANCE.nextID(), method = "theta.GetAccount", params = listOf(mapOf("address".to(address.display())))), this.gson)).execute().body()?.result ?: throw IOException("RPC Error")

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
            if (ExtensionsKt.canLoadBalance(asset, coin)) {
                copyOf[i] = Asset(asset, loadBalance(asset))
            }
        }
        return copyOf
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable{
            try {
                val body = thetaRpcClient.broadCastTransaction(ThetaModelsKt.toJson(ThetaRequestData(id = JSONRPCIDGenerator.INSTANCE.nextID(), method = "theta.BroadcastRawTransaction", params = listOf(mapOf("tx_bytes" to ExtensionsKt.toHex(signedMessage)))), gson))
                        .execute().body() ?: throw IOException("RPC Error")
                if (body.result.hash.isNullOrEmpty()) {
                    throw Exception("Transaction Failed")
                }
                body.result.hash
            } catch (e : Exception) {
                throw java.lang.Exception(e.message)
            }
        }
    }
}
