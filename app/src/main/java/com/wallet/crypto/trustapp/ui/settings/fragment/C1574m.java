package com.wallet.crypto.trustapp.ui.settings.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.fragment.m */
public final /* synthetic */ class C1574m implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ SettingsFragment f16970a;

    public /* synthetic */ C1574m(SettingsFragment settingsFragment) {
        this.f16970a = settingsFragment;
    }

    public final void onClick(View view) {
        this.f16970a.f22217c.toggleRequestPinOnSending();
    }
}
