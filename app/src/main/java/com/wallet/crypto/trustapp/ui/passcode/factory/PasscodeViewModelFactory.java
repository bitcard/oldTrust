package com.wallet.crypto.trustapp.ui.passcode.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.router.FromPinRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.ui.passcode.viewmodel.PasscodeViewModel;
import javax.inject.Inject;

public class PasscodeViewModelFactory implements Factory {
    /* renamed from: a */
    private final PasscodeRepositoryType f19858a;
    /* renamed from: b */
    private final PasscodeRouter f19859b;
    /* renamed from: c */
    private final FromPinRouter f19860c;

    @Inject
    public PasscodeViewModelFactory(PasscodeRepositoryType passcodeRepositoryType, PasscodeRouter passcodeRouter, FromPinRouter fromPinRouter) {
        this.f19858a = passcodeRepositoryType;
        this.f19859b = passcodeRouter;
        this.f19860c = fromPinRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new PasscodeViewModel(this.f19858a, this.f19859b, this.f19860c);
    }
}
