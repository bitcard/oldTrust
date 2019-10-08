package com.wallet.crypto.trustapp.ui.dex.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LotsFragment.kt */
final class LotsFragment$onActivityCreated$3<T> implements Observer<Boolean> {
    /* renamed from: a */
    final /* synthetic */ LotsFragment f19605a;

    LotsFragment$onActivityCreated$3(LotsFragment lotsFragment) {
        this.f19605a = lotsFragment;
    }

    public final void onChanged(Boolean bool) {
        SystemView access$getSystemView$p = LotsFragment.access$getSystemView$p(this.f19605a);
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        access$getSystemView$p.showProgress(bool.booleanValue());
    }
}
