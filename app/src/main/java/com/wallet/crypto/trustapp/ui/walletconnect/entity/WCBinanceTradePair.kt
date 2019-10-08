package com.wallet.crypto.trustapp.ui.walletconnect.entity

/* compiled from: WCBinanceModels.kt */
data class WCBinanceTradePair(val from: String, val to: String) {

    companion object {
        fun from(symbol: String): WCBinanceTradePair? {
            val symbols = symbol.split("_")
            if (symbols.size <= 1) {
                return null
            }

            return WCBinanceTradePair(symbols[0].split("-")[0], symbols[1].split("-")[0])
        }
    }
}
