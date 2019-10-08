package trust.blockchain.blockchain.binance

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.FeeCalculator
import trust.blockchain.Slip
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Unit

/* compiled from: BinanceFeeCalculator.kt */
class BinanceFeeCalculator : FeeCalculator {
    override fun calc(fee: Fee): BigInteger {
        return fee.price().multiply(fee.limit().toBigInteger())
    }

    override fun defaultFee(): Long {
        return 37500
    }

    override fun energyAsset(account: Account): Asset {
        return Slip.BINANCE.coinAsset(account, true)
    }

    override fun feeMax(): BigInteger {
        return priceDef().multiply(BigInteger.valueOf(limitMax()))
    }

    override fun isValidLimit(j: Long): Int {
        return 0
    }

    override fun isValidPrice(bigInteger: BigInteger): Int {
        return 0
    }

    override fun limitDef(i: Int): Long {
        return (if (i == 5) 5000 else 37500).toLong()
    }

    override fun limitMax(): Long {
        return 100000000000L
    }

    override fun limitMin(): Long {
        return 0
    }

    override fun priceDef(): BigInteger {
        return BigInteger.ONE
    }

    override fun priceMax(): BigInteger {
        return BigInteger.ONE
    }

    override fun priceMin(): BigInteger {
        return BigInteger.ONE
    }

    override fun priceToWei(str: String): BigInteger {
        return BigInteger(str).multiply(BigInteger.TEN.pow(unit().decimals))
    }

    override fun type(): Int {
        return 1
    }

    override fun unit(): Unit {
        return Slip.BINANCE.unit()
    }
}
