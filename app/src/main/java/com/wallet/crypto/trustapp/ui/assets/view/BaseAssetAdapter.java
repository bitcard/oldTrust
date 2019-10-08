package com.wallet.crypto.trustapp.ui.assets.view;

import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import com.wallet.crypto.trustapp.widget.ViewDataAdapter;
import com.wallet.crypto.trustapp.widget.ViewDataSortedList;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseAssetAdapter.kt */
public abstract class BaseAssetAdapter extends ViewDataAdapter<BinderViewHolder<? extends ViewData>> {
    /* renamed from: c */
    private final ViewDataSortedList items = new ViewDataSortedList(this);
    /* renamed from: d */
    private final GlideRequests f21263d;
    /* renamed from: e */
    private final OnAssetClickListener f21264e;

    protected BaseAssetAdapter(GlideRequests glideRequests, OnAssetClickListener onAssetClickListener) {
        Intrinsics.checkParameterIsNotNull(glideRequests, "glideRequests");
        Intrinsics.checkParameterIsNotNull(onAssetClickListener, "onAssetClickListener");
        this.f21263d = glideRequests;
        this.f21264e = onAssetClickListener;
    }

    protected final GlideRequests getGlideRequests() {
        return this.f21263d;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "items.get(position)");
        return ((ViewData) obj).getViewType();
    }

    protected final ViewDataSortedList getItems() {
        return this.items;
    }

    protected final OnAssetClickListener getOnAssetClickListener() {
        return this.f21264e;
    }

    public final void setAssets(ViewData[] newItems) {
        Intrinsics.checkParameterIsNotNull(newItems, "newItems");
        this.items.beginBatchedUpdates();
        this.items.replaceAll((ViewData[]) Arrays.copyOf(newItems, newItems.length));
        this.items.endBatchedUpdates();
    }
}
