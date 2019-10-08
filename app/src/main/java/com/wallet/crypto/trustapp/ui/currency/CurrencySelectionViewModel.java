package com.wallet.crypto.trustapp.ui.currency;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.CurrencyInfo;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.CurrencyInfoService;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import java.util.ArrayList;
import java.util.Currency;

public class CurrencySelectionViewModel extends BaseViewModel {
    /* renamed from: d */
    private final CurrencyInfoService f21365d;
    /* renamed from: e */
    private final PreferenceRepositoryType f21366e;
    /* renamed from: f */
    private final SessionRepository f21367f;
    /* renamed from: g */
    private final MutableLiveData<CurrencyViewData[]> f21368g = new MutableLiveData();

    public CurrencySelectionViewModel(CurrencyInfoService currencyInfoService, PreferenceRepositoryType preferenceRepositoryType, SessionRepository sessionRepository) {
        this.f21365d = currencyInfoService;
        this.f21366e = preferenceRepositoryType;
        this.f21367f = sessionRepository;
    }

    static /* synthetic */ void lambda$init$0(Throwable th) throws Exception {
    }

    private void onCurrencies(CurrencyInfo[] currencyInfoArr) {
        ArrayList<CurrencyViewData> arrayList = new ArrayList<>();
        for (CurrencyInfo currencyInfo : currencyInfoArr) {
            try {
                Currency instance = Currency.getInstance(currencyInfo.currencyCode);
                if (instance != null) {
                    arrayList.add(new CurrencyViewData(currencyInfo.rating, instance.getDisplayName(), currencyInfo.currencyCode, instance.getSymbol(), currencyInfo.currencyCode.equals(defaultCurrencyCode())));
                }
            } catch (Exception unused) {
            }
        }
        this.f21368g.setValue(arrayList.toArray(new CurrencyViewData[arrayList.size()]));
    }

    public LiveData<CurrencyViewData[]> currencies() {
        return this.f21368g;
    }

    public String defaultCurrencyCode() {
        return this.f21366e.getCurrencyCode();
    }

    public void init() {
        this.f19422c.add(this.f21365d.fetch().subscribe(currencyInfos -> onCurrencies(currencyInfos), throwable -> lambda$init$0(throwable)));
    }

    public void selectCurrencyCode(String str) {
        CurrencyViewData[] currencyViewDataArr = (CurrencyViewData[]) this.f21368g.getValue();
        if (!defaultCurrencyCode().equals(str)) {
            for (CurrencyViewData currencyViewData : currencyViewDataArr) {
                if (currencyViewData.f19638c.equals(str)) {
                    this.f21367f.setCurrencyCode(str).subscribe();
                    return;
                }
            }
        }
    }
}
