package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

/* compiled from: CollectiblesCategoriesFragment.kt */
final class CollectiblesCategoriesFragment$onActivityCreated$4 implements OnRefreshListener {
    /* renamed from: a */
    final /* synthetic */ CollectiblesCategoriesFragment f19596a;

    CollectiblesCategoriesFragment$onActivityCreated$4(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        this.f19596a = collectiblesCategoriesFragment;
    }

    public final void onRefresh() {
        CollectiblesCategoriesFragment.access$getViewModel$p(this.f19596a).fetch(true);
    }
}
