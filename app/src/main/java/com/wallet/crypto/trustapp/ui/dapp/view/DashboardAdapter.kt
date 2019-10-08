package com.wallet.crypto.trustapp.ui.dapp.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.ViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.FavoriteCategoryViewData
import com.wallet.crypto.trustapp.widget.BinderViewHolder
import com.wallet.crypto.trustapp.widget.ViewDataSortedList
import kotlin.jvm.internal.Intrinsics

/* compiled from: DashboardAdapter.kt */
class DashboardAdapter(/* renamed from: d */
        private val onDappCategoryClickListener: OnDappCategoryClickListener, /* renamed from: e */
        private val onDappLinkClickLister: OnDappLinkClickLister) : Adapter<BinderViewHolder<out ViewData>>() {
    /* renamed from: c */
    private val items = ViewDataSortedList(this)

    override fun getItemCount(): Int {
        return this.items.size()
    }

    override fun getItemViewType(position: Int): Int {
        return items.get(position).getViewType()
    }

    fun set(data: Array<ViewData>) {
        this.items.replaceAll(data, false)
    }

    override fun onBindViewHolder(holder: BinderViewHolder<out ViewData>, i: Int) {
        val viewData = this.items.get(i) as ViewData
        if (holder is FavoriteCategoryViewHolder) {
            holder.bind(viewData as FavoriteCategoryViewData)
        } else if (holder is DappsCategoryViewHolder) {
            holder.bind(viewData as DappCategoryViewData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): BinderViewHolder<out ViewData> {
        if (i == FavoriteCategoryViewData.VIEW_TYPE) {
            return FavoriteCategoryViewHolder(R.layout.item_favorite_category, parent, this.onDappCategoryClickListener, this.onDappLinkClickLister)
        }
        if (i == DappCategoryViewData.VIEW_TYPE) {
            return DappsCategoryViewHolder(R.layout.item_dapps_category, parent, this.onDappCategoryClickListener, this.onDappLinkClickLister)
        }
        throw IllegalArgumentException()
    }
}
