package com.wallet.crypto.trustapp.util

import android.content.Context
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.SharedPreferenceRepository
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.entity.Account
import trust.blockchain.entity.Wallet
import kotlin.TypeCastException
import com.wallet.crypto.trustapp.CoinConfig



/* compiled from: WalletUtils.kt */
class WalletUtils {
    /* compiled from: WalletUtils.kt */
    companion object {

        fun getWalletDexAccounts(session: Session): Array<Account> {
            return session.wallet.accounts.intersect(CoinConfig.getDexCoins().map {session.wallet.account(it)}).toTypedArray()
        }

        fun getWalletDexCoins(session: Session): Array<Slip> {
            return getWalletDexAccounts(session).map {it.coin}.toTypedArray()
        }

        @JvmStatic
        fun isDappAvailable(context: Context, session: Session): Boolean {
            var z: Boolean = false
            for (account in session.wallet.accounts) {
                if (account.coin.isAvailableDapp) {
                    z = true
                    break
                }
            }
            return (z && SharedPreferenceRepository(context).enableDapp)
        }

        @JvmStatic
        fun isDexAvailable(session: Session): Boolean {
            return if (session.wallet.type === 1) {
                false
            } else getWalletDexAccounts(session).isNotEmpty()
        }
    }
}
