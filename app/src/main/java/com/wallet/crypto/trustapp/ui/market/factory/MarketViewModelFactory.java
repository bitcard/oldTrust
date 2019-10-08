package com.wallet.crypto.trustapp.ui.market.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.market.MarketRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.market.viewmodel.MarketViewModel;
import javax.inject.Inject;

public class MarketViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19824a;
    /* renamed from: b */
    private final MarketRepositoryType f19825b;
    /* renamed from: c */
    private final ExternalBrowserRouter f19826c;

    @Inject
    public MarketViewModelFactory(SessionRepository sessionRepository, MarketRepositoryType marketRepositoryType, ExternalBrowserRouter externalBrowserRouter) {
        this.f19824a = sessionRepository;
        this.f19825b = marketRepositoryType;
        this.f19826c = externalBrowserRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new MarketViewModel(this.f19824a, this.f19825b, this.f19826c);
    }
}
