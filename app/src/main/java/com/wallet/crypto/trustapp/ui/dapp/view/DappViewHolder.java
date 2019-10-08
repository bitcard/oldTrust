package com.wallet.crypto.trustapp.ui.dapp.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappViewHolder.kt */
public final class DappViewHolder extends BinderViewHolder<DappViewData> implements OnClickListener {
    /* renamed from: t */
    private DappViewData f21376t;
    /* renamed from: u */
    private ImageView f21377u;
    /* renamed from: v */
    private TextView f21378v;
    /* renamed from: w */
    private TextView f21379w;
    /* renamed from: x */
    private final OnDappLinkClickLister f21380x;

    public DappViewHolder(int i, ViewGroup viewGroup, int i2, OnDappLinkClickLister onDappLinkClickLister) {
//        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
//        Intrinsics.checkParameterIsNotNull(onDappLinkClickLister, "onDappLinkClickLister");
        super(i, viewGroup);
        this.f21380x = onDappLinkClickLister;
        View findViewById = findViewById(R.id.image);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.image)");
        this.f21377u = (ImageView) findViewById;
        findViewById = findViewById(R.id.name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.name)");
        this.f21378v = (TextView) findViewById;
        findViewById = findViewById(R.id.description);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.description)");
        this.f21379w = (TextView) findViewById;
        findViewById = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView");
        LayoutParams layoutParams = findViewById.getLayoutParams();
        layoutParams.width = i2;
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        view.setLayoutParams(layoutParams);
        this.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        DappViewData dappViewData = this.f21376t;
        if (dappViewData != null) {
            this.f21380x.onDappLinkClick(dappViewData.getDapp().getUrl(), dappViewData.getDapp().getCoin());
        }
    }

    public void bind(DappViewData dappViewData, Bundle addition) {
        Intrinsics.checkParameterIsNotNull(addition, "addition");
        this.f21376t = dappViewData;
        if (dappViewData != null) {
            GlideRequest load = GlideApp.with((View) this.f21377u).load(dappViewData.getDapp().getImage());
            load.centerInside();
            load.transform(new MultiTransformation(new CenterInside(), new RoundedCorners(16)));
            load.error(R.drawable.icon_dapp_playceholder);
            load.into(this.f21377u);
            this.f21378v.setText(dappViewData.getDapp().getName());
            this.f21379w.setText(dappViewData.getDapp().getDescription());
        }
    }
}
