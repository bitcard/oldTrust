package trust.blockchain

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.blockchain.aion.AionAddress
import trust.blockchain.blockchain.bitcoincash.BitcoinCashAddress
import trust.blockchain.blockchain.ethereum.EthLikeAddress
import trust.blockchain.blockchain.icon.IconAddress
import trust.blockchain.blockchain.nimiq.NimiqAddress
import trust.blockchain.blockchain.tron.TronAddress
import trust.blockchain.blockchain.wan.WanchainAddress
import trust.blockchain.blockchain.zilliqa.ZilliqaAddress
import trust.blockchain.entity.Address
import trust.blockchain.entity.PlainAddress

/* compiled from: AddressBuilder.kt */
class AddressBuilder {

    companion object{
        val INSTANCE = AddressBuilder

        @JvmStatic
        fun create(slip: Slip, str: String): Address {
            Intrinsics.checkParameterIsNotNull(slip, "coin")
            Intrinsics.checkParameterIsNotNull(str, org.web3j.abi.datatypes.Address.TYPE_NAME)
            when (slip) {
                Slip.ETH, Slip.GO, Slip.CLO, Slip.ETC, Slip.POA, Slip.VET, Slip.THUNDERTOKEN, Slip.THETA, Slip.TOMO -> return EthLikeAddress(str)
                Slip.WAN -> return WanchainAddress(str)
                Slip.ICX -> return IconAddress(str)
                Slip.TRX -> return TronAddress(str)
                Slip.BCH -> return BitcoinCashAddress(str)
                Slip.AION -> return AionAddress(str)
                Slip.NIMIQ -> return NimiqAddress(str)
                Slip.BTC, Slip.LTC, Slip.DASH, Slip.ZCOIN, Slip.ZCASH, Slip.BINANCE, Slip.RIPPLE, Slip.TEZOS, Slip.KIN, Slip.STELLAR, Slip.COSMOS, Slip.ONTOLOGY, Slip.GROESTL, Slip.QTUM, Slip.VIACOIN, Slip.ZELCASH, Slip.IOTEX, Slip.DOGECOIN, Slip.WAVES, Slip.RAVEN -> return PlainAddress(str)
                Slip.ZILLIQA -> return ZilliqaAddress(str)
                else -> throw NoWhenBranchMatchedException()
            }
        }

    }
}
