package com.wallet.crypto.trustapp.repository.wallet

import com.wallet.crypto.trustapp.util.CryptoUtils
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.WalletCryptographer

/* compiled from: TrustWalletCryptographer.kt */
class TrustWalletCryptographer : WalletCryptographer {
    override fun decrypt(data: ByteArray, password: String): ByteArray {
        return CryptoUtils.decrypt(data, password)
    }

    override fun encrypt(data: ByteArray, password: String): ByteArray {
        return CryptoUtils.encrypt(data, password)
    }
}
