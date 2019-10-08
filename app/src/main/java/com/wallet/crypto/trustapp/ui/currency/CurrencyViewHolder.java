package com.wallet.crypto.trustapp.ui.currency;

import android.os.Bundle;
import android.view.ViewGroup;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

class CurrencyViewHolder extends BinderViewHolder<CurrencyViewData> {
    /* renamed from: t */
    private final CurrencyBinder f21369t = new CurrencyBinder(this.itemView);
    /* renamed from: u */
    OnCurrencyClickListener f21370u;

    public CurrencyViewHolder(int i, ViewGroup viewGroup, OnCurrencyClickListener onCurrencyClickListener) {
        super(i, viewGroup);
        this.f21370u = onCurrencyClickListener;
    }

    public void bind(CurrencyViewData currencyViewData, Bundle bundle) {
        this.f21369t.bind(currencyViewData, bundle);
        this.f21369t.setOnCurrencyClickListener(this.f21370u);
    }
}
