package com.wallet.crypto.trustapp.ui.currency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class CurrencySelectionFragment extends Fragment {
    @Inject
    /* renamed from: a */
    CurrencySelectionViewModelFactory f21360a;
    /* renamed from: b */
    private CurrencySelectionViewModel f21361b;
    /* renamed from: c */
    private CurrenciesAdapter f21362c;
    /* renamed from: d */
    private SystemView f21363d;
    /* renamed from: e */
    private RecyclerView f21364e;

    private void onCurrencyClick(View view, String str) {
        this.f21361b.selectCurrencyCode(str);
        getActivity().finish();
    }

    private void showCurrencies(CurrencyViewData[] currencyViewDataArr) {
        this.f21362c.addCurrencies(currencyViewDataArr);
        this.f21363d.showProgress(false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f21361b = (CurrencySelectionViewModel) ViewModelProviders.of((Fragment) this, this.f21360a).get(CurrencySelectionViewModel.class);
        LiveData progress = this.f21361b.progress();
        SystemView systemView = this.f21363d;
        systemView.getClass();
        progress.observe(this, new C2401a(systemView));
        this.f21361b.currencies().observe(this, currencyViewData -> showCurrencies(currencyViewData));
        this.f21363d.showProgress(true);
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_currency_selection, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        this.f21361b.init();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21362c = new CurrenciesAdapter((view1, str) -> onCurrencyClick(view1, str));
        this.f21363d = (SystemView) view.findViewById(R.id.system_view);
        this.f21364e = (RecyclerView) view.findViewById(R.id.list);
        this.f21364e.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.f21364e.setAdapter(this.f21362c);
        this.f21363d.attachRecyclerView(this.f21364e);
    }
}
