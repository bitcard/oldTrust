package trust.blockchain.blockchain.ontology

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.blockchain.ethereum.EthFeeCalculator
import trust.blockchain.blockchain.ethereum.EthLikeAddress
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Contract
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Unit

/* compiled from: OngFeeCalculator.kt */
class OngFeeCalculator : EthFeeCalculator() {

    override fun calc(fee: Fee): BigInteger {
        return fee.price().multiply(BigInteger.valueOf(fee.limit()))
    }

    override fun energyAsset(account: Account): Asset {
        val ethLikeAddress = ONG
        return Asset(4, Contract(ethLikeAddress, ethLikeAddress.toString(), ONG_NAME, unit(), Slip.ONTOLOGY, "ong"), account, false, false)
    }

    override fun limitMax(): Long {
        return 20000
    }

    override fun priceToWei(price: String): BigInteger {
        return BigInteger(price)
    }

    override fun type(): Int {
        return 1
    }

    override fun unit(): Unit {
        return Unit(9, ONG_SYMBOL)
    }

    companion object {
        private val ONG = EthLikeAddress("0x00000000000000000000000000000000006F6e67")
        @JvmField val ONG_NAME = "Ontology Gas"
        @JvmField val ONG_SYMBOL = "ONG"
    }
}
