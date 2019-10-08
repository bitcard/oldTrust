package com.wallet.crypto.trustapp.ui.dapp.entity

import com.wallet.crypto.trustapp.entity.Dapp
import com.wallet.crypto.trustapp.entity.ViewData
import kotlin.jvm.internal.Intrinsics

/* compiled from: DappViewData.kt */
class DappViewData(/* renamed from: c */
        private val weight: Int, /* renamed from: d */
        val dapp: Dapp) : ViewData {

    override fun areContentsTheSame(other: ViewData): Boolean {
        if (other != null) {
            val dapp = (other as DappViewData).dapp
            return Intrinsics.areEqual(this.dapp.id, this.dapp.id) && Intrinsics.areEqual(dapp.name, dapp.name) && Intrinsics.areEqual(dapp.image, dapp.image) && Intrinsics.areEqual(dapp.url, dapp.url)
        } else {
            throw TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData")
        }
    }

    override fun areItemsTheSame(other: ViewData): Boolean {
        return other is DappViewData
    }

    override fun compare(other: ViewData): Int {
        return getWeight() - other.getWeight()
    }

    override fun getViewType(): Int {
        return VIEW_TYPE
    }

    override fun getWeight(): Int {
        return this.weight
    }

    companion object {
        @JvmField
        /* renamed from: a */
        val VIEW_TYPE = 1011
    }
}
