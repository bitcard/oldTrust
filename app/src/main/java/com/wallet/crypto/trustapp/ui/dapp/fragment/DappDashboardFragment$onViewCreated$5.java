package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$onViewCreated$5 implements OnRefreshListener {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f19709a;

    DappDashboardFragment$onViewCreated$5(DappDashboardFragment dappDashboardFragment) {
        this.f19709a = dappDashboardFragment;
    }

    public final void onRefresh() {
        this.f19709a.getViewModel$v1_7_010_s3Release().refresh();
    }
}
