package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserView;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.q */
public final /* synthetic */ class C2424q implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserView f19719a;

    public /* synthetic */ C2424q(BrowserView browserView) {
        this.f19719a = browserView;
    }

    public final void onChanged(Object obj) {
        this.f19719a.onSignTypedMessageSuccessful((SignedMessage) obj);
    }
}
