package com.wallet.crypto.trustapp.ui.collection.view;

import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesItemsAdapter.kt */
public final class CollectiblesItemsAdapter$items$1 extends Callback<CollectiblesItem> {
    /* renamed from: a */
    final /* synthetic */ CollectiblesItemsAdapter f21346a;

    CollectiblesItemsAdapter$items$1(CollectiblesItemsAdapter collectiblesItemsAdapter) {
        this.f21346a = collectiblesItemsAdapter;
    }

    public boolean areContentsTheSame(CollectiblesItem collectiblesItem, CollectiblesItem collectiblesItem2) {
        Intrinsics.checkParameterIsNotNull(collectiblesItem, "oldItem");
        Intrinsics.checkParameterIsNotNull(collectiblesItem2, "newItem");
        return true;
    }

    public void onChanged(int i, int i2) {
        this.f21346a.notifyItemRangeChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.f21346a.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.f21346a.notifyItemRangeRemoved(i, i2);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        this.f21346a.notifyItemMoved(fromPosition, toPosition);
    }

    public boolean areItemsTheSame(CollectiblesItem collectiblesItem, CollectiblesItem collectiblesItem2) {
        Intrinsics.checkParameterIsNotNull(collectiblesItem, "item1");
        Intrinsics.checkParameterIsNotNull(collectiblesItem2, "item2");
        return collectiblesItem.getId().equals(collectiblesItem2.getId());
    }

    public int compare(CollectiblesItem collectiblesItem, CollectiblesItem collectiblesItem2) {
        Intrinsics.checkParameterIsNotNull(collectiblesItem, "o1");
        Intrinsics.checkParameterIsNotNull(collectiblesItem2, "o2");
        return collectiblesItem.getId().compareTo(collectiblesItem2.getId());
    }
}
