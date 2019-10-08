package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappCategoryFragment.kt */
final class DappCategoryFragment$onViewCreated$3<T> implements Observer<Boolean> {
    /* renamed from: a */
    final /* synthetic */ DappCategoryFragment f19703a;

    DappCategoryFragment$onViewCreated$3(DappCategoryFragment dappCategoryFragment) {
        this.f19703a = dappCategoryFragment;
    }

    public final void onChanged(Boolean bool) {
        SystemView access$getSystemView$p = DappCategoryFragment.access$getSystemView$p(this.f19703a);
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        access$getSystemView$p.showProgress(bool.booleanValue());
    }
}
