package com.wallet.crypto.trustapp.ui.currency;

import com.wallet.crypto.trustapp.entity.ViewData;

public class CurrencyViewData implements ViewData {
    /* renamed from: a */
    public final int f19636a;
    /* renamed from: b */
    public final String f19637b;
    /* renamed from: c */
    public final String f19638c;
    /* renamed from: d */
    public final String f19639d;
    /* renamed from: e */
    public final boolean f19640e;

    public CurrencyViewData(int i, String str, String str2, String str3, boolean z) {
        this.f19636a = i;
        this.f19637b = str;
        this.f19638c = str2;
        this.f19639d = str3;
        this.f19640e = z;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        CurrencyViewData currencyViewData = (CurrencyViewData) viewData;
        return this.f19636a == currencyViewData.f19636a && this.f19637b.equals(currencyViewData.f19637b) && this.f19639d.equals(currencyViewData.f19639d);
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType() && ((CurrencyViewData) viewData).f19638c.equals(this.f19638c);
    }

    public int compare(ViewData viewData) {
        return getWeight() == viewData.getWeight() ? this.f19638c.compareTo(((CurrencyViewData) viewData).f19638c) : -1;
    }

    public int getViewType() {
        return 1011;
    }

    public int getWeight() {
        return getViewType();
    }
}
