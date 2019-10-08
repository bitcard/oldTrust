package com.wallet.crypto.trustapp.ui.transfer.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.transfer.fragment.a */
public final /* synthetic */ class C2530a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f20013a;

    public /* synthetic */ C2530a(SystemView systemView) {
        this.f20013a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f20013a.showProgress(((Boolean) obj).booleanValue());
    }
}
