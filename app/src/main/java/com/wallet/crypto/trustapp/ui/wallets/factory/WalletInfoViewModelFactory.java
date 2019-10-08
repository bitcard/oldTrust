package com.wallet.crypto.trustapp.ui.wallets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.OpenExtendedKeyRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.ui.wallets.viewmodel.WalletInfoViewModel;
import javax.inject.Inject;

public class WalletInfoViewModelFactory implements Factory {
    /* renamed from: a */
    private final WalletsRepository f20122a;
    /* renamed from: b */
    private final PasscodeRepositoryType f20123b;
    /* renamed from: c */
    private final PasscodeRouter f20124c;
    /* renamed from: d */
    private final OpenExtendedKeyRouter f20125d;

    @Inject
    public WalletInfoViewModelFactory(WalletsRepository walletsRepository, PasscodeRepositoryType passcodeRepositoryType, PasscodeRouter passcodeRouter, OpenExtendedKeyRouter openExtendedKeyRouter) {
        this.f20122a = walletsRepository;
        this.f20123b = passcodeRepositoryType;
        this.f20124c = passcodeRouter;
        this.f20125d = openExtendedKeyRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new WalletInfoViewModel(this.f20122a, this.f20123b, this.f20124c, this.f20125d);
    }
}
