package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.BuyCryptoViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoViewModelFactory.kt */
public final class BuyCryptoViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19479a;
    /* renamed from: b */
    private final ApiService f19480b;

    @Inject
    public BuyCryptoViewModelFactory(SessionRepository sessionRepository, ApiService apiService) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(apiService, "apiService");
        this.f19479a = sessionRepository;
        this.f19480b = apiService;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new BuyCryptoViewModel(this.f19479a, this.f19480b);
    }
}
