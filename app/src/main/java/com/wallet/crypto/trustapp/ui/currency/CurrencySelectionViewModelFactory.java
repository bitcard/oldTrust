package com.wallet.crypto.trustapp.ui.currency;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.CurrencyInfoService;
import javax.inject.Inject;

public class CurrencySelectionViewModelFactory implements Factory {
    /* renamed from: a */
    private final CurrencyInfoService f19633a;
    /* renamed from: b */
    private final PreferenceRepositoryType f19634b;
    /* renamed from: c */
    private final SessionRepository f19635c;

    @Inject
    public CurrencySelectionViewModelFactory(CurrencyInfoService currencyInfoService, PreferenceRepositoryType preferenceRepositoryType, SessionRepository sessionRepository) {
        this.f19633a = currencyInfoService;
        this.f19634b = preferenceRepositoryType;
        this.f19635c = sessionRepository;
        currencyInfoService.fetch();
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new CurrencySelectionViewModel(this.f19633a, this.f19634b, this.f19635c);
    }
}
