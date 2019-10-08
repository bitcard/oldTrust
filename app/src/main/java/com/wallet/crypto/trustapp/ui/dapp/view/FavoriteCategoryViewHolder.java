package com.wallet.crypto.trustapp.ui.dapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.DappLink.Type;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.dapp.entity.FavoriteCategoryViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FavoriteCategoryViewHolder.kt */
public final class FavoriteCategoryViewHolder extends BinderViewHolder<FavoriteCategoryViewData> {
    /* renamed from: t */
    private TextView f21386t;
    /* renamed from: u */
    private DashboardCategoryAdapter f21387u;
    /* renamed from: v */
    private final int f21388v;
    /* renamed from: w */
    private final ViewGroup parent;
    /* renamed from: x */
    private final OnDappCategoryClickListener onDappCategoryClickListener;
    /* renamed from: y */
    private final OnDappLinkClickLister onDappLinkClickLister;

    /* compiled from: FavoriteCategoryViewHolder.kt */
    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.view.FavoriteCategoryViewHolder$1 */
    static final class C15351 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ FavoriteCategoryViewHolder f16895a;

        C15351(FavoriteCategoryViewHolder favoriteCategoryViewHolder) {
            this.f16895a = favoriteCategoryViewHolder;
        }

        public final void onClick(View view) {
            this.f16895a.onDappCategoryClickListener.onCategory(Type.bookmark);
        }
    }

    public FavoriteCategoryViewHolder(int i, ViewGroup parent, OnDappCategoryClickListener onDappCategoryClickListener, OnDappLinkClickLister onDappLinkClickLister) {
        super(i, parent);
        this.f21388v = i;
        this.parent = parent;
        this.onDappCategoryClickListener = onDappCategoryClickListener;
        this.onDappLinkClickLister = onDappLinkClickLister;
        this.f21386t = (TextView) this.findViewById(R.id.name);
        this.f21387u = new DashboardCategoryAdapter(getContext(), 0.0f, this.onDappLinkClickLister);
        this.f21386t.setText(R.string.Bookmarks);
        RecyclerView sublist = (RecyclerView) findViewById(R.id.sublist);
        Intrinsics.checkExpressionValueIsNotNull(sublist, "sublist");
        sublist.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        sublist.setAdapter(this.f21387u);
        findViewById(R.id.action_open).setOnClickListener(new C15351(this));
    }

    public void bind(FavoriteCategoryViewData favoriteCategoryViewData, Bundle addition) {
        if (favoriteCategoryViewData != null) {
            DashboardCategoryAdapter dashboardCategoryAdapter = this.f21387u;
            String string = getString(R.string.Bookmarks);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.Bookmarks)");
            dashboardCategoryAdapter.set(string, (ViewData[]) favoriteCategoryViewData.getItems());
        }
    }
}
