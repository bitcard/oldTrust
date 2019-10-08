package com.wallet.crypto.trustapp.ui.dapp.entity

import com.wallet.crypto.trustapp.entity.ViewData
import kotlin.jvm.internal.Intrinsics

/* compiled from: FavoriteCategoryViewData.kt */
class FavoriteCategoryViewData(/* renamed from: c */
        val items: Array<DappLinkViewData>) : ViewData {

    override fun areContentsTheSame(newItem: ViewData): Boolean {
        return false
    }

    override fun areItemsTheSame(other: ViewData): Boolean {
        return other.getViewType() == getViewType()
    }

    override fun compare(other: ViewData): Int {
        return getWeight() - other.getWeight()
    }

    override fun getViewType(): Int {
        return VIEW_TYPE
    }

    override fun getWeight(): Int {
        return 0
    }

    companion object {
        /* renamed from: a */
        val VIEW_TYPE = 7001
    }
}
