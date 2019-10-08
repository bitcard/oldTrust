package trust.blockchain.blockchain.theta

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.blockchain.ethereum.EthFeeCalculator
import trust.blockchain.blockchain.ethereum.EthLikeAddress
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Contract
import trust.blockchain.entity.Unit

/* compiled from: ThetaFuelFeeCalculator.kt */
class ThetaFuelFeeCalculator : EthFeeCalculator() {

    override fun defaultFee(): Long {
        return 2000000000000L
    }

    override fun energyAsset(account: Account): Asset {
        val ethLikeAddress = TFUEL
        return Asset(4, Contract(ethLikeAddress, ethLikeAddress.toString(), TFUEL_NAME, Unit(18, TFUEL_SYMBOL), Slip.THETA, "tfuel"), account, false, false)
    }

    override fun priceToWei(price: String): BigInteger {
        return BigInteger(price)
    }

    override fun type(): Int {
        return 1
    }

    override fun unit(): Unit {
        return Unit(18, TFUEL_SYMBOL)
    }

    companion object {
        private val TFUEL = EthLikeAddress("0x000000000000000000000000000000746675656c")
        val TFUEL_NAME = "Theta Fuel"
        val TFUEL_SYMBOL = "TFUEL"
    }
}
