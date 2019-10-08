package com.wallet.crypto.trustapp.ui.collection.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesItemViewHolder.kt */
public final class CollectiblesItemViewHolder extends BinderViewHolder<CollectiblesItem> implements OnClickListener {
    /* renamed from: t */
    private OnCollectiblesItemClickListener f21340t;
    /* renamed from: u */
    private final ImageView f21341u;
    /* renamed from: v */
    private final TextView f21342v;
    /* renamed from: w */
    private final TextView f21343w;
    /* renamed from: x */
    private CollectiblesItem f21344x;
    /* renamed from: y */
    private final GlideRequests glideRequests;

    public CollectiblesItemViewHolder(int i, ViewGroup parent, GlideRequests glideRequests) {
        super(i, parent);
        this.glideRequests = glideRequests;
        View findViewById = findViewById(R.id.icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.icon)");
        this.f21341u = (ImageView) findViewById;
        findViewById = findViewById(R.id.symbol);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.symbol)");
        this.f21342v = (TextView) findViewById;
        findViewById = findViewById(R.id.annotation);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.annotation)");
        this.f21343w = (TextView) findViewById;
        this.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.f21344x != null) {
            OnCollectiblesItemClickListener onCollectiblesItemClickListener = this.f21340t;
            if (onCollectiblesItemClickListener != null) {
                View view2 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
                CollectiblesItem collectiblesItem = this.f21344x;
                if (collectiblesItem != null) {
                    onCollectiblesItemClickListener.onCollectiblesItemClick(view2, collectiblesItem);
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            }
        }
    }

    public final void setOnCollectiblesItemClickListener(OnCollectiblesItemClickListener onCollectiblesItemClickListener) {
        this.f21340t = onCollectiblesItemClickListener;
    }

    public void bind(CollectiblesItem collectiblesItem, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "addition");
        this.f21344x = collectiblesItem;
        if (collectiblesItem != null) {
            GlideRequest load = this.glideRequests.load(collectiblesItem.getImageUrl());
            load.placeholder(R.drawable.icon_dapp_playceholder);
            load.error(R.drawable.icon_dapp_playceholder);
            load.into(this.f21341u);
            this.f21342v.setText(collectiblesItem.getName());
            this.f21343w.setText(collectiblesItem.getDescription());
        }
    }
}
