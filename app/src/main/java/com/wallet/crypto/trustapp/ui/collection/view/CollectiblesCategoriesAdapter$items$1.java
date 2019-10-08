package com.wallet.crypto.trustapp.ui.collection.view;

import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoriesAdapter.kt */
public final class CollectiblesCategoriesAdapter$items$1 extends Callback<CollectiblesCategory> {
    /* renamed from: a */
    final /* synthetic */ CollectiblesCategoriesAdapter f21331a;

    CollectiblesCategoriesAdapter$items$1(CollectiblesCategoriesAdapter collectiblesCategoriesAdapter) {
        this.f21331a = collectiblesCategoriesAdapter;
    }

    public void onChanged(int i, int i2) {
        this.f21331a.notifyItemRangeChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.f21331a.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.f21331a.notifyItemRangeRemoved(i, i2);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        this.f21331a.notifyItemMoved(fromPosition, toPosition);
    }

    public boolean areContentsTheSame(CollectiblesCategory collectiblesCategory, CollectiblesCategory collectiblesCategory2) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategory, "oldItem");
        Intrinsics.checkParameterIsNotNull(collectiblesCategory2, "newItem");
        return collectiblesCategory.getTotal() == collectiblesCategory2.getTotal();
    }

    public boolean areItemsTheSame(CollectiblesCategory collectiblesCategory, CollectiblesCategory collectiblesCategory2) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategory, "item1");
        Intrinsics.checkParameterIsNotNull(collectiblesCategory2, "item2");
        return collectiblesCategory.getName().equals(collectiblesCategory2.getName());
    }

    public int compare(CollectiblesCategory collectiblesCategory, CollectiblesCategory collectiblesCategory2) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategory, "o1");
        Intrinsics.checkParameterIsNotNull(collectiblesCategory2, "o2");
        return collectiblesCategory.getName().compareTo(collectiblesCategory2.getName());
    }
}
