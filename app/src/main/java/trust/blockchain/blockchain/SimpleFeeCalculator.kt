package trust.blockchain.blockchain

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.FeeCalculator
import trust.blockchain.Slip
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Unit

/* compiled from: SimpleFeeCalculator.kt */
open class SimpleFeeCalculator(val coin: Slip) : FeeCalculator {

    override fun calc(fee: Fee): BigInteger {
        return fee.price().multiply(BigInteger.valueOf(fee.limit()))
    }

    override fun defaultFee(): Long {
        return 1
    }

    override fun energyAsset(account: Account): Asset {
        return this.coin.coinAsset(account, true)
    }

    override fun feeMax(): BigInteger {
        return BigInteger.valueOf(java.lang.Long.MAX_VALUE)
    }

    override fun isValidLimit(j: Long): Int {
        return 0
    }

    override fun isValidPrice(bigInteger: BigInteger): Int {
        return 0
    }

    override fun limitDef(i: Int): Long {
        return 0
    }

    override fun limitMax(): Long {
        return 0
    }

    override fun limitMin(): Long {
        return 0
    }

    override fun priceDef(): BigInteger {
        return BigInteger.valueOf(30)
    }

    override fun priceMax(): BigInteger {
        return BigInteger.valueOf(60)
    }

    override fun priceMin(): BigInteger {
        return BigInteger.valueOf(20)
    }

    override fun priceToWei(price: String): BigInteger {
        return BigInteger(price).multiply(BigInteger.TEN.pow(unit().decimals))
    }

    override fun type(): Int {
        return 1
    }

    override fun unit(): Unit {
        return this.coin.unit()
    }

    companion object {
        fun checkMinimumFee(j: Long) {
            if (j <= 0) {
                throw Exception("Unreasonable Fee, Please try again.")
            }
        }


        fun getMinimumByteFee(coin: Slip): Long {

            when (coin) {
                Slip.BTC, Slip.LTC, Slip.ZCASH -> return 0
                Slip.GROESTL, Slip.BCH -> return 1

                Slip.ZCOIN,
                Slip.DASH,
                Slip.VIACOIN,
                Slip.QTUM,
                Slip.RAVEN,
                Slip.ZELCASH -> return 1000

                Slip.DOGECOIN -> return 10000

                Slip.ETH, Slip.CLO, Slip.ETC, Slip.ICX, Slip.GO, Slip.AION, Slip.THETA,
                Slip.BINANCE, Slip.STELLAR, Slip.POA, Slip.TOMO, Slip.VET, Slip.WAN,
                Slip.THUNDERTOKEN, Slip.KIN, Slip.RIPPLE, Slip.TEZOS, Slip.COSMOS,
                Slip.TRX, Slip.ONTOLOGY, Slip.ZILLIQA, Slip.IOTEX, Slip.WAVES, Slip.NIMIQ -> throw IllegalArgumentException("Coin not supported")
//                else -> throw NoWhenBranchMatchedException()
            }
        }
    }
}
