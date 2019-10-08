package com.wallet.crypto.trustapp.ui.wallets.fragment;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/* compiled from: ConcentFragment.kt */
final class ConcentFragment$onViewCreated$1 implements OnCheckedChangeListener {
    /* renamed from: a */
    final /* synthetic */ ConcentFragment f17046a;

    ConcentFragment$onViewCreated$1(ConcentFragment concentFragment) {
        this.f17046a = concentFragment;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ConcentFragment.access$getNext$p(this.f17046a).setEnabled(z);
    }
}
