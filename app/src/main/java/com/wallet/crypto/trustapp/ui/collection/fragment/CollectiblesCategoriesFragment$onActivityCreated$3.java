package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.ui.collection.view.CollectiblesCategoriesAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoriesFragment.kt */
final class CollectiblesCategoriesFragment$onActivityCreated$3<T> implements Observer<CollectiblesCategory[]> {
    /* renamed from: a */
    final /* synthetic */ CollectiblesCategoriesFragment f19595a;

    CollectiblesCategoriesFragment$onActivityCreated$3(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        this.f19595a = collectiblesCategoriesFragment;
    }

    public final void onChanged(CollectiblesCategory[] collectiblesCategoryArr) {
        CollectiblesCategoriesAdapter access$getAdapter$p = CollectiblesCategoriesFragment.access$getAdapter$p(this.f19595a);
        Intrinsics.checkExpressionValueIsNotNull(collectiblesCategoryArr, "it");
        access$getAdapter$p.setAssets(collectiblesCategoryArr);
    }
}
