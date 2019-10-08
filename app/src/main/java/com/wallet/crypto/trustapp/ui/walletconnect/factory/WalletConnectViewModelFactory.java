package com.wallet.crypto.trustapp.ui.walletconnect.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.AnySignerInteract;
import com.wallet.crypto.trustapp.interact.HandleTransactionInteract;
import com.wallet.crypto.trustapp.interact.SignMessageInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.walletconnect.viewmodel.WalletConnectViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WalletConnectViewModelFactory.kt */
public final class WalletConnectViewModelFactory implements Factory {
    private final AnySignerInteract anySignerInteract;
    private final HandleTransactionInteract handleTransactionInteract;
    private final PreferenceRepositoryType preferenceRepository;
    private final SessionRepository sessionRepository;
    private final SignMessageInteract signMessageInteract;

    @Inject
    public WalletConnectViewModelFactory(SessionRepository sessionRepository, PreferenceRepositoryType preferenceRepositoryType, HandleTransactionInteract handleTransactionInteract, SignMessageInteract signMessageInteract, AnySignerInteract anySignerInteract) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(preferenceRepositoryType, "preferenceRepository");
        Intrinsics.checkParameterIsNotNull(handleTransactionInteract, "handleTransactionInteract");
        Intrinsics.checkParameterIsNotNull(signMessageInteract, "signMessageInteract");
        Intrinsics.checkParameterIsNotNull(anySignerInteract, "anySignerInteract");
        this.sessionRepository = sessionRepository;
        this.preferenceRepository = preferenceRepositoryType;
        this.handleTransactionInteract = handleTransactionInteract;
        this.signMessageInteract = signMessageInteract;
        this.anySignerInteract = anySignerInteract;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new WalletConnectViewModel(this.sessionRepository, this.preferenceRepository, this.handleTransactionInteract, this.signMessageInteract, this.anySignerInteract);
    }
}
