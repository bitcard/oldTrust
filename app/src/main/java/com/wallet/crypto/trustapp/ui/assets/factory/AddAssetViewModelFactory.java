package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.AddAssetInteract;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AddAssetViewModel;
import javax.inject.Inject;

public class AddAssetViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19465a;
    /* renamed from: b */
    private final AddAssetInteract f19466b;
    /* renamed from: c */
    private final ApiService f19467c;

    @Inject
    public AddAssetViewModelFactory(SessionRepository sessionRepository, AddAssetInteract addAssetInteract, ApiService apiService) {
        this.f19465a = sessionRepository;
        this.f19466b = addAssetInteract;
        this.f19467c = apiService;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new AddAssetViewModel(this.f19465a, this.f19466b, this.f19467c);
    }
}
