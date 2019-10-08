package com.wallet.crypto.trustapp.ui.settings.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.fragment.c */
public final /* synthetic */ class C1566c implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ SettingsFragment f16962a;

    public /* synthetic */ C1566c(SettingsFragment settingsFragment) {
        this.f16962a = settingsFragment;
    }

    public final void onClick(View view) {
        this.f16962a.f22217c.openTelegram(this.f16962a.getContext());
    }
}
