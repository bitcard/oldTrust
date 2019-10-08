package com.wallet.crypto.trustapp.service.rpc.bitcoin

import com.wallet.crypto.trustapp.C
import io.reactivex.Single
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger
import java.util.Arrays
import javax.inject.Inject
import trust.blockchain.AccountIndicesService
import trust.blockchain.Slip
import trust.blockchain.UnspentOutputsSelector
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.blockchain.bitcoin.BitcoinRpcService
import trust.blockchain.blockchain.bitcoin.entity.BitcoinLikeModelsKt
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.*
import trust.blockchain.util.ExtensionsKt
import trust.blockchain.util.HDWalletExtensions
import trust.blockchain.util.Numbers

/* compiled from: BitcoinLikeRpcService.kt */
class BitcoinLikeRpcService @Inject
constructor(private val rpcClient: BitcoinRpcClient, private val accountIndicesService: AccountIndicesService) : BitcoinRpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.BTC, Slip.LTC, Slip.BCH, Slip.DASH, Slip.ZCOIN, Slip.ZCASH, Slip.DOGECOIN, Slip.GROESTL, Slip.QTUM, Slip.VIACOIN, Slip.RAVEN, Slip.ZELCASH)

    private fun getBalance(asset: Asset): Value {
        try {
            val body = this.rpcClient.getBalance(C.rpcUrl(asset.coin()) + "/v2/xpub/" + asset.account.extendedPublicKey + "?tokens=used").execute().body() ?: throw IOException("RPC Error get balance")
            val list = body.tokens.map {
                BitcoinLikeModelsKt.toBip32Path(it)
            }
            this.accountIndicesService[asset.account.extendedPublicKey] = list.toTypedArray()
            return Value(BigDecimal(body.balance))

        } catch (unused: Exception) {
            return asset.balance ?: Value(BigDecimal.ZERO)
        }

    }

    private fun getPriority(slip: Slip): Int {
        return if (slip != Slip.DASH) 2 else 4
    }

    override fun encodeTransaction(transactionUnsigned: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        return Single.just(ByteArray(0))
    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        return Single.fromCallable<Fee> {
            val asset2 = loadBalances(tx.account().coin, arrayOf(tx.asset())).first()
            val loadFee = loadFee(tx.asset().coin())
            val unspentOutputs = getUnspentOutputs(tx)
            val unspentOutputsSelector = UnspentOutputsSelector(loadFee.toLong())
            var length : Int
            var i : Int
            try {
                length = unspentOutputsSelector.select(unspentOutputs, tx.weiAmount().toLong()).size
                i = 2
            } catch (unused: Exception) {
                length = ExtensionsKt.filterOutDusts(unspentOutputs.toList(), loadFee.toLong()).size
                i = 1
            }

            Fee(loadFee, true, unspentOutputsSelector.calcTransactionSize(length, i), true, asset2, asset2)
        }
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.just(java.lang.Long.valueOf(0))
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.fromCallable {
            val body = rpcClient.getTransactionByHash(C.rpcUrl(account.coin) + "/v2/tx/" + hash).execute().body() ?: throw IOException("RPC Error find transaction")
            Transaction(body.txid, body.time, null, null, SubunitValue(Value(BigInteger.ZERO), account.coin.unit()), account.coin, if (body.confirmations > 0) Transaction.Status.COMPLETED else Transaction.Status.PENDING)
        }
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.fromCallable {
            val body = rpcClient.latestBlock(C.rpcUrl(coin) + '/').execute().body() ?: throw IOException("RPC Error latest block")
            BlockInfo("", BigInteger.valueOf(body.backend.blocks))
        }
    }

    override fun getCurrentChangeAddress(account: Account): String {
        val extendedPublicKey = account.extendedPublicKey
        val tokens = this.rpcClient.getDerivedAddresses(C.rpcUrl(account.coin) + "/v2/xpub/" + extendedPublicKey + "?tokens=derived").execute().body()?.tokens ?: throw IOException("RPC Error")
        return HDWalletExtensions.deriveAddress(
                account.coin,
                extendedPublicKey,
                DerivationPath(tokens.first {DerivationPath(it.path).changeIndex == 1 && it.transfers == 0L}.path))
                ?: throw IllegalStateException("Could not get change address")
    }

    fun getNetworkFee(coin: Slip): String {
        return this.rpcClient.estimateFee(C.rpcUrl(coin) + "/v2/estimatefee/" + getPriority(coin)).execute().body()?.result ?: throw IOException("RPC Error estimate fee")
    }

    override fun getUnspentOutputs(tx: TransactionUnsigned): Array<UnspentOutput> {
        val distinct = this.rpcClient.getUnspents(C.rpcUrl(tx.account().coin) + "/v2/utxo/" + tx.asset().account.extendedPublicKey).execute().body()?.distinct() ?: throw IOException("RPC Error")
        for (it in distinct) {
            it.txid = Numbers.INSTANCE.reverseBytes(it.txid)
        }
        return distinct.toTypedArray()
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        val copyOf = Arrays.copyOf(assets, assets.size)
        val length = copyOf.size
        for (i in 0 until length) {
            val asset = copyOf[i]
            if (asset.type == 1 && asset.coin() === coin) {
                copyOf[i] = Asset(asset, getBalance(asset))
            }
        }
        return copyOf
    }

    fun loadFee(coin: Slip): BigInteger {
        val minimumByteFee = BigInteger.valueOf(SimpleFeeCalculator.getMinimumByteFee(coin))
        try {
            var networkFeePerKb = coin.unit().toUnit(getNetworkFee(coin))
            return convertToFeePerByte(networkFeePerKb).max(minimumByteFee)
        } catch (unused: Exception) {
            return minimumByteFee
        }

    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.fromCallable {
            val requestBody = RequestBody.create(C.f16601d, ExtensionsKt.toHex(signedMessage))
            try {
                val response = rpcClient.broadcastTransaction(C.rpcUrl(account.coin) + "/v2/sendtx/", requestBody).execute()
                if (response == null || response.isSuccessful() || response.errorBody() == null) {
                    val result = response.body()?.result?: throw IOException("Response error")
                    if (result.isEmpty())
                        throw Exception("Failed to relay transaction")
                    result
                } else {
                    throw Exception(JSONObject(response.errorBody()?.string()).getString("error") ?: throw IOException("Response error"))
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

    override fun transactionId(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<String> {
        return Single.just("")
    }

    companion object {
        private val oneKB: BigInteger =BigInteger.valueOf(1000)

        fun convertToFeePerByte(feePerKB: BigInteger): BigInteger {
            return feePerKB.divide(oneKB)
        }

    }
}
