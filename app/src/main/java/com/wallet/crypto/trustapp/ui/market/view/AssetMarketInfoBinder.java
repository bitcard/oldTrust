package com.wallet.crypto.trustapp.ui.market.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.R.id;
import com.wallet.crypto.trustapp.ui.market.entity.StockTickerViewData;
import com.wallet.crypto.trustapp.widget.Binder;

public class AssetMarketInfoBinder extends Binder<StockTickerViewData> {
    /* renamed from: b */
    private final TextView f19829b = ((TextView) findViewById(R.id.market_cap));
    /* renamed from: c */
    private final TextView f19830c = ((TextView) findViewById(R.id.volume));
    /* renamed from: d */
    private final TextView f19831d = ((TextView) findViewById(R.id.circulating_supply));
    /* renamed from: e */
    private final TextView f19832e = ((TextView) findViewById(R.id.total_supply));
    /* renamed from: f */
    private final TextView f19833f = ((TextView) findViewById(R.id.market_cap_title));
    /* renamed from: g */
    private final TextView f19834g = ((TextView) findViewById(R.id.volume_title));
    /* renamed from: h */
    private final TextView f19835h = ((TextView) findViewById(R.id.circulating_supply_title));
    /* renamed from: i */
    private final TextView f19836i = ((TextView) findViewById(R.id.total_supply_title));
    /* renamed from: j */
    private StockTickerViewData f19837j;

    public AssetMarketInfoBinder(View view) {
        super(view);
    }

    private void bindCirculatingSupply(String str) {
        TextView textView = this.f19831d;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindCirculatingSupplyTitle(String str) {
        TextView textView = this.f19835h;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindMarketCap(String str) {
        TextView textView = this.f19829b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindMarketCapTitle(String str) {
        TextView textView = this.f19833f;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindTotalSupply(String str) {
        TextView textView = this.f19832e;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindTotalSupplyTitle(String str) {
        TextView textView = this.f19836i;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindVolume(String str) {
        TextView textView = this.f19830c;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void bindVolumeTitle(String str) {
        TextView textView = this.f19834g;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void bind(StockTickerViewData stockTickerViewData, Bundle bundle) {
        this.f19837j = stockTickerViewData;
        if (stockTickerViewData != null) {
            bindMarketCap(stockTickerViewData.f19815e);
            bindVolume(stockTickerViewData.f19816f);
            bindCirculatingSupply(stockTickerViewData.f19817g);
            bindTotalSupply(stockTickerViewData.f19818h);
            bindMarketCapTitle(getString(R.string.MarketCap));
            bindVolumeTitle(getString(R.string.Volume24h));
            bindCirculatingSupplyTitle(getString(R.string.CirculatingSupply));
            bindTotalSupplyTitle(getString(R.string.TotalSupply));
        }
    }
}
