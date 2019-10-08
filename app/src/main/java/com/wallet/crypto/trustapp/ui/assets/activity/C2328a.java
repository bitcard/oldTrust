package com.wallet.crypto.trustapp.ui.assets.activity;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.assets.activity.a */
public final /* synthetic */ class C2328a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f19433a;

    public /* synthetic */ C2328a(SystemView systemView) {
        this.f19433a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f19433a.showProgress(((Boolean) obj).booleanValue());
    }
}
