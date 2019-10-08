package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.widget.SystemView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoriesFragment.kt */
final class CollectiblesCategoriesFragment$onActivityCreated$1<T> implements Observer<Boolean> {
    /* renamed from: a */
    final /* synthetic */ CollectiblesCategoriesFragment f19593a;

    CollectiblesCategoriesFragment$onActivityCreated$1(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        this.f19593a = collectiblesCategoriesFragment;
    }

    public final void onChanged(Boolean bool) {
        SystemView access$getSystemView$p = CollectiblesCategoriesFragment.access$getSystemView$p(this.f19593a);
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        access$getSystemView$p.showProgress(bool.booleanValue());
    }
}
