package com.wallet.crypto.trustapp.ui.currency;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.currency.a */
public final /* synthetic */ class C2401a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ SystemView f19642a;

    public /* synthetic */ C2401a(SystemView systemView) {
        this.f19642a = systemView;
    }

    public final void onChanged(Object obj) {
        this.f19642a.showProgress(((Boolean) obj).booleanValue());
    }
}
