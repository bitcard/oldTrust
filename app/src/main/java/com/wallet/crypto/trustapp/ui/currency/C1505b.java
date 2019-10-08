package com.wallet.crypto.trustapp.ui.currency;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.currency.b */
public final /* synthetic */ class C1505b implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ CurrencyBinder f16805a;
    /* renamed from: b */
    private final /* synthetic */ CurrencyViewData f16806b;

    public /* synthetic */ C1505b(CurrencyBinder currencyBinder, CurrencyViewData currencyViewData) {
        this.f16805a = currencyBinder;
        this.f16806b = currencyViewData;
    }

    public final void onClick(View view) {
        CurrencyBinder.m124b(this.f16805a, this.f16806b, view);
    }
}
