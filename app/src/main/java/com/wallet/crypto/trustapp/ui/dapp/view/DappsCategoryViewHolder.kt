package com.wallet.crypto.trustapp.ui.dapp.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.ViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData
import com.wallet.crypto.trustapp.widget.BinderViewHolder
import kotlin.jvm.internal.Intrinsics

/* compiled from: DappsCategoryViewHolder.kt */
class DappsCategoryViewHolder(i: Int, parent: ViewGroup, /* renamed from: w */
                              private val onDappCategoryClickListener: OnDappCategoryClickListener, onDappLinkClickLister: OnDappLinkClickLister) : BinderViewHolder<DappCategoryViewData>(i, parent) {
    /* renamed from: t */
    private val f21381t: TextView
    /* renamed from: u */
    private val f21382u: DashboardCategoryAdapter
    /* renamed from: v */
    private var data: DappCategoryViewData? = null

    /* compiled from: DappsCategoryViewHolder.kt */
    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.view.DappsCategoryViewHolder$1 */
    internal class C15341(/* renamed from: a */
            val f16894a:/* synthetic */ DappsCategoryViewHolder) : OnClickListener {

        override fun onClick(view: View) {
            val `access$getData$p` = this.f16894a.data
            if (`access$getData$p` != null) {
                val categoryId = `access$getData$p`.categoryId
                if (categoryId != null) {
                    this.f16894a.onDappCategoryClickListener.onCategory(categoryId)
                }
            }
        }
    }

    init {
        this.f21381t = findViewById<View>(R.id.name) as TextView
        val context = context
        this.f21382u = DashboardCategoryAdapter(context, 0.9f, onDappLinkClickLister)
        val sublist = findViewById<View>(R.id.sublist) as RecyclerView
        sublist.layoutManager = GridLayoutManager(sublist.context, 3, RecyclerView.HORIZONTAL, false)
        sublist.adapter = this.f21382u
        findViewById<View>(R.id.action_open).setOnClickListener(C15341(this))
    }

    override fun bind(dappCategoryViewData: DappCategoryViewData?, addition: Bundle) {
        this.data = dappCategoryViewData
        if (this.data != null) {
            this.f21381t.text = dappCategoryViewData?.name
            this.f21382u.set(dappCategoryViewData?.categoryId!!, dappCategoryViewData.items as Array<ViewData>)
        }
    }
}
