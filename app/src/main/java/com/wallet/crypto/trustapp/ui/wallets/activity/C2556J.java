package com.wallet.crypto.trustapp.ui.wallets.activity;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.J */
public final /* synthetic */ class C2556J implements OnRefreshListener {
    /* renamed from: a */
    private final /* synthetic */ WalletsActivity f20080a;

    public /* synthetic */ C2556J(WalletsActivity walletsActivity) {
        this.f20080a = walletsActivity;
    }

    public final void onRefresh() {
        this.f20080a.f23297d.fetch(true);
    }
}
