package com.wallet.crypto.trustapp.ui.dapp.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappCategoryFragment.kt */
final class DappCategoryFragment$onViewCreated$1<T> implements Observer<DappCategoryViewData> {
    /* renamed from: a */
    final /* synthetic */ DappCategoryFragment f19701a;

    DappCategoryFragment$onViewCreated$1(DappCategoryFragment dappCategoryFragment) {
        this.f19701a = dappCategoryFragment;
    }

    public final void onChanged(DappCategoryViewData dappCategoryViewData) {
        DappCategoryFragment dappCategoryFragment = this.f19701a;
        Intrinsics.checkExpressionValueIsNotNull(dappCategoryViewData, "it");
        dappCategoryFragment.setName(dappCategoryViewData, this.f19701a.getViewModel$v1_7_010_s3Release().getDappLinkType());
        DappCategoryFragment.access$getAdapter$p(this.f19701a).set("", (ViewData[]) dappCategoryViewData.getItems());
    }
}
