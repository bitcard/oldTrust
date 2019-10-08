package trust.blockchain

import kotlin.jvm.internal.Intrinsics

/* compiled from: CoinServiceAdapter.kt */
open class CoinServiceAdapter<T : CoinService>(val coinServices: Array<T>) {

    init {
        Intrinsics.checkParameterIsNotNull(coinServices, "coinServices")
    }

    fun getService(coin: Slip): T {
        for (coinService in this.coinServices) {
            if (coin.available(coinService.maintainedCoins)) {
                return coinService
            }
        }
        throw IllegalStateException("Coin doesn't supported")
    }
}
