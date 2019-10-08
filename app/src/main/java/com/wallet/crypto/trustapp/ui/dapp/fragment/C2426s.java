package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserView;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.s */
public final /* synthetic */ class C2426s implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserView f19721a;

    public /* synthetic */ C2426s(BrowserView browserView) {
        this.f19721a = browserView;
    }

    public final void onChanged(Object obj) {
        this.f19721a.onSignMessageSuccessful((SignedMessage) obj);
    }
}
