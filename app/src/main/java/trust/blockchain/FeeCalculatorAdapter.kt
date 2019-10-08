package trust.blockchain

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.blockchain.binance.BinanceFeeCalculator
import trust.blockchain.blockchain.cosmos.CosmosFeeCalculator
import trust.blockchain.blockchain.ethereum.EthFeeCalculator
import trust.blockchain.blockchain.ethereum.ThorFeeCalculator
import trust.blockchain.blockchain.ethereum.WanFeeCalculator
import trust.blockchain.blockchain.icon.IconFeeCalculator
import trust.blockchain.blockchain.iotex.IotexFeeCalculator
import trust.blockchain.blockchain.nimiq.NimiqFeeCalculator
import trust.blockchain.blockchain.ontology.OngFeeCalculator
import trust.blockchain.blockchain.ripple.RippleFeeCalculator
import trust.blockchain.blockchain.tezos.TezosFeeCalculator
import trust.blockchain.blockchain.theta.ThetaFuelFeeCalculator
import trust.blockchain.blockchain.tron.TronFeeCalculator
import trust.blockchain.blockchain.waves.WavesFeeCalculator
import trust.blockchain.blockchain.zcash.ZcashFeeCalculator
import trust.blockchain.blockchain.zilliqa.ZilliqaFeeCalculator

/* compiled from: FeeCalculatorAdapter.kt */
class FeeCalculatorAdapter {

    /* compiled from: FeeCalculatorAdapter.kt */
    companion object {

        fun byCoin(coin: Slip): FeeCalculator {
            when (coin) {
                Slip.ETH, Slip.GO, Slip.CLO, Slip.ETC, Slip.POA, Slip.TOMO, Slip.THUNDERTOKEN, Slip.AION -> return EthFeeCalculator()
                Slip.WAN -> return WanFeeCalculator()
                Slip.VET -> return ThorFeeCalculator()
                Slip.ICX -> return IconFeeCalculator()
                Slip.TRX -> return TronFeeCalculator()
                Slip.VIACOIN -> return SimpleFeeCalculator(Slip.VIACOIN)
                Slip.QTUM -> return SimpleFeeCalculator(Slip.QTUM)
                Slip.GROESTL -> return SimpleFeeCalculator(Slip.GROESTL)
                Slip.BTC -> return SimpleFeeCalculator(Slip.BTC)
                Slip.LTC -> return SimpleFeeCalculator(Slip.LTC)
                Slip.BCH -> return SimpleFeeCalculator(Slip.BCH)
                Slip.DASH -> return SimpleFeeCalculator(Slip.DASH)
                Slip.ZCOIN -> return SimpleFeeCalculator(Slip.ZCOIN)
                Slip.ZCASH -> return ZcashFeeCalculator()
                Slip.BINANCE -> return BinanceFeeCalculator()
                Slip.RIPPLE -> return RippleFeeCalculator()
                Slip.TEZOS -> return TezosFeeCalculator()
                Slip.KIN -> return SimpleFeeCalculator(Slip.KIN)
                Slip.STELLAR -> return SimpleFeeCalculator(Slip.STELLAR)
                Slip.NIMIQ -> return NimiqFeeCalculator()
                Slip.COSMOS -> return CosmosFeeCalculator()
                Slip.DOGECOIN -> return SimpleFeeCalculator(Slip.DOGECOIN)
                Slip.THETA -> return ThetaFuelFeeCalculator()
                Slip.ONTOLOGY -> return OngFeeCalculator()
                Slip.ZILLIQA -> return ZilliqaFeeCalculator()
                Slip.ZELCASH -> return SimpleFeeCalculator(Slip.ZELCASH)
                Slip.IOTEX -> return IotexFeeCalculator()
                Slip.RAVEN -> return SimpleFeeCalculator(Slip.RAVEN)
                Slip.WAVES -> return WavesFeeCalculator()
            }
        }
    }

//    fun byCoin(slip: Slip): FeeCalculator {
//        return byCoin(slip)
//    }
}
