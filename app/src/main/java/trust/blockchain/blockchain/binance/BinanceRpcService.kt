package trust.blockchain.blockchain.binance

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.RpcService
import trust.blockchain.Slip
import trust.blockchain.blockchain.binance.entity.BinanceAccount
import trust.blockchain.blockchain.binance.entity.NodeInfo
import trust.blockchain.entity.Asset

/* compiled from: BinanceRpcService.kt */
abstract class BinanceRpcService : RpcService {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.BINANCE)

    abstract fun getNodeInfo(): NodeInfo

    abstract fun getAccountData(str: String): BinanceAccount

    fun getAssetDenom(asset: Asset): String {
        val str: String
        if (asset.isCoin) {
            return asset.coin().unit().symbol
        }
        return asset.contract.tokenId
    }
}
