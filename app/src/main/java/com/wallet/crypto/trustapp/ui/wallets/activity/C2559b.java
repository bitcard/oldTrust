package com.wallet.crypto.trustapp.ui.wallets.activity;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.b */
public final /* synthetic */ class C2559b implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f20092a;

    public /* synthetic */ C2559b(SystemView systemView) {
        this.f20092a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f20092a.showProgress(((Boolean) obj).booleanValue());
    }
}
