package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$onViewCreated$3<T> implements Observer<Boolean> {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f19707a;

    DappDashboardFragment$onViewCreated$3(DappDashboardFragment dappDashboardFragment) {
        this.f19707a = dappDashboardFragment;
    }

    public final void onChanged(Boolean bool) {
        SystemView access$getSystemView$p = DappDashboardFragment.access$getSystemView$p(this.f19707a);
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        access$getSystemView$p.showProgress(bool.booleanValue());
    }
}
