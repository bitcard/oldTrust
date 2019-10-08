package com.wallet.crypto.trustapp.ui.collection.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoryHolder.kt */
public final class CollectiblesCategoryHolder extends BinderViewHolder<CollectiblesCategory> implements OnClickListener {
    /* renamed from: A */
    private final GlideRequests f21332A;
    /* renamed from: t */
    private OnCollectiblesCategoryClickListener f21333t;
    /* renamed from: u */
    private ImageView f21334u;
    /* renamed from: v */
    private TextView f21335v;
    /* renamed from: w */
    private TextView f21336w;
    /* renamed from: x */
    private TextView f21337x;
    /* renamed from: y */
    private View f21338y;
    /* renamed from: z */
    private CollectiblesCategory f21339z;

    public CollectiblesCategoryHolder(int i, ViewGroup viewGroup, GlideRequests glideRequests) {
//        Intrinsics.checkParameterIsNotNull(glideRequests, "glideRequests");
        super(i, viewGroup);
        this.f21332A = glideRequests;
        View findViewById = findViewById(R.id.icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.icon)");
        this.f21334u = (ImageView) findViewById;
        findViewById = findViewById(R.id.symbol);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.symbol)");
        this.f21335v = (TextView) findViewById;
        findViewById = findViewById(R.id.annotation);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.annotation)");
        this.f21336w = (TextView) findViewById;
        findViewById = findViewById(R.id.collectibles_count);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.collectibles_count)");
        this.f21337x = (TextView) findViewById;
        findViewById = findViewById(R.id.stuff);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.stuff)");
        this.f21338y = findViewById;
        this.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        CollectiblesCategory collectiblesCategory = this.f21339z;
        if (collectiblesCategory != null) {
            OnCollectiblesCategoryClickListener onCollectiblesCategoryClickListener = this.f21333t;
            if (onCollectiblesCategoryClickListener != null) {
                if (collectiblesCategory != null) {
                    onCollectiblesCategoryClickListener.onCategoryClick(collectiblesCategory);
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            }
        }
    }

    public final void setOnCollectiblesCategoryClickListener(OnCollectiblesCategoryClickListener onCollectiblesCategoryClickListener) {
        this.f21333t = onCollectiblesCategoryClickListener;
    }

    public void bind(CollectiblesCategory collectiblesCategory, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "addition");
        if (collectiblesCategory != null) {
            this.f21339z = collectiblesCategory;
            GlideRequest asBitmap = this.f21332A.asBitmap();
            asBitmap.fitCenter();
            asBitmap.diskCacheStrategy(DiskCacheStrategy.ALL);
            asBitmap.placeholder(R.drawable.ic_main_wallet);
            asBitmap.load(collectiblesCategory.getImageUrl()).into(this.f21334u);
            this.f21335v.setText(collectiblesCategory.getName());
            this.f21336w.setText(collectiblesCategory.getDescription());
            this.f21337x.setText(String.valueOf(collectiblesCategory.getTotal()));
            if (TextUtils.isEmpty(collectiblesCategory.getDescription())) {
                this.f21338y.setVisibility(View.GONE);
            } else {
                this.f21338y.setVisibility(View.VISIBLE);
            }
        }
    }
}
