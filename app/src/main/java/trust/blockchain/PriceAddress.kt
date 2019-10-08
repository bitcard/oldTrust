package trust.blockchain

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Asset
import trust.blockchain.entity.PlainAddress

/* compiled from: PriceAddress.kt */
object PriceAddress {

//    /* compiled from: PriceAddress.kt */
//    class Companion private constructor() {
//
//        fun byAsset(asset: Asset): PlainAddress {
//            var byCoin: String?
//            Intrinsics.checkParameterIsNotNull(asset, "asset")
//            if (asset.isCoin) {
//                val companion = this
//                val coin = asset.coin()
//                Intrinsics.checkExpressionValueIsNotNull(coin, "asset.coin()")
//                byCoin = companion.byCoin(coin)
//            } else {
//                val coin2 = asset.coin()
//                if (coin2 != null) {
//                    if (coin2 == Slip.TRX) {
//                        byCoin = asset.contract.contract
//                        Intrinsics.checkExpressionValueIsNotNull(byCoin!!, "asset.contract.contract")
//                        if (byCoin != null) {
//                            byCoin = byCoin.toLowerCase()
//                            Intrinsics.checkExpressionValueIsNotNull(byCoin, "(this as java.lang.String).toLowerCase()")
//                        } else {
//                            throw TypeCastException("null cannot be cast to non-null type java.lang.String")
//                        }
//                    }
//                }
//                byCoin = asset.contract.address.toString()
//                if (byCoin != null) {
//                    byCoin = byCoin.toLowerCase()
//                    Intrinsics.checkExpressionValueIsNotNull(byCoin, "(this as java.lang.String).toLowerCase()")
//                } else {
//                    throw TypeCastException("null cannot be cast to non-null type java.lang.String")
//                }
//            }
//            return PlainAddress(byCoin)
//        }
//
//        fun byCoin(coin: Slip): String {
//            val bigInteger = BigInteger(coin.coinType().value().toString()).toString(16)
//            val stringBuilder = StringBuilder()
//            stringBuilder.append("0x")
//            stringBuilder.append("0".repeat(40 - bigInteger.length))
//            stringBuilder.append(bigInteger)
//            return stringBuilder.toString()
//        }
//    }

    @JvmStatic
    fun byAsset(asset: Asset): PlainAddress {
        val byCoin: String
        if (asset.isCoin) {
            byCoin = byCoin(asset.coin())
        } else {
            val coin2 = asset.coin()
            if (coin2 != null && coin2 == Slip.TRX) {
                byCoin = asset.contract.contract.toLowerCase()
            } else {
                byCoin = asset.contract.address.toString().toLowerCase()
            }
        }
        return PlainAddress(byCoin)
    }

    @JvmStatic
    fun byCoin(coin: Slip): String {
        val bigInteger = BigInteger(coin.coinType().value().toString()).toString(16)
        val stringBuilder = StringBuilder()
        stringBuilder.append("0x")
        stringBuilder.append("0".repeat(40 - bigInteger.length))
        stringBuilder.append(bigInteger)
        return stringBuilder.toString()
    }
}
