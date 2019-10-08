package com.wallet.crypto.trustapp.ui.assets.viewmodel

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import com.wallet.crypto.trustapp.entity.AssetDescription
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetViewDataConvertHelper.AssetFormatter
import trust.blockchain.Slip
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Contract
import trust.blockchain.entity.CurrencyValue
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.Ticker
import trust.blockchain.entity.Unit
import trust.blockchain.entity.Value
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/* compiled from: BaseAssetFormatter.kt */
open class BaseAssetFormatter : AssetFormatter {
    /* renamed from: a */
    private val f19544a = Color.parseColor("#f7506c")
    /* renamed from: b */
    private val f19545b = Color.parseColor("#2fbb4f")

    override fun calculateBalance(contract: Contract, value: Value?): SubunitValue? {
        return if (value == null) null else SubunitValue(value, contract.unit)
    }

    override fun createCoverUri(asset: Asset): String {
        val objArr: Array<Any>
        val format: String?
        val i = asset.type
        if (i != 4) {
            when (i) {
                1 -> {
                    objArr = arrayOf(Integer.valueOf(asset.contract.coin.coinType().value()))
                    return String.format("file:///android_asset/coins/%s.png", *Arrays.copyOf(objArr, objArr.size))
                }
                2 -> {
                }
                else -> throw IllegalArgumentException()
            }
        }

        return String.format("https://assets.trustwalletapp.com/blockchains/%s/assets/%s/logo.png", asset.coin().id(), AssetDescription.getTokenId(asset).toLowerCase())
    }

    override fun formatBalance(subunitValue: SubunitValue?): Spannable {
        return SpannableString(if (subunitValue == null) "0.00" else subunitValue.mediumFormat("0.00", -1))
    }

    override fun formatCurrencyBalance(ticker: Ticker?, subunitValue: SubunitValue?): Spannable {
        if (!(subunitValue == null || ticker == null || "0".equals(subunitValue.mediumFormat("0", 0), true))) {
            if (!"0".equals(ticker.price, true)) {
                return SpannableString(CurrencyValue(subunitValue, ticker).shortFormat(getZero(ticker).toString(), 0))
            }
        }
        return SpannableString("")
    }

    override fun formatName(asset: Asset): Spannable {
        val str = if (TextUtils.isEmpty(asset.contract.name as CharSequence)) if (TextUtils.isEmpty(asset.contract.unit.symbol as CharSequence)) "" else asset.contract.unit.symbol else asset.contract.name
        return SpannableString(str)
    }

    override fun formatPrice(ticker: Ticker?): Spannable {
        if (ticker == null) {
            return SpannableString("")
        }
        val format = CurrencyValue(SubunitValue(1.0, Unit(0, "")), ticker).format(2, ',', "", 0)
        if (TextUtils.isEmpty(format)) {
            return SpannableString("")
        }
        val spannableString: Spannable
        val doubleValue = ticker.percentChange24h().toDouble()
        if (doubleValue == java.lang.Double.NaN) {
            spannableString = SpannableString(format)
        } else {
            val foregroundColorSpan: Any
            val stringBuilder: String
            val stringBuilder2: StringBuilder
            if (doubleValue < 0.toDouble()) {
                foregroundColorSpan = ForegroundColorSpan(this.f19544a)
                stringBuilder2 = StringBuilder()
                stringBuilder2.append(doubleValue)
                stringBuilder2.append('%')
                stringBuilder = stringBuilder2.toString()
            } else {
                foregroundColorSpan = ForegroundColorSpan(this.f19545b)
                stringBuilder2 = StringBuilder()
                stringBuilder2.append('+')
                stringBuilder2.append(doubleValue)
                stringBuilder2.append('%')
                stringBuilder = stringBuilder2.toString()
            }
            val stringBuilder3 = StringBuilder()
            stringBuilder3.append(format)
            stringBuilder3.append("\u3000")
            stringBuilder3.append(stringBuilder)
            val spannableString2 = SpannableString(stringBuilder3.toString())
            spannableString2.setSpan(foregroundColorSpan, spannableString2.length - stringBuilder.length, spannableString2.length, 33)
            spannableString = spannableString2
        }
        return spannableString
    }

    fun getZero(r4: Ticker?): CharSequence {
        var r0 = NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat
        r0.maximumFractionDigits = 2147483647
        val currentCode : String
        if (r4 == null || r4.currencyCode == null) {
            currentCode = Currency.getInstance(Locale.getDefault()).currencyCode
        } else {
            currentCode = r4.currencyCode
        }
        var currency = Currency.getInstance(currentCode)
        var formatter = NumberFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
        formatter.maximumFractionDigits = 2147483647
        formatter.currency = currency
        return formatter.format(0.00)
    }

    override fun shouldShowCoinAddress(): Boolean {
        return true
    }
}
