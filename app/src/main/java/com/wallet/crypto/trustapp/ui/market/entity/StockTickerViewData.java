package com.wallet.crypto.trustapp.ui.market.entity;

import android.util.SparseArray;
import com.wallet.crypto.trustapp.entity.StockTicker;
import com.wallet.crypto.trustapp.entity.ViewData;

public class StockTickerViewData implements ViewData {
    /* renamed from: a */
    public int f19811a;
    /* renamed from: b */
    public String f19812b;
    /* renamed from: c */
    public String f19813c;
    /* renamed from: d */
    public String f19814d;
    /* renamed from: e */
    public String f19815e;
    /* renamed from: f */
    public String f19816f;
    /* renamed from: g */
    public String f19817g;
    /* renamed from: h */
    public String f19818h;
    /* renamed from: i */
    public String f19819i;
    /* renamed from: j */
    public SparseArray<Object> f19820j;
    /* renamed from: k */
    public int f19821k;
    /* renamed from: l */
    public int f19822l;
    /* renamed from: m */
    public String f19823m;

    public StockTickerViewData(StockTicker stockTicker) {
        this.f19811a = stockTicker.id;
        this.f19812b = stockTicker.name;
        this.f19813c = stockTicker.symbol;
        this.f19814d = stockTicker.price;
        this.f19815e = stockTicker.marketCap;
        this.f19816f = stockTicker.volume24h;
        this.f19819i = stockTicker.websiteSlug;
        this.f19817g = stockTicker.circulatingSupply;
        this.f19818h = stockTicker.totalSupply;
        this.f19820j = stockTicker.percentage;
        this.f19821k = stockTicker.rank;
        this.f19822l = stockTicker.coin;
        this.f19823m = stockTicker.contract;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        StockTickerViewData stockTickerViewData = (StockTickerViewData) viewData;
        boolean z = this.f19813c == stockTickerViewData.f19813c;
        if ((this.f19812b == stockTickerViewData.f19812b) && z) {
            return true;
        }
        return false;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        boolean z = false;
        if (viewData.getViewType() != getViewType()) {
            return false;
        }
        if (this.f19811a == ((StockTickerViewData) viewData).f19811a) {
            z = true;
        }
        return z;
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() != 5003) {
            return 1;
        }
        if (this.f19811a == ((StockTickerViewData) viewData).f19811a) {
            return 0;
        }
        return 1;
    }

    public int getViewType() {
        return 5003;
    }

    public int getWeight() {
        return 0;
    }
}
