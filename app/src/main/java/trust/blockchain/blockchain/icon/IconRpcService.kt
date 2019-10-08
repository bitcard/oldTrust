package trust.blockchain.blockchain.icon

import io.reactivex.Single
import org.bouncycastle.jcajce.provider.digest.SHA3
import org.json.JSONObject
import org.spongycastle.util.encoders.Base64
import org.web3j.utils.Numeric
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.TransactionUnsigned
import java.util.concurrent.ThreadLocalRandom

/* compiled from: IconRpcService.kt */
open class IconRpcService : RpcService {

    val blockInfo: BlockInfo
        get() = BlockInfo(null, null, System.currentTimeMillis() * 1000.toLong(), null, null, null, 3, -1)

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ICX)

    private fun convert(transactionUnsigned: TransactionUnsigned): Map<String, String> {
        return mutableMapOf(
                "from" to transactionUnsigned.from().address().display(),
                "to" to transactionUnsigned.recipientAddress().display(),
                "timestamp" to "0x" + transactionUnsigned.blockInfo().timestamp.toString(16),
                "nonce" to "0x" + Math.abs(transactionUnsigned.nonce()).toString(16).toLowerCase(),
                "stepLimit" to "0x" + transactionUnsigned.fee().limit().toString(16),
                "value" to "0x" + transactionUnsigned.weiAmount().toString(16).toLowerCase(),
                "nid" to "0x" + getChainId(transactionUnsigned),
                "version" to "0x" + java.lang.Long.toString(3, 16).toLowerCase()
                )
    }

    private fun encodeJsonToBytes(transactionUnsigned: TransactionUnsigned, bArr: ByteArray): Single<ByteArray> {
        return Single.fromCallable<ByteArray> {
            val params = JSONObject(this.convert(transactionUnsigned))
            params.put("signature", Base64.toBase64String(bArr))
            val request = JSONObject()
            request.put("jsonrpc", "2.0")
            request.put("method", "icx_sendTransaction")
            request.put("id", 2)
            request.put("params", params)
            request.toString().toByteArray(Charsets.UTF_8)
        }
    }

    private fun encodeToIconTx(transactionUnsigned: TransactionUnsigned): Single<ByteArray> {
        return Single.fromCallable <ByteArray>{
            val mapData = this.convert(transactionUnsigned)
            var str = "icx_sendTransaction"
            for (key in mapData.keys.sorted()) {
                str += '.' + key + '.' + mapData.get(key)
            }
            str.toByteArray(Charsets.UTF_8)
        }
    }

    override fun encodeTransaction(tx: TransactionUnsigned, signature: ByteArray?): Single<ByteArray> {
        if (signature != null && signature.size != 0)
            return encodeJsonToBytes(tx, signature)

        return Single.just<BlockInfo>(blockInfo)
                .map {
                    tx.blockInfo(it)
                }
                .flatMap {
                    encodeToIconTx(it);
                }

    }

    override fun estimateFee(tx: TransactionUnsigned): Single<Fee> {
        val asset = loadBalances(tx.account().coin, arrayOf(tx.asset()))!![0]
        val price = asset.coin().feeCalculator().priceDef()
        val limit = asset.coin().feeCalculator().limitMin()
        return Single.just<Fee>(Fee(price, limit, asset, asset))
    }

    override fun estimateNonce(account: Account): Single<Long> {
        return Single.fromCallable<Long> {
            Math.abs(ThreadLocalRandom.current().nextLong());
        }
    }

    override fun findTransaction(account: Account, hash: String): Single<Transaction> {
        return Single.just(null)
    }

    override fun getBlockNumber(coin: Slip): Single<BlockInfo> {
        return Single.just<BlockInfo>(null)
    }

    open fun getChainId(tx: TransactionUnsigned): String {
        return "1"
    }

    override fun loadBalances(coin: Slip, assets: Array<Asset>): Array<Asset> {
        return arrayOf()
    }

    override fun sendTransaction(account: Account, signedMessage: ByteArray): Single<String> {
        return Single.just<String>("")
    }

    override fun transactionId(tx: TransactionUnsigned, sign: ByteArray): Single<String> {
        return encodeToIconTx(tx).map{
            Numeric.toHexString(SHA3.Digest256().digest(it));
        }
    }

    companion object {
        val ICON_VERSION: Long = 3
    }
}
