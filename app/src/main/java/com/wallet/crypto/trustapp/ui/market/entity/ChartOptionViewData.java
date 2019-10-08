package com.wallet.crypto.trustapp.ui.market.entity;

import com.wallet.crypto.trustapp.entity.StockTickerFrame;
import com.wallet.crypto.trustapp.entity.ViewData;

public class ChartOptionViewData implements ViewData {
    /* renamed from: a */
    public StockTickerFrame f19810a;

    public ChartOptionViewData(StockTickerFrame stockTickerFrame) {
        this.f19810a = stockTickerFrame;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        return this.f19810a == ((ChartOptionViewData) viewData).f19810a;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        boolean z = false;
        if (viewData.getViewType() != getViewType()) {
            return false;
        }
        if (this.f19810a == ((ChartOptionViewData) viewData).f19810a) {
            z = true;
        }
        return z;
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() != getViewType()) {
            return 1;
        }
        if (this.f19810a == ((ChartOptionViewData) viewData).f19810a) {
            return 0;
        }
        return 1;
    }

    public int getViewType() {
        return 5005;
    }

    public int getWeight() {
        return 0;
    }
}
