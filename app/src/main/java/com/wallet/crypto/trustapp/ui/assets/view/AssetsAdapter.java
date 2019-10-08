package com.wallet.crypto.trustapp.ui.assets.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetFooterViewData;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.entity.ListData;
import com.wallet.crypto.trustapp.ui.assets.entity.WalletInfoViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import com.wallet.crypto.trustapp.widget.ViewDataSortedList;
import java.util.Arrays;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.ServiceErrorException;

/* compiled from: AssetsAdapter.kt */
public final class AssetsAdapter extends Adapter<BinderViewHolder<? extends ViewData>> {
    /* renamed from: c */
    private final ViewDataSortedList items = new ViewDataSortedList(this);
    /* renamed from: d */
    private final GlideRequests f19538d;
    /* renamed from: e */
    private final OnAssetClickListener f19539e;

    public AssetsAdapter(GlideRequests glideRequests, OnAssetClickListener onAssetClickListener) {
        Intrinsics.checkParameterIsNotNull(glideRequests, "glideRequests");
        Intrinsics.checkParameterIsNotNull(onAssetClickListener, "onAssetClickListener");
        this.f19538d = glideRequests;
        this.f19539e = onAssetClickListener;
    }

    public final void clear() {
        this.items.clear();
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "items.get(position)");
        return ((ViewData) obj).getViewType();
    }

    public final void setData(ListData listData) {
        Intrinsics.checkParameterIsNotNull(listData, "data");
        this.items.beginBatchedUpdates();
        ViewDataSortedList viewDataSortedList = this.items;
        Object[] data = listData.getData();
        if (data == null) {
            data = new ViewData[0];
        }
        viewDataSortedList.replaceAll((ViewData[]) Arrays.copyOf(data, data.length));
        this.items.endBatchedUpdates();
    }

    public void onBindViewHolder(BinderViewHolder<? extends ViewData> binderViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(binderViewHolder, "holder");
        ViewData viewData = (ViewData) this.items.get(i);
        i = getItemViewType(i);
        if (i != 5001) {
            switch (i) {
                case ServiceErrorException.IV_OR_ALIAS_NO_ON_DISK /*1006*/:
                    WalletInfoViewHolder walletInfoViewHolder = (WalletInfoViewHolder) binderViewHolder;
                    if (viewData != null) {
                        walletInfoViewHolder.bind((WalletInfoViewData) viewData);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.assets.entity.WalletInfoViewData");
                case ServiceErrorException.INVALID_KEY /*1007*/:
                    AssetFooterHolder assetFooterHolder = (AssetFooterHolder) binderViewHolder;
                    if (viewData != null) {
                        assetFooterHolder.bind((AssetFooterViewData) viewData);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.assets.entity.AssetFooterViewData");
                default:
                    return;
            }
        }
        AssetHolder assetHolder = (AssetHolder) binderViewHolder;
        if (viewData != null) {
            assetHolder.bind((AssetViewData) viewData);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData");
    }

    public BinderViewHolder<? extends ViewData> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i != 5001) {
            switch (i) {
                case ServiceErrorException.IV_OR_ALIAS_NO_ON_DISK /*1006*/:
                    return new WalletInfoViewHolder(R.layout.item_total_balance, viewGroup);
                case ServiceErrorException.INVALID_KEY /*1007*/:
                    return new AssetFooterHolder(R.layout.item_tokens_footer, viewGroup);
                default:
                    throw new IllegalArgumentException();
            }
        }
        AssetHolder assetHolder = new AssetHolder(R.layout.item_token, viewGroup, this.f19538d);
        assetHolder.setOnAssetClickListener(this.f19539e);
        return assetHolder;
    }
}
