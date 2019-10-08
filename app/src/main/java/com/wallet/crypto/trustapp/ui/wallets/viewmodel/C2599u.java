package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import io.reactivex.functions.Action;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.u */
public final /* synthetic */ class C2599u implements Action {
    /* renamed from: a */
    private final /* synthetic */ WalletsViewModel f20164a;

    public /* synthetic */ C2599u(WalletsViewModel walletsViewModel) {
        this.f20164a = walletsViewModel;
    }

    public final void run() {
        this.f20164a.fetch(false);
    }
}
