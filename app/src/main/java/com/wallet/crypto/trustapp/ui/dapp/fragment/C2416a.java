package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.DappLink;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.a */
public final /* synthetic */ class C2416a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserFragment f19711a;

    public /* synthetic */ C2416a(BrowserFragment browserFragment) {
        this.f19711a = browserFragment;
    }

    public final void onChanged(Object obj) {
        BrowserFragment.m274a(this.f19711a, (DappLink) obj);
    }
}
