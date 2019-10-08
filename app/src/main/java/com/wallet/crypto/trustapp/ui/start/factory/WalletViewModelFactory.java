package com.wallet.crypto.trustapp.ui.start.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.start.viewmodel.WalletViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WalletViewModelFactory.kt */
public final class WalletViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19954a;

    @Inject
    public WalletViewModelFactory(SessionRepository sessionRepository) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        this.f19954a = sessionRepository;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
        return (T) new WalletViewModel(this.f19954a);
    }
}
