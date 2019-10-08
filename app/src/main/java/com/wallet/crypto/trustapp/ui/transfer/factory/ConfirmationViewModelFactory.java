package com.wallet.crypto.trustapp.ui.transfer.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.HandleTransactionInteract;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.ConfirmationViewModel;
import javax.inject.Inject;

public class ConfirmationViewModelFactory implements Factory {
    /* renamed from: a */
    private final BlockchainRepository f19997a;
    /* renamed from: b */
    private final AssetsController f19998b;
    /* renamed from: c */
    private final SessionRepository f19999c;
    /* renamed from: d */
    private final HandleTransactionInteract f20000d;
    /* renamed from: e */
    private final ApiService f20001e;

    @Inject
    public ConfirmationViewModelFactory(BlockchainRepository blockchainRepository, AssetsController assetsController, SessionRepository sessionRepository, HandleTransactionInteract handleTransactionInteract, ApiService apiService) {
        this.f19997a = blockchainRepository;
        this.f19998b = assetsController;
        this.f19999c = sessionRepository;
        this.f20000d = handleTransactionInteract;
        this.f20001e = apiService;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new ConfirmationViewModel(this.f19997a, this.f19998b, this.f19999c, this.f20000d, this.f20001e);
    }
}
