package com.wallet.crypto.trustapp.ui.start.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.CheckWalletExportInteract;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.start.viewmodel.MainViewModel;
import javax.inject.Inject;

public class MainViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19948a;
    /* renamed from: b */
    private final WalletsRepository f19949b;
    /* renamed from: c */
    private final AssetsController f19950c;
    /* renamed from: d */
    private final TransactionsRepository f19951d;
    /* renamed from: e */
    private final CheckWalletExportInteract f19952e;
    /* renamed from: f */
    private final ExternalBrowserRouter f19953f;

    @Inject
    MainViewModelFactory(SessionRepository sessionRepository, WalletsRepository walletsRepository, AssetsController assetsController, TransactionsRepository transactionsRepository, CheckWalletExportInteract checkWalletExportInteract, ExternalBrowserRouter externalBrowserRouter) {
        this.f19948a = sessionRepository;
        this.f19949b = walletsRepository;
        this.f19950c = assetsController;
        this.f19951d = transactionsRepository;
        this.f19952e = checkWalletExportInteract;
        this.f19953f = externalBrowserRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new MainViewModel(this.f19948a, this.f19949b, this.f19950c, this.f19951d, this.f19952e, this.f19953f);
    }
}
