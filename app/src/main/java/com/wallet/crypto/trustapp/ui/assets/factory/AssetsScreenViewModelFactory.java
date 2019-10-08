package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.start.viewmodel.AssetsScreenViewModel;
import javax.inject.Inject;

public class AssetsScreenViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19473a;

    @Inject
    public AssetsScreenViewModelFactory(SessionRepository sessionRepository) {
        this.f19473a = sessionRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new AssetsScreenViewModel(this.f19473a);
    }
}
