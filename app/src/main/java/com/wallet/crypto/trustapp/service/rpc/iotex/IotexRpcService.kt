package com.wallet.crypto.trustapp.service.rpc.iotex

import android.text.TextUtils

import com.wallet.crypto.trustapp.service.rpc.iotex.entity.IotexAccount
import io.reactivex.Single
import java.math.BigInteger
import java.util.Arrays

import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.iotex.IotexFeeCalculator
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.Value
import trust.blockchain.util.ExtensionsKt

/* compiled from: IotexRpcService.kt */
class IotexRpcService @Inject
constructor(private val rpcClient: IotexRpcClient) : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.IOTEX)

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable {
            val asset = loadBalances(tx.account().coin, arrayOf(tx.asset()))[0]
            Fee(asset.coin().feeCalculator().priceDef(), true, asset.coin().feeCalculator().limitDef(0), true, asset, asset)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.fromCallable {
            getAccountData(account).accountMeta.pendingNonce.toLong()
        }
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val body = rpcClient.getTransactionByHash(hash).execute().body()!!
            val actionInfo = body.actionInfo
            var i = true
            if (!(actionInfo is Collection<*> && (actionInfo as Collection<*>).isEmpty())) {
                for (blkHeight in actionInfo) {
                    if (java.lang.Long.parseLong(blkHeight.blkHeight) <= 0) {
                        i = false
                        break;
                    }
                }
            }
            Transaction(hash,
                    "",
                    "",
                    0,
                    0,
                    account.address(),
                    null,
                    null,
                    (IotexFeeCalculator().defaultFee()).toString(),
                    "",
                    account.coin,
                    Transaction.Type.TRANSFER,
                    Transaction.Status.getState(!i),
                    Transaction.Direction.OUT)
        }
    }

    fun getAccountData(account: Account): IotexAccount {
        return this.rpcClient.getAccountData(account.address().data()).execute().body()!!
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable { BlockInfo("", 0) }
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val assetArr = Arrays.copyOf(assets, assets.size)
        val length = assetArr.size
        for (i in 0 until length) {
            val asset = assetArr[i]
            if (asset.type == 1 && asset.coin() === coin) {
                assetArr[i] = Asset(asset,
                        try
                        {
                            Value(BigInteger(getAccountData(asset.account).accountMeta.balance))
                        } catch (unused: Exception) {
                            asset.balance ?: Value(BigInteger.ZERO)
                        }
                )
            }
        }
        return assetArr
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val body = rpcClient.broadcastTransaction(ExtensionsKt.toHex(signedMessage)).execute().body()!!
            if (TextUtils.isEmpty(body.actionHash)) {
                throw IllegalStateException(body.error)
            }
            body.actionHash
        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }
}
