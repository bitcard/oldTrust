package com.wallet.crypto.trustapp.ui.wallets.view;

import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtendedKeyAdapter.kt */
public final class ExtendedKeyAdapter$items$1 extends Callback<ExtendedKeyViewData> {
    /* renamed from: a */
    final /* synthetic */ ExtendedKeyAdapter f21566a;

    ExtendedKeyAdapter$items$1(ExtendedKeyAdapter extendedKeyAdapter) {
        this.f21566a = extendedKeyAdapter;
    }

    public void onChanged(int i, int i2) {
        this.f21566a.notifyItemRangeChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.f21566a.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.f21566a.notifyItemRangeRemoved(i, i2);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        this.f21566a.notifyItemMoved(fromPosition, toPosition);
    }

    public boolean areContentsTheSame(ExtendedKeyViewData extendedKeyViewData, ExtendedKeyViewData extendedKeyViewData2) {
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData, "oldItem");
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData2, "newItem");
        return extendedKeyViewData.areContentsTheSame(extendedKeyViewData2);
    }

    public boolean areItemsTheSame(ExtendedKeyViewData extendedKeyViewData, ExtendedKeyViewData extendedKeyViewData2) {
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData, "item1");
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData2, "item2");
        return extendedKeyViewData.areItemsTheSame(extendedKeyViewData2);
    }

    public int compare(ExtendedKeyViewData extendedKeyViewData, ExtendedKeyViewData extendedKeyViewData2) {
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData, "o1");
        Intrinsics.checkParameterIsNotNull(extendedKeyViewData2, "o2");
        return extendedKeyViewData.compare(extendedKeyViewData2);
    }
}
