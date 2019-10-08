package com.wallet.crypto.trustapp.ui.receive.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ShareAddressRouter;
import com.wallet.crypto.trustapp.ui.receive.viewmodel.ReceiveViewModel;
import io.reactivex.annotations.NonNull;
import javax.inject.Inject;

public class ReceiveViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19877a;
    /* renamed from: b */
    private final ShareAddressRouter f19878b;
    /* renamed from: c */
    private final AssetsController f19879c;

    @Inject
    public ReceiveViewModelFactory(SessionRepository sessionRepository, ShareAddressRouter shareAddressRouter, AssetsController assetsController) {
        this.f19877a = sessionRepository;
        this.f19878b = shareAddressRouter;
        this.f19879c = assetsController;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> cls) {
        return (T) new ReceiveViewModel(this.f19877a, this.f19878b, this.f19879c);
    }
}
