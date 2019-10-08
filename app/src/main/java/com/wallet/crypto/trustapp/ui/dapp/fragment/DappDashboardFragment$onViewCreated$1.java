package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.dapp.view.DashboardAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$onViewCreated$1<T> implements Observer<ViewData[]> {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f19706a;

    DappDashboardFragment$onViewCreated$1(DappDashboardFragment dappDashboardFragment) {
        this.f19706a = dappDashboardFragment;
    }

    public final void onChanged(ViewData[] viewDataArr) {
        DashboardAdapter access$getAdapter$p = DappDashboardFragment.access$getAdapter$p(this.f19706a);
        Intrinsics.checkExpressionValueIsNotNull(viewDataArr, "it");
        access$getAdapter$p.set(viewDataArr);
    }
}
