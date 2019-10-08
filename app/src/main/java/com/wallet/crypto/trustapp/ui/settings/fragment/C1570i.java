package com.wallet.crypto.trustapp.ui.settings.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.fragment.i */
public final /* synthetic */ class C1570i implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ SettingsFragment f16966a;

    public /* synthetic */ C1570i(SettingsFragment settingsFragment) {
        this.f16966a = settingsFragment;
    }

    public final void onClick(View view) {
        this.f16966a.f22217c.togglePasscode(this.f16966a.getContext());
    }
}
