package com.wallet.crypto.trustapp.entity

import com.wallet.crypto.trustapp.CoinConfig
import java.util.concurrent.TimeUnit
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip

/* compiled from: TimeoutAdapter.kt */
class TimeoutAdapter private constructor() {

    val fasT_TIMEOUTS: TrustTimeout
        get() = FAST_TIMEOUTS

    val lengthY_TIMEOUTS: TrustTimeout
        get() = LENGTHY_TIMEOUTS

    fun timeoutFor(coin: Slip): TrustTimeout {
        if (CoinConfig.isUTXOBased(coin)) {
            return FAST_TIMEOUTS
        }
        if (coin !== Slip.RIPPLE) {
            if (coin !== Slip.BINANCE) {
                return LENGTHY_TIMEOUTS
            }
        }
        return FAST_TIMEOUTS
    }

    companion object {
        private val FAST_TIMEOUTS = TrustTimeout(Timeout(1, TimeUnit.MINUTES), Timeout(2, TimeUnit.MINUTES))
        val INSTANCE = TimeoutAdapter()
        private val LENGTHY_TIMEOUTS = TrustTimeout(Timeout(5, TimeUnit.MINUTES), Timeout(10, TimeUnit.MINUTES))
    }
}
