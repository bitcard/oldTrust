package com.wallet.crypto.trustapp.ui.dapp.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.SignMessageInteract;
import com.wallet.crypto.trustapp.repository.dapp.DappRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.BrowserViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BrowserViewModelFactory.kt */
public final class BrowserViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19678a;
    /* renamed from: b */
    private final DappRepository f19679b;
    /* renamed from: c */
    private final SignMessageInteract f19680c;

    @Inject
    public BrowserViewModelFactory(SessionRepository sessionRepository, DappRepository dappRepository, SignMessageInteract signMessageInteract) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(dappRepository, "dappRepository");
        Intrinsics.checkParameterIsNotNull(signMessageInteract, "signMessageInteract");
        this.f19678a = sessionRepository;
        this.f19679b = dappRepository;
        this.f19680c = signMessageInteract;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new BrowserViewModel(this.f19678a, this.f19679b, this.f19680c);
    }
}
