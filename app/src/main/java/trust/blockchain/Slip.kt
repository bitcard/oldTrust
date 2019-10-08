package trust.blockchain

import java.util.ArrayList
import trust.blockchain.entity.Account
import trust.blockchain.entity.Address
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Contract
import trust.blockchain.entity.Unit
import wallet.core.jni.CoinType
import wallet.core.jni.CoinTypeConfiguration
import wallet.core.jni.Purpose

enum class Slip private constructor(private val coinType: CoinType, val isAvailableTokens: Boolean, val isAvailableDapp: Boolean, val isReleased: Boolean) {
    ETH(CoinType.ETHEREUM, true, true, true),
    CLO(CoinType.CALLISTO, true, true, true),
    GO(CoinType.GOCHAIN, true, true, true),
    ETC(CoinType.ETHEREUMCLASSIC, true, true, true),
    POA(CoinType.POANETWORK, true, true, true),
    VET(CoinType.VECHAIN, true, false, true),
    WAN(CoinType.WANCHAIN, true, false, true),
    TRX(CoinType.TRON, false, false, true),
    ICX(CoinType.ICON, false, false, true),
    TOMO(CoinType.TOMOCHAIN, true, true, true),
    BTC(CoinType.BITCOIN, false, false, true),
    LTC(CoinType.LITECOIN, false, false, true),
    BCH(CoinType.BITCOINCASH, false, false, true),
    DASH(CoinType.DASH, false, false, true),
    ZCOIN(CoinType.ZCOIN, false, false, true),
    ZCASH(CoinType.ZCASH, false, false, true),
    BINANCE(CoinType.BINANCE, true, false, true),
    RIPPLE(CoinType.XRP, false, false, true),
    TEZOS(CoinType.TEZOS, false, false, true),
    STELLAR(CoinType.STELLAR, false, false, true),
    KIN(CoinType.KIN, false, false, true),
    AION(CoinType.AION, true, false, true),
    NIMIQ(CoinType.NIMIQ, false, false, true),
    THUNDERTOKEN(CoinType.THUNDERTOKEN, true, true, true),
    COSMOS(CoinType.COSMOS, false, false, true),
    DOGECOIN(CoinType.DOGECOIN, false, false, true),
    THETA(CoinType.THETA, true, false, true),
    ONTOLOGY(CoinType.ONTOLOGY, true, false, true),
    GROESTL(CoinType.GROESTLCOIN, false, false, true),
    VIACOIN(CoinType.VIACOIN, false, false, true),
    QTUM(CoinType.QTUM, false, false, true),
    ZELCASH(CoinType.ZELCASH, false, false, true),
    ZILLIQA(CoinType.ZILLIQA, false, false, true),
    IOTEX(CoinType.IOTEX, false, false, true),
    RAVEN(CoinType.RAVENCOIN, false, false, true),
    WAVES(CoinType.WAVES, false, false, true);

    private val purpose: Purpose

    init {
        this.purpose = coinType.purpose()
    }

    fun available(slipArr: Array<Slip>): Boolean {
        for (slip in slipArr) {
            if (slip == this) {
                return true
            }
        }
        return false
    }

    fun chainId(): Int {
        when (this) {
            Slip.ETH -> return 1
            Slip.CLO -> return 820
            Slip.GO -> return 60
            Slip.ETC -> return 61
            Slip.POA -> return 99
            Slip.VET -> return 74
            Slip.TOMO -> return 88
            Slip.AION, Slip.THETA -> return 0
            Slip.THUNDERTOKEN -> return 108
            else -> return 1
        }
    }

    fun coinAddress(): String {
        return PriceAddress.byCoin(this)
    }

    fun coinAsset(account: Account, z: Boolean): Asset {
        return Asset(1, Contract(account.zeroAddress(), coinName(), unit(), this), account, z, false)
    }

    fun coinName(): String {
        return CoinTypeConfiguration.getName(this.coinType)
    }

    fun coinType(): CoinType {
        return this.coinType
    }

    fun extractAddress(str: String): String {
        return AddressExtractor.extractAddress(this, str)
    }

    fun feeCalculator(): FeeCalculator {
        return FeeCalculatorAdapter.byCoin(this)
    }

    fun getTokenSymbol(coinType: CoinType): String {
        when (coinType) {
            CoinType.ETHEREUM -> return "ERC%s20"
            CoinType.VECHAIN -> return "VIP%s180"
            CoinType.TRON -> return "TRC%s10"
            CoinType.TOMOCHAIN -> return "TRC%s20"
            CoinType.BINANCE -> return "BEP%s2"
            else -> {
                val stringBuilder = StringBuilder()
                stringBuilder.append(CoinTypeConfiguration.getSymbol(coinType))
                stringBuilder.append("%s20")
                return stringBuilder.toString()
            }
        }
    }

    fun isValid(str: String): Boolean {
        return this.coinType.validate(str)
    }

    fun purpose(): Purpose {
        return this.purpose
    }

    fun toAddress(str: String): Address {
        return AddressBuilder.create(this, str)
    }

    fun unit(): Unit {
        val coinType = this.coinType
        return Unit(coinType, getTokenSymbol(coinType))
    }

    fun id() : String {
        return CoinTypeConfiguration.getID(coinType());
    }

    companion object {

        @JvmStatic
        fun find(i: Int): Slip {
            for (slip in values()) {
                if (slip.coinType.value() == i) {
                    return slip
                }
            }
            val stringBuilder = StringBuilder()
            stringBuilder.append("Coin not found - CoinTypeValue: ")
            stringBuilder.append(i)
            throw IllegalArgumentException(stringBuilder.toString())
        }

        @JvmStatic
        fun available(): Array<Slip> {
            val arrayList = ArrayList<Slip>()
            for (slip in values()) {
                if (slip.isReleased) {
                    arrayList.add(slip)
                }
            }
            return arrayList.toTypedArray()
        }

        @JvmStatic
        fun find(coinType: CoinType): Slip {
            for (slip in values()) {
                if (slip.coinType == coinType) {
                    return slip
                }
            }
            throw IllegalArgumentException("Coin not found")
        }
    }
}
