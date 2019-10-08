package com.wallet.crypto.trustapp.ui.receive.activity;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.activity.a */
public final /* synthetic */ class C2467a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f19875a;

    public /* synthetic */ C2467a(SystemView systemView) {
        this.f19875a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f19875a.showProgress(((Boolean) obj).booleanValue());
    }
}
