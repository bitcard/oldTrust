package com.wallet.crypto.trustapp.ui.assets.viewmodel

import android.text.Spannable
import android.text.SpannableString
import trust.blockchain.entity.CurrencyValue
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.Ticker

/* compiled from: DetailsAssetFormatter.kt */
class DetailsAssetFormatter : BaseAssetFormatter() {
    override fun formatBalance(subunitValue: SubunitValue?): Spannable {
        return SpannableString(if (subunitValue == null) "0.00" else subunitValue.fullFormat("0.00", -1))
    }

    override fun formatCurrencyBalance(ticker: Ticker?, subunitValue: SubunitValue?): Spannable {
        if (!(subunitValue == null || ticker == null || "0".equals(subunitValue.mediumFormat("0", 0), true))) {
            if (!"0".equals(ticker.price, true)) {
                val shortFormat = CurrencyValue(subunitValue, ticker).shortFormat(getZero(ticker).toString(), 0)
                val stringBuilder = StringBuilder()
                stringBuilder.append("≈ ")
                stringBuilder.append(shortFormat)
                return SpannableString(stringBuilder.toString())
            }
        }
        return SpannableString("")
    }
}
