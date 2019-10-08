package com.wallet.crypto.trustapp.ui.transfer.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.TransactionDetailViewModel;
import javax.inject.Inject;

public class TransactionDetailViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f20003a;
    /* renamed from: b */
    private final BlockchainRepository f20004b;
    /* renamed from: c */
    private final TransactionsRepository f20005c;
    /* renamed from: d */
    private final ExternalBrowserRouter f20006d;
    /* renamed from: e */
    private final AssetsController f20007e;

    @Inject
    public TransactionDetailViewModelFactory(SessionRepository sessionRepository, BlockchainRepository blockchainRepository, TransactionsRepository transactionsRepository, ExternalBrowserRouter externalBrowserRouter, AssetsController assetsController) {
        this.f20003a = sessionRepository;
        this.f20004b = blockchainRepository;
        this.f20005c = transactionsRepository;
        this.f20006d = externalBrowserRouter;
        this.f20007e = assetsController;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new TransactionDetailViewModel(this.f20003a, this.f20004b, this.f20005c, this.f20006d, this.f20007e);
    }
}
