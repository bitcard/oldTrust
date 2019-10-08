package com.wallet.crypto.trustapp.ui.dapp.entity

import com.wallet.crypto.trustapp.entity.ViewData
import kotlin.jvm.internal.Intrinsics

/* compiled from: DappCategoryViewData.kt */
class DappCategoryViewData(/* renamed from: c */
        val categoryId: String, /* renamed from: d */
        val name: String, /* renamed from: e */
        private val weight: Int, /* renamed from: f */
        val items: Array<DappViewData>) : ViewData {

    override fun areContentsTheSame(other: ViewData): Boolean {
        return false
    }

    override fun areItemsTheSame(other: ViewData): Boolean {
        return other is DappCategoryViewData && this.categoryId == other.categoryId
    }

    override fun compare(other: ViewData): Int {
        return getWeight() - other.getWeight()
    }

    override fun getViewType(): Int {
        return VIEW_TYPE
    }

    override fun getWeight(): Int {
        return this.weight + 10
    }

    companion object {
        /* renamed from: a */
        val VIEW_TYPE = 7003
    }
}
