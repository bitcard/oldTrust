package com.wallet.crypto.trustapp.ui.dapp.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappLinkViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: DappLinkViewHolder.kt */
public final class DappLinkViewHolder extends BinderViewHolder<DappLinkViewData> implements OnClickListener {
    /* renamed from: t */
    private DappLinkViewData f21372t;
    /* renamed from: u */
    private final TextView f21373u;
    /* renamed from: v */
    private final ImageView f21374v;
    /* renamed from: w */
    private final OnDappLinkClickLister f21375w;

    public DappLinkViewHolder(int i, ViewGroup parent, OnDappLinkClickLister onDappLinkClickLister) {
//        Intrinsics.checkParameterIsNotNull(parent, "parent");
//        Intrinsics.checkParameterIsNotNull(onDappLinkClickLister, "onDappLinkClickLister");
        super(i, parent);
        this.f21375w = onDappLinkClickLister;
        View findViewById = findViewById(R.id.name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.name)");
        this.f21373u = (TextView) findViewById;
        findViewById = findViewById(R.id.icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.icon)");
        this.f21374v = (ImageView) findViewById;
        this.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        DappLinkViewData data = this.f21372t;
        if (data != null) {
            OnDappLinkClickLister onDappLinkClickLister = this.f21375w;
            String str = data.url;
            Intrinsics.checkExpressionValueIsNotNull(str, "data.url");
            Slip slip = data.coin;
            Intrinsics.checkExpressionValueIsNotNull(slip, "data.coin");
            onDappLinkClickLister.onDappLinkClick(str, slip);
        }
    }

    public void bind(DappLinkViewData dappLinkViewData, Bundle addition) {
        Intrinsics.checkParameterIsNotNull(addition, "addition");
        this.f21372t = dappLinkViewData;
        if (dappLinkViewData != null) {
            this.f21373u.setText(dappLinkViewData.f19665a);
            this.f21374v.setImageBitmap(null);
            GlideRequest load = GlideApp.with((View) this.f21374v).load(dappLinkViewData.f19669e);
            load.transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(16)));
            load.error(R.drawable.icon_dapp_playceholder);
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
            load.into(this.f21374v);
        }
    }
}
