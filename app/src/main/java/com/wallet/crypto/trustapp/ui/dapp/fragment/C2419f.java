package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.DappLink;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.f */
public final /* synthetic */ class C2419f implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserFragment f19714a;

    public /* synthetic */ C2419f(BrowserFragment browserFragment) {
        this.f19714a = browserFragment;
    }

    public final void onChanged(Object obj) {
        BrowserFragment.m280b(this.f19714a, (DappLink) obj);
    }
}
