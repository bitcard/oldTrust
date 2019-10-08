package com.wallet.crypto.trustapp.entity

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Asset
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.SwapDirection

/* compiled from: Lot.kt */
class TradeAsset(val lotInfo: LotInfo?, val assets: Array<Asset>) {
    private var direction = SwapDirection.BUY

    fun getToAsset(): Asset {
        when (this.direction) {
            SwapDirection.BUY -> {
                return base()!!
            }
            SwapDirection.SELL -> {
                return quote()!!
            }
            SwapDirection.NONE -> throw IllegalStateException()
            else -> throw NoWhenBranchMatchedException()
        }
    }

    val isCompatible: Boolean
        get() {
            val base = base()
            val quote = quote()
            return !(base == null || quote == null || base.id() == quote.id())
        }

    fun getFromAsset(): Asset {
        when (this.direction) {
            SwapDirection.BUY -> {
                return quote()!!
            }
            SwapDirection.SELL -> {
                return base()!!
            }
            SwapDirection.NONE -> throw IllegalStateException()
            else -> throw NoWhenBranchMatchedException()
        }
    }

    fun base(): Asset? {
        val r0 = lotInfo?.baseAssetContract?: ""
        for (asset in assets) {
            if (asset.contract() == r0)
                return asset
        }
        return null
    }

    fun calculateOpposite(fromAmount: BigDecimal?, toAmount: BigDecimal?, bigDecimal3: BigDecimal?): BigDecimal {
        if (fromAmount == null && toAmount == null || bigDecimal3 == null) {
            return BigDecimal.ZERO
        }

        if (toAmount == null) {
            return if (this.direction == SwapDirection.BUY) {
                BigDecimal(getFromAsset().unit().toUnit(fromAmount)).divide(bigDecimal3, MathContext.DECIMAL64)
            } else {
                SubunitValue(fromAmount?.multiply(bigDecimal3), getToAsset().unit()).convert()
            }
        } else {
            return  if (this.direction == SwapDirection.BUY) {
                SubunitValue(toAmount.multiply(bigDecimal3), getFromAsset().unit()).convert()
            } else {
                BigDecimal(getToAsset().unit().toUnit(toAmount)).divide(bigDecimal3, MathContext.DECIMAL64)
            }
        }
    }

    @Throws(IllegalStateException::class)
    fun fromContract(): String {
        val lotInfo = this.lotInfo
        if (lotInfo != null) {
            when (this.direction) {
                SwapDirection.BUY -> return lotInfo.quoteAssetContract
                SwapDirection.SELL -> return lotInfo.baseAssetContract
                SwapDirection.NONE -> throw IllegalStateException()
                else -> throw NoWhenBranchMatchedException()
            }
        }
        throw IllegalStateException()
    }

    @Throws(IllegalStateException::class)
    fun fromSymbol(): String {
        val lotInfo = this.lotInfo
        if (lotInfo != null) {
            when (this.direction) {
                SwapDirection.BUY -> return lotInfo.quoteAssetSymbol
                SwapDirection.SELL -> return lotInfo.baseAssetSymbol
                SwapDirection.NONE -> throw IllegalStateException()
                else -> throw NoWhenBranchMatchedException()
            }
        }
        throw IllegalStateException()
    }

    fun getDirection(): SwapDirection {
        return this.direction
    }

    fun lotSize(): BigInteger {
        var lotSize: BigInteger?
        return this.lotInfo?.lotSize?: BigInteger.ZERO
    }

    fun opposite(opposite: Asset): Asset {
        for (asset2 in this.assets) {
            if (asset2.id() != opposite.id()) {
                return asset2
            }
        }
        return opposite
    }

    fun quote(): Asset? {
        val r0 = lotInfo?.quoteAssetContract?: ""
        assets.forEach {
            if (it.contract() == r0)
                return it
        }
        return null
    }

    fun round(asset: Asset?, lotSize: BigInteger, value: BigDecimal): String {
        if (!(value == BigInteger.ZERO || lotSize == BigInteger.ZERO || asset == null)) {
            if (asset.id() == base()?.id()) {
                return SubunitValue(asset.unit().toUnit(value).divide(lotSize).multiply(lotSize), asset.unit()).convert().stripTrailingZeros().toPlainString()
            }
        }
        return value.stripTrailingZeros().toPlainString()
    }

    fun setDirection(swapDirection: SwapDirection) {
        Intrinsics.checkParameterIsNotNull(swapDirection, "<set-?>")
        this.direction = swapDirection
    }

    @Throws(IllegalStateException::class)
    fun tickSize(): BigInteger {
        val lotInfo = this.lotInfo
        if (lotInfo != null) {
            val tickSize = lotInfo.tickSize
            if (tickSize != null) {
                return tickSize
            }
        }
        throw IllegalStateException()
    }

    @Throws(IllegalStateException::class)
    fun toContract(): String {
        val lotInfo = this.lotInfo
        if (lotInfo != null) {
            when (this.direction) {
                SwapDirection.BUY -> return lotInfo.baseAssetContract
                SwapDirection.SELL -> return lotInfo.quoteAssetContract
                SwapDirection.NONE -> throw IllegalStateException()
                else -> throw NoWhenBranchMatchedException()
            }
        }
        throw IllegalStateException()
    }

    @Throws(IllegalStateException::class)
    fun toSymbol(): String {
        val lotInfo = this.lotInfo
        if (lotInfo != null) {
            when (this.direction) {
                SwapDirection.BUY -> return lotInfo.baseAssetSymbol
                SwapDirection.SELL -> return lotInfo.quoteAssetSymbol
                SwapDirection.NONE -> throw IllegalStateException()
                else -> throw NoWhenBranchMatchedException()
            }
        }
        throw IllegalStateException()
    }

    fun setDirection(asset: Asset) {
        val swapDirection: SwapDirection
        Intrinsics.checkParameterIsNotNull(asset, "opposite")
        val quote = quote()
        if (quote == null) {
            swapDirection = SwapDirection.NONE
        } else if (Intrinsics.areEqual(quote.id(), asset.id())) {
            swapDirection = SwapDirection.BUY
        } else {
            swapDirection = SwapDirection.SELL
        }
        this.direction = swapDirection
    }
}
