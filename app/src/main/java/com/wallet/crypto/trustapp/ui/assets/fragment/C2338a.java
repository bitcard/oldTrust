package com.wallet.crypto.trustapp.ui.assets.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.assets.fragment.a */
public final /* synthetic */ class C2338a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f19502a;

    public /* synthetic */ C2338a(SystemView systemView) {
        this.f19502a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f19502a.showProgress(((Boolean) obj).booleanValue());
    }
}
