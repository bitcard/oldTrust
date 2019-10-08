package com.wallet.crypto.trustapp.ui.currency;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.widget.Binder;

class CurrencyBinder extends Binder<CurrencyViewData> implements OnClickListener {
    /* renamed from: b */
    private CurrencyViewData f19626b;
    /* renamed from: c */
    private final TextView f19627c = ((TextView) findViewById(R.id.title));
    /* renamed from: d */
    private final RadioButton f19628d = ((RadioButton) findViewById(R.id.button));
    /* renamed from: e */
    private final RelativeLayout f19629e = ((RelativeLayout) findViewById(R.id.currency_view));
    /* renamed from: f */
    OnCurrencyClickListener f19630f;

    public CurrencyBinder(View view) {
        super(view);
    }

    /* renamed from: b */
    public static /* synthetic */ void m124b(CurrencyBinder currencyBinder, CurrencyViewData currencyViewData, View view) {
        currencyBinder.f19630f.onClick(view, currencyViewData.f19638c);
        currencyBinder.f19628d.setChecked(true);
    }

    public void onClick(View view) {
        CurrencyViewData currencyViewData = this.f19626b;
        OnCurrencyClickListener onCurrencyClickListener = this.f19630f;
        if (onCurrencyClickListener != null && currencyViewData != null) {
            onCurrencyClickListener.onClick(view, currencyViewData.f19638c);
        }
    }

    public void setOnCurrencyClickListener(OnCurrencyClickListener onCurrencyClickListener) {
        this.f19630f = onCurrencyClickListener;
    }

    public void bind(CurrencyViewData currencyViewData, Bundle bundle) {
        this.f19626b = currencyViewData;
        TextView textView = this.f19627c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currencyViewData.f19638c);
        stringBuilder.append(" - ");
        stringBuilder.append(currencyViewData.f19637b);
        textView.setText(stringBuilder.toString());
        this.f19628d.setChecked(currencyViewData.f19640e);
        this.f19628d.setOnClickListener(new C1506c(this, currencyViewData));
        this.f19629e.setOnClickListener(new C1505b(this, currencyViewData));
    }
}
