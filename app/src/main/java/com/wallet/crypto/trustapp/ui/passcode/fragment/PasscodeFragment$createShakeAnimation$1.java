package com.wallet.crypto.trustapp.ui.passcode.fragment;

import android.view.animation.Animation;
import com.wallet.crypto.trustapp.widget.SimpleAnimationListener;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PasscodeFragment.kt */
public final class PasscodeFragment$createShakeAnimation$1 extends SimpleAnimationListener {
    /* renamed from: a */
    final /* synthetic */ PasscodeFragment f19861a;

    PasscodeFragment$createShakeAnimation$1(PasscodeFragment passcodeFragment) {
        this.f19861a = passcodeFragment;
    }

    public void onAnimationEnd(Animation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        PasscodeFragment.access$getPinView$p(this.f19861a).clear();
        PasscodeFragment.access$getPinKeyboard$p(this.f19861a).unlock();
    }
}
