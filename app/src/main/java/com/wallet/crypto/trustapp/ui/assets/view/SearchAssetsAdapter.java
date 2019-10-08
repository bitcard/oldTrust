package com.wallet.crypto.trustapp.ui.assets.view;

import android.view.ViewGroup;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchAssetsAdapter.kt */
public final class SearchAssetsAdapter extends BaseAssetAdapter {
    /* renamed from: f */
    private final OnAssetClickListener f22096f;

    public SearchAssetsAdapter(GlideRequests glideRequests, OnAssetClickListener onAssetClickListener, OnAssetClickListener onAssetClickListener2) {
        super(glideRequests, onAssetClickListener);
        this.f22096f = onAssetClickListener2;
    }

    @Override
    public void onBindViewHolder(BinderViewHolder<? extends ViewData> binderViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(binderViewHolder, "holder");
        ViewData viewData = (ViewData) getItems().get(i);
        if (binderViewHolder instanceof ManageableAssetHolder) {
            ManageableAssetHolder manageableAssetHolder = (ManageableAssetHolder) binderViewHolder;
            if (viewData != null) {
                manageableAssetHolder.bind((AssetViewData) viewData);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData");
        }
        throw new IllegalArgumentException();
    }

    @Override
    public BinderViewHolder<? extends ViewData> onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        if (i == 5001) {
            return new ManageableAssetHolder(R.layout.item_token_manage, parent, getGlideRequests(), getOnAssetClickListener(), this.f22096f);
        }
        throw new IllegalArgumentException();
    }
}
