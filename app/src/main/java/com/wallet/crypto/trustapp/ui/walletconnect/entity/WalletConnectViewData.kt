package com.wallet.crypto.trustapp.ui.walletconnect.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: WalletConnectViewData.kt */
data class WalletConnectViewData (val state: WCState,
                             val name: String,
                             val url: String,
                             val logoUrl: String,
                             val wcUrl: String,
                             val address: String,
                             val isAutoSigned: Boolean) {

    constructor(state: WCState, other: WalletConnectViewData) : this(state, other.name, other.url, other.logoUrl, other.wcUrl, other.address, other.isAutoSigned)
}
