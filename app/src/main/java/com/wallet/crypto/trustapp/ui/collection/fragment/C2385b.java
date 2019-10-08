package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.collection.fragment.b */
public final /* synthetic */ class C2385b implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f19600a;

    public /* synthetic */ C2385b(SystemView systemView) {
        this.f19600a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f19600a.showProgress(((Boolean) obj).booleanValue());
    }
}
