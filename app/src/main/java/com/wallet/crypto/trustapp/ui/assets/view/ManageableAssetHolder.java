package com.wallet.crypto.trustapp.ui.assets.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.Erc20Drawable;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: ManageableAssetHolder.kt */
public final class ManageableAssetHolder extends BinderViewHolder<AssetViewData> implements OnCheckedChangeListener, OnClickListener {
    /* renamed from: A */
    private final OnAssetClickListener f21265A;
    /* renamed from: B */
    private final OnAssetClickListener f21266B;
    /* renamed from: t */
    private final Erc20Drawable f21267t = new Erc20Drawable(getContext());
    /* renamed from: u */
    private final GlideRequest<Bitmap> f21268u;
    /* renamed from: v */
    private final ImageView f21269v;
    /* renamed from: w */
    private final TextView f21270w;
    /* renamed from: x */
    private final TextView f21271x;
    /* renamed from: y */
    private final CheckBox f21272y;
    /* renamed from: z */
    private AssetViewData f21273z;

    public ManageableAssetHolder(int i, ViewGroup parent, GlideRequests glideRequests, OnAssetClickListener onAssetClickListener, OnAssetClickListener onAssetClickListener2) {
        super(i, parent);
        this.f21265A = onAssetClickListener;
        this.f21266B = onAssetClickListener2;
        View findViewById = findViewById(R.id.icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.icon)");
        this.f21269v = (ImageView) findViewById;
        findViewById = findViewById(R.id.title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.title)");
        this.f21270w = (TextView) findViewById;
        findViewById = findViewById(R.id.address);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.address)");
        this.f21271x = (TextView) findViewById;
        findViewById = findViewById(R.id.action_add);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_add)");
        this.f21272y = (CheckBox) findViewById;
        GlideRequest asBitmap = glideRequests.asBitmap();
        asBitmap.fitCenter();
        asBitmap.diskCacheStrategy(DiskCacheStrategy.ALL);
        asBitmap = asBitmap.clone();
        asBitmap.placeholder((Drawable) this.f21267t);
        asBitmap.error((Drawable) this.f21267t);
        Intrinsics.checkExpressionValueIsNotNull(asBitmap, "glideRequests\n          …  .error(iconPlaceholder)");
        this.f21268u = asBitmap;
        this.f21272y.setOnCheckedChangeListener(this);
        if (this.f21266B == null) {
            this.f21272y.setVisibility(View.GONE);
        }
        this.itemView.setOnClickListener(this);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        AssetViewData assetViewData = this.f21273z;
        if (assetViewData != null) {
            Asset asset = assetViewData.value;
            if (asset != null) {
                OnAssetClickListener onAssetClickListener = this.f21266B;
                if (onAssetClickListener != null) {
                    onAssetClickListener.onAssetClick(new Asset(asset.type, asset.contract, asset.account, assetViewData != null ? assetViewData.f19455m : true, asset.isAddedManually));
                }
            }
        }
    }

    public void onClick(View view) {
        AssetViewData assetViewData = this.f21273z;
        if (assetViewData != null) {
            Asset asset = assetViewData.value;
            if (asset != null) {
                OnAssetClickListener onAssetClickListener = this.f21265A;
                if (onAssetClickListener != null) {
                    onAssetClickListener.onAssetClick(asset);
                }
            }
        }
    }

    public void bind(AssetViewData data, Bundle addition) {
        Intrinsics.checkParameterIsNotNull(addition, "addition");
        this.f21273z = data;
        if (data != null) {
            int i;
            Asset asset = data.value;
            Intrinsics.checkExpressionValueIsNotNull(asset, "data.value");
            if ((asset.isCoin() || asset.isGas()) && !data.f19459q) {
                this.f21271x.setVisibility(View.GONE);
            } else {
                this.f21271x.setVisibility(View.VISIBLE);
            }

            this.f21270w.setText(data.f19445c);
            this.f21271x.setText(data.f19453k);
            this.f21268u.load(data.f19452j).into(this.f21269v);
            this.f21272y.setOnCheckedChangeListener(null);
            this.f21272y.setChecked(data.f19455m);
            this.f21272y.setOnCheckedChangeListener(this);
        }
    }
}
