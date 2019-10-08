package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserView;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.p */
public final /* synthetic */ class C2423p implements Observer {
    /* renamed from: a */
    private final /* synthetic */ BrowserView f19718a;

    public /* synthetic */ C2423p(BrowserView browserView) {
        this.f19718a = browserView;
    }

    public final void onChanged(Object obj) {
        this.f19718a.onSignPersonalMessageSuccessful((SignedMessage) obj);
    }
}
