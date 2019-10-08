package com.wallet.crypto.trustapp.ui.settings.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.fragment.u */
public final /* synthetic */ class C1580u implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ SettingsFragment f16976a;

    public /* synthetic */ C1580u(SettingsFragment settingsFragment) {
        this.f16976a = settingsFragment;
    }

    public final void onClick(View view) {
        this.f16976a.f22217c.openWallets(this.f16976a.getContext());
    }
}
