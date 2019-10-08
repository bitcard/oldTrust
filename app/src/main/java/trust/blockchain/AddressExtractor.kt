package trust.blockchain

import java.util.regex.Pattern
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address

/* compiled from: AddressExtractor.kt */
class AddressExtractor private constructor() {

    /* synthetic */ object WhenMappings {
        /* synthetic */ val `$EnumSwitchMapping$0` = IntArray(Slip.values().size)

        init {
            `$EnumSwitchMapping$0`[Slip.ETH.ordinal] = 1
            `$EnumSwitchMapping$0`[Slip.GO.ordinal] = 2
            `$EnumSwitchMapping$0`[Slip.CLO.ordinal] = 3
            `$EnumSwitchMapping$0`[Slip.ETC.ordinal] = 4
            `$EnumSwitchMapping$0`[Slip.POA.ordinal] = 5
            `$EnumSwitchMapping$0`[Slip.VET.ordinal] = 6
            `$EnumSwitchMapping$0`[Slip.WAN.ordinal] = 7
            `$EnumSwitchMapping$0`[Slip.THUNDERTOKEN.ordinal] = 8
            `$EnumSwitchMapping$0`[Slip.THETA.ordinal] = 9
            `$EnumSwitchMapping$0`[Slip.TOMO.ordinal] = 10
            `$EnumSwitchMapping$0`[Slip.ICX.ordinal] = 11
            `$EnumSwitchMapping$0`[Slip.TRX.ordinal] = 12
            `$EnumSwitchMapping$0`[Slip.DASH.ordinal] = 13
            `$EnumSwitchMapping$0`[Slip.BTC.ordinal] = 14
            `$EnumSwitchMapping$0`[Slip.LTC.ordinal] = 15
            `$EnumSwitchMapping$0`[Slip.BCH.ordinal] = 16
            `$EnumSwitchMapping$0`[Slip.ZCOIN.ordinal] = 17
            `$EnumSwitchMapping$0`[Slip.ZCASH.ordinal] = 18
            `$EnumSwitchMapping$0`[Slip.BINANCE.ordinal] = 19
            `$EnumSwitchMapping$0`[Slip.RIPPLE.ordinal] = 20
            `$EnumSwitchMapping$0`[Slip.TEZOS.ordinal] = 21
            `$EnumSwitchMapping$0`[Slip.STELLAR.ordinal] = 22
            `$EnumSwitchMapping$0`[Slip.KIN.ordinal] = 23
            `$EnumSwitchMapping$0`[Slip.NIMIQ.ordinal] = 24
            `$EnumSwitchMapping$0`[Slip.COSMOS.ordinal] = 25
            `$EnumSwitchMapping$0`[Slip.DOGECOIN.ordinal] = 26
            `$EnumSwitchMapping$0`[Slip.ONTOLOGY.ordinal] = 27
            `$EnumSwitchMapping$0`[Slip.GROESTL.ordinal] = 28
            `$EnumSwitchMapping$0`[Slip.QTUM.ordinal] = 29
            `$EnumSwitchMapping$0`[Slip.VIACOIN.ordinal] = 30
            `$EnumSwitchMapping$0`[Slip.ZELCASH.ordinal] = 31
            `$EnumSwitchMapping$0`[Slip.ZILLIQA.ordinal] = 32
            `$EnumSwitchMapping$0`[Slip.IOTEX.ordinal] = 33
            `$EnumSwitchMapping$0`[Slip.AION.ordinal] = 34
            `$EnumSwitchMapping$0`[Slip.WAVES.ordinal] = 35
            `$EnumSwitchMapping$0`[Slip.RAVEN.ordinal] = 36
        }
    }

    private fun extract(str: String, pattern: Pattern): String {
        var str = str
        val matcher = pattern.matcher(str)
        if (!matcher.find()) {
            return str
        }
        str = matcher.group(0)
        Intrinsics.checkExpressionValueIsNotNull(str, "matcher.group(0)")
        return str
    }

    companion object {
        val INSTANCE = AddressExtractor()

        fun extractAddress(coin: Slip, address: String): String {
            val addressExtractor: AddressExtractor
            val compile: Pattern
            when (WhenMappings.`$EnumSwitchMapping$0`[coin.ordinal]) {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> {
                    addressExtractor = INSTANCE
                    compile = Pattern.compile("0x[a-fA-F0-9]{40}")
                    Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"0x[a-fA-F0-9]{40}\")")
                    return addressExtractor.extract(address, compile)
                }
                11 -> {
                    addressExtractor = INSTANCE
                    compile = Pattern.compile("^(hx|cx)[a-fA-F0-9]{40}")
                    Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"^(hx|cx)[a-fA-F0-9]{40}\")")
                    return addressExtractor.extract(address, compile)
                }
                12 -> {
                    addressExtractor = INSTANCE
                    compile = Pattern.compile("T[1-9A-HJ-NP-Za-km-z]{33}")
                    Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"T[1-9A-HJ-NP-Za-km-z]{33}\")")
                    return addressExtractor.extract(address, compile)
                }
                13 -> {
                    addressExtractor = INSTANCE
                    compile = Pattern.compile("X[1-9A-HJ-NP-Za-km-z]{33}")
                    Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"X[1-9A-HJ-NP-Za-km-z]{33}\")")
                    return addressExtractor.extract(address, compile)
                }
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 -> return address
                else -> throw NoWhenBranchMatchedException()
            }
        }
    }
}
