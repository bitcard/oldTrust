package com.wallet.crypto.trustapp.ui.passcode.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: PasscodeFragment.kt */
final class PasscodeFragment$showFingerprintDialog$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ PasscodeFragment f16922a;

    PasscodeFragment$showFingerprintDialog$1(PasscodeFragment passcodeFragment) {
        this.f16922a = passcodeFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PasscodeFragment.access$getFingerprintHelper$p(this.f16922a).stopListening();
    }
}
