package com.wallet.crypto.trustapp.ui.dapp.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.dapp.DappRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappCategoryViewModelFactory.kt */
public final class DappCategoryViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19681a;
    /* renamed from: b */
    private final DappRepository f19682b;

    @Inject
    public DappCategoryViewModelFactory(SessionRepository sessionRepository, DappRepository dappRepository) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(dappRepository, "dappRepository");
        this.f19681a = sessionRepository;
        this.f19682b = dappRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new DappCategoryViewModel(this.f19681a, this.f19682b);
    }
}
