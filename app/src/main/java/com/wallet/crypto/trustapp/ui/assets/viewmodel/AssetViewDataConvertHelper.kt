package com.wallet.crypto.trustapp.ui.assets.viewmodel

import android.text.Spannable
import com.wallet.crypto.trustapp.entity.AssetDescription
import com.wallet.crypto.trustapp.entity.AssetStatus
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData
import java.util.Arrays
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Contract
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.Ticker
import trust.blockchain.entity.Value

/* compiled from: AssetViewDataConvertHelper.kt */
class AssetViewDataConvertHelper(val formatter: AssetFormatter = BaseAssetFormatter()) {

    /* compiled from: AssetViewDataConvertHelper.kt */
    interface AssetFormatter {
        fun calculateBalance(contract: Contract, value: Value?): SubunitValue?

        fun createCoverUri(asset: Asset): String

        fun formatBalance(subunitValue: SubunitValue?): Spannable

        fun formatCurrencyBalance(ticker: Ticker?, subunitValue: SubunitValue?): Spannable

        fun formatName(asset: Asset): Spannable

        fun formatPrice(ticker: Ticker?): Spannable

        fun shouldShowCoinAddress(): Boolean
    }

    fun convert(asset: Asset): AssetViewData {
        return convert(asset, null, null)
    }

    fun convert(asset: Asset, session: Session?, assetStatus: AssetStatus?, z: Boolean = false): AssetViewData {
        val display: String?
        val assetViewData = AssetViewData(asset.id())
        val calculateBalance = this.formatter.calculateBalance(asset.contract, asset.balance)
        assetViewData.value = asset
        assetViewData.f19445c = this.formatter.formatName(asset)
        assetViewData.f19447e = AssetDescription.getTokenIdWithType(asset)
        assetViewData.f19449g = this.formatter.formatBalance(calculateBalance)
        assetViewData.f19450h = this.formatter.formatPrice(asset.ticker)
        assetViewData.f19451i = this.formatter.formatCurrencyBalance(asset.ticker, calculateBalance)
        assetViewData.f19452j = this.formatter.createCoverUri(asset)
        assetViewData.f19459q = this.formatter.shouldShowCoinAddress()
        assetViewData.f19454l = asset.type
        val contract2 = asset.contract
        assetViewData.f19448f = contract2.unit.symbol
        assetViewData.f19446d = contract2.address.display()
        assetViewData.f19455m = asset.isEnabled
        assetViewData.f19456n = asset.isAddedManually
        assetViewData.f19457o = z
        var z2 = false
        val objArr = arrayOf<Any>("")
        assetViewData.f19453k = if (asset.type == 1) "coin" else String.format(asset.contract.coin.unit().tokenSymbol, *Arrays.copyOf(objArr, objArr.size))
        if (session != null) {
            val wallet = session.wallet
            if (wallet != null) {
                val account = wallet.account(asset.coin())
                if (account != null) {
                    val address = account.address()
                    if (address != null) {
                        display = address.display()
                        assetViewData.f19460r = display
                        if (assetStatus != null && assetStatus.isBuyCryptoAvailable) {
                            z2 = true
                        }
                        assetViewData.f19461s = z2
                        return assetViewData
                    }
                }
            }
        }
        display = null
        assetViewData.f19460r = display
        z2 = true
        assetViewData.f19461s = z2
        return assetViewData
    }
}
