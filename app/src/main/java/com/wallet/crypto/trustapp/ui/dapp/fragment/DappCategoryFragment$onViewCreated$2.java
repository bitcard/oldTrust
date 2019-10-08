package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

/* compiled from: DappCategoryFragment.kt */
final class DappCategoryFragment$onViewCreated$2 implements OnRefreshListener {
    /* renamed from: a */
    final /* synthetic */ DappCategoryFragment f19702a;

    DappCategoryFragment$onViewCreated$2(DappCategoryFragment dappCategoryFragment) {
        this.f19702a = dappCategoryFragment;
    }

    public final void onRefresh() {
        this.f19702a.getViewModel$v1_7_010_s3Release().refresh();
    }
}
