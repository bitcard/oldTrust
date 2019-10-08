package com.wallet.crypto.trustapp.ui.assets.view;

import android.os.Bundle;
import android.view.ViewGroup;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class AssetHolder extends BinderViewHolder<AssetViewData> {
    /* renamed from: t */
    private final AssetBinder f21261t;

    public AssetHolder(int i, ViewGroup viewGroup, GlideRequests glideRequests) {
        super(i, viewGroup);
        this.f21261t = new AssetBinder(this.itemView, glideRequests);
    }

    public void setOnAssetClickListener(OnAssetClickListener onAssetClickListener) {
        this.f21261t.setOnAssetClickListener(onAssetClickListener);
    }

    public void bind(AssetViewData assetViewData, Bundle bundle) {
        this.f21261t.bind(assetViewData, bundle);
    }
}
