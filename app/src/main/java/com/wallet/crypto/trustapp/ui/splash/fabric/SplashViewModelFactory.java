package com.wallet.crypto.trustapp.ui.splash.fabric;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.CheckWalletExportInteract;
import com.wallet.crypto.trustapp.interact.migration.JsonStoreToRealmInteract;
import com.wallet.crypto.trustapp.interact.migration.PinToPasswordStoreInteract;
import com.wallet.crypto.trustapp.interact.migration.ToSlip44Interact;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.splash.viewmodel.SplashViewModel;
import javax.inject.Inject;

public class SplashViewModelFactory implements Factory {
    /* renamed from: a */
    private final WalletsRepository f19927a;
    /* renamed from: b */
    private final PasscodeRepositoryType f19928b;
    /* renamed from: c */
    private final CheckWalletExportInteract f19929c;
    /* renamed from: d */
    private final ToSlip44Interact f19930d;
    /* renamed from: e */
    private final PinToPasswordStoreInteract f19931e;
    /* renamed from: f */
    private final JsonStoreToRealmInteract f19932f;

    @Inject
    public SplashViewModelFactory(WalletsRepository walletsRepository, PasscodeRepositoryType passcodeRepositoryType, CheckWalletExportInteract checkWalletExportInteract, ToSlip44Interact toSlip44Interact, PinToPasswordStoreInteract pinToPasswordStoreInteract, JsonStoreToRealmInteract jsonStoreToRealmInteract) {
        this.f19927a = walletsRepository;
        this.f19928b = passcodeRepositoryType;
        this.f19929c = checkWalletExportInteract;
        this.f19930d = toSlip44Interact;
        this.f19931e = pinToPasswordStoreInteract;
        this.f19932f = jsonStoreToRealmInteract;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new SplashViewModel(this.f19927a, this.f19928b, this.f19929c, this.f19930d, this.f19931e, this.f19932f);
    }
}
