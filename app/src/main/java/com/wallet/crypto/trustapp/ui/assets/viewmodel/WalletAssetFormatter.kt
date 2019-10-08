package com.wallet.crypto.trustapp.ui.assets.viewmodel

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Contract
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.Value

/* compiled from: WalletAssetFormatter.kt */
class WalletAssetFormatter : BaseAssetFormatter() {
    override fun calculateBalance(contract: Contract, value: Value?): SubunitValue? {
        if (value == null) {
            return null
        }
        val subunitValue = SubunitValue(value, contract.unit)
        subunitValue.setShowSymbol(true)
        return subunitValue
    }
}
