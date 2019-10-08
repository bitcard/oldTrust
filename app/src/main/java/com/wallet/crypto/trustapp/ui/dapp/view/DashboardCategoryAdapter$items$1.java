package com.wallet.crypto.trustapp.ui.dapp.view;

import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.entity.ViewData;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashboardCategoryAdapter.kt */
public final class DashboardCategoryAdapter$items$1 extends Callback<ViewData> {
    /* renamed from: a */
    final /* synthetic */ DashboardCategoryAdapter f21385a;

    DashboardCategoryAdapter$items$1(DashboardCategoryAdapter dashboardCategoryAdapter) {
        this.f21385a = dashboardCategoryAdapter;
    }

    public void onChanged(int i, int i2) {
        this.f21385a.notifyItemRangeChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.f21385a.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.f21385a.notifyItemRangeRemoved(i, i2);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {

    }

    public boolean areContentsTheSame(ViewData viewData, ViewData viewData2) {
        Intrinsics.checkParameterIsNotNull(viewData, "oldItem");
        Intrinsics.checkParameterIsNotNull(viewData2, "newItem");
        return viewData.areContentsTheSame(viewData2);
    }

    public boolean areItemsTheSame(ViewData viewData, ViewData viewData2) {
        Intrinsics.checkParameterIsNotNull(viewData, "item1");
        Intrinsics.checkParameterIsNotNull(viewData2, "item2");
        return viewData.areItemsTheSame(viewData2);
    }

    public int compare(ViewData viewData, ViewData viewData2) {
        Intrinsics.checkParameterIsNotNull(viewData, "o1");
        Intrinsics.checkParameterIsNotNull(viewData2, "o2");
        return viewData.compare(viewData2);
    }
}
