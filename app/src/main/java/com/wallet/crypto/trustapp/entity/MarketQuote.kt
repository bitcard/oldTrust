package com.wallet.crypto.trustapp.entity

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics

/* compiled from: MarketQuote.kt */
class MarketQuote(private val average: String,
                  private val max: String,
                  private val slippage : Double = 0.0,
                  val info : Info?= null) {

    fun getAverage(tickSize: BigInteger): BigInteger {
        return try {
            BigInteger(this.average).divide(tickSize).multiply(tickSize)
        } catch (unused: Exception) {
            BigInteger.ZERO
        }

    }

    fun getMax(tickSize: BigInteger): BigInteger {
        return try {
            BigInteger(this.max).divide(tickSize).multiply(tickSize)
        } catch (unused: Exception) {
            BigInteger.ZERO
        }

    }

    override fun hashCode(): Int {
        val str = this.average
        var i = 0
        val hashCode = (str?.hashCode() ?: 0) * 31
        val str2 = this.max
        if (str2 != null) {
            i = str2.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("MarketQuote(average=")
        stringBuilder.append(this.average)
        stringBuilder.append(", max=")
        stringBuilder.append(this.max)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MarketQuote

        if (average != other.average) return false
        if (max != other.max) return false

        return true
    }

    companion object {
        val UNAVAILABLE = MarketQuote("0", "0")
    }
}
