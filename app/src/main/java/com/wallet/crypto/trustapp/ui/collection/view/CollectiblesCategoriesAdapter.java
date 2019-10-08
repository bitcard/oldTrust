package com.wallet.crypto.trustapp.ui.collection.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoriesAdapter.kt */
public final class CollectiblesCategoriesAdapter extends Adapter<CollectiblesCategoryHolder> {
    /* renamed from: c */
    private final SortedList<CollectiblesCategory> f19608c = new SortedList(CollectiblesCategory.class, new CollectiblesCategoriesAdapter$items$1(this));
    /* renamed from: d */
    private final GlideRequests f19609d;
    /* renamed from: e */
    private final OnCollectiblesCategoryClickListener f19610e;

    public CollectiblesCategoriesAdapter(GlideRequests glideRequests, OnCollectiblesCategoryClickListener onCollectiblesCategoryClickListener) {
        Intrinsics.checkParameterIsNotNull(glideRequests, "glideRequests");
        Intrinsics.checkParameterIsNotNull(onCollectiblesCategoryClickListener, "onCollectiblesCategoryClickListener");
        this.f19609d = glideRequests;
        this.f19610e = onCollectiblesCategoryClickListener;
    }

    public int getItemCount() {
        return this.f19608c.size();
    }

    public final void setAssets(CollectiblesCategory[] collectiblesCategoryArr) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategoryArr, "newItems");
        this.f19608c.beginBatchedUpdates();
        this.f19608c.replaceAll((CollectiblesCategory[]) Arrays.copyOf(collectiblesCategoryArr, collectiblesCategoryArr.length));
        this.f19608c.endBatchedUpdates();
    }

    public void onBindViewHolder(CollectiblesCategoryHolder collectiblesCategoryHolder, int i) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategoryHolder, "holder");
        collectiblesCategoryHolder.bind(this.f19608c.get(i));
    }

    public CollectiblesCategoryHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        CollectiblesCategoryHolder collectiblesCategoryHolder = new CollectiblesCategoryHolder(R.layout.item_categories, viewGroup, this.f19609d);
        collectiblesCategoryHolder.setOnCollectiblesCategoryClickListener(this.f19610e);
        return collectiblesCategoryHolder;
    }
}
