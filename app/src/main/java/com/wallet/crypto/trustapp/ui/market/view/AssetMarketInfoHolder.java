package com.wallet.crypto.trustapp.ui.market.view;

import android.os.Bundle;
import android.view.ViewGroup;
import com.wallet.crypto.trustapp.ui.market.entity.StockTickerViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class AssetMarketInfoHolder extends BinderViewHolder<StockTickerViewData> {
    /* renamed from: t */
    private final AssetMarketInfoBinder f21420t = new AssetMarketInfoBinder(this.itemView);

    public AssetMarketInfoHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    public void bind(StockTickerViewData stockTickerViewData, Bundle bundle) {
        this.f21420t.bind(stockTickerViewData, bundle);
    }
}
