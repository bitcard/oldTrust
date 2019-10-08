package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.r */
public final /* synthetic */ class C2425r implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserFragment f19720a;

    public /* synthetic */ C2425r(BrowserFragment browserFragment) {
        this.f19720a = browserFragment;
    }

    public final void onChanged(Object obj) {
        this.f19720a.onError((Throwable) obj);
    }
}
