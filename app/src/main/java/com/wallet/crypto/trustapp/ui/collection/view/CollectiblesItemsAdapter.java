package com.wallet.crypto.trustapp.ui.collection.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesItemsAdapter.kt */
public final class CollectiblesItemsAdapter extends Adapter<CollectiblesItemViewHolder> {
    /* renamed from: c */
    private final SortedList<CollectiblesItem> f19611c = new SortedList(CollectiblesItem.class, new CollectiblesItemsAdapter$items$1(this));
    /* renamed from: d */
    private final GlideRequests f19612d;
    /* renamed from: e */
    private final OnCollectiblesItemClickListener f19613e;

    public CollectiblesItemsAdapter(GlideRequests glideRequests, OnCollectiblesItemClickListener onCollectiblesItemClickListener) {
        Intrinsics.checkParameterIsNotNull(glideRequests, "glideRequests");
        Intrinsics.checkParameterIsNotNull(onCollectiblesItemClickListener, "onCollectiblesItemClickListener");
        this.f19612d = glideRequests;
        this.f19613e = onCollectiblesItemClickListener;
    }

    public final void clear() {
        this.f19611c.clear();
    }

    public int getItemCount() {
        return this.f19611c.size();
    }

    public final void onItems(CollectiblesItem[] collectiblesItemArr) {
        Intrinsics.checkParameterIsNotNull(collectiblesItemArr, "newItems");
        this.f19611c.beginBatchedUpdates();
        this.f19611c.replaceAll((CollectiblesItem[]) Arrays.copyOf(collectiblesItemArr, collectiblesItemArr.length));
        this.f19611c.endBatchedUpdates();
    }

    public void onBindViewHolder(CollectiblesItemViewHolder collectiblesItemViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(collectiblesItemViewHolder, "holder");
        collectiblesItemViewHolder.bind(this.f19611c.get(i));
    }

    public CollectiblesItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        CollectiblesItemViewHolder collectiblesItemViewHolder = new CollectiblesItemViewHolder(R.layout.item_collection, viewGroup, this.f19612d);
        collectiblesItemViewHolder.setOnCollectiblesItemClickListener(this.f19613e);
        return collectiblesItemViewHolder;
    }
}
