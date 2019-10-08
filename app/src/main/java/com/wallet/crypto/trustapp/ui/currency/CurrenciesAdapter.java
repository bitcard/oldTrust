package com.wallet.crypto.trustapp.ui.currency;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import java.util.ArrayList;
import java.util.List;

class CurrenciesAdapter extends Adapter<BinderViewHolder> {
    /* renamed from: c */
    private final OnCurrencyClickListener f19624c;
    /* renamed from: d */
    private final List<ViewData> f19625d = new ArrayList();

    public CurrenciesAdapter(OnCurrencyClickListener onCurrencyClickListener) {
        this.f19624c = onCurrencyClickListener;
    }

    public void addCurrencies(CurrencyViewData[] currencyViewDataArr) {
        this.f19625d.clear();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (CurrencyViewData currencyViewData : currencyViewDataArr) {
            if (currencyViewData.f19636a == 1) {
                arrayList.add(currencyViewData);
            } else {
                arrayList2.add(currencyViewData);
            }
        }
        this.f19625d.add(new SeparatorViewData(R.string.settings_currency_popular_label_title));
        this.f19625d.addAll(arrayList);
        this.f19625d.add(new SeparatorViewData(R.string.settings_currency_all_label_title));
        this.f19625d.addAll(arrayList2);
    }

    public int getItemCount() {
        return this.f19625d.size();
    }

    public int getItemViewType(int i) {
        return ((ViewData) this.f19625d.get(i)).getViewType();
    }

    public void onBindViewHolder(BinderViewHolder binderViewHolder, int i) {
        binderViewHolder.bind(this.f19625d.get(i));
    }

    public BinderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1011:
                return new CurrencyViewHolder(R.layout.item_currency, viewGroup, this.f19624c);
            case 1012:
                return new SeparatorViewHolder(R.layout.item_currencies_separator, viewGroup);
            default:
                return null;
        }
    }
}
