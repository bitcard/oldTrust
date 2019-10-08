package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.SearchAssetsViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchAssetsViewModelFactory.kt */
public final class SearchAssetsViewModelFactory implements Factory {
    /* renamed from: a */
    final SessionRepository f19487a;
    /* renamed from: b */
    final AssetsController f19488b;
    /* renamed from: c */
    final ApiService f19489c;

    @Inject
    public SearchAssetsViewModelFactory(SessionRepository sessionRepository, AssetsController assetsController, ApiService apiService) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(assetsController, "assetsController");
        Intrinsics.checkParameterIsNotNull(apiService, "apiService");
        this.f19487a = sessionRepository;
        this.f19488b = assetsController;
        this.f19489c = apiService;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new SearchAssetsViewModel(this.f19487a, this.f19488b, this.f19489c);
    }
}
