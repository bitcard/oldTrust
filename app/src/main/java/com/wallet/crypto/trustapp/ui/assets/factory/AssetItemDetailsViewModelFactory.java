package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.router.AssetMarketInfoRouter;
import com.wallet.crypto.trustapp.router.TransferRouter;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetItemDetailsViewModel;
import javax.inject.Inject;

public class AssetItemDetailsViewModelFactory implements Factory {
    /* renamed from: a */
    private final AssetsController f19468a;
    /* renamed from: b */
    private final TransferRouter f19469b;
    /* renamed from: c */
    private final AssetMarketInfoRouter f19470c;
    /* renamed from: d */
    private final SessionRepository f19471d;
    /* renamed from: e */
    private final TransactionsRepository f19472e;

    @Inject
    public AssetItemDetailsViewModelFactory(SessionRepository sessionRepository, AssetsController assetsController, TransactionsRepository transactionsRepository, TransferRouter transferRouter, AssetMarketInfoRouter assetMarketInfoRouter) {
        this.f19471d = sessionRepository;
        this.f19468a = assetsController;
        this.f19472e = transactionsRepository;
        this.f19469b = transferRouter;
        this.f19470c = assetMarketInfoRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new AssetItemDetailsViewModel(this.f19471d, this.f19472e, this.f19468a, this.f19469b, this.f19470c);
    }
}
