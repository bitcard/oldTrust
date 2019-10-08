package com.wallet.crypto.trustapp.ui.dapp.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.dapp.DappRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.DashboardViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashboardVeiwModelFactory.kt */
public final class DashboardVeiwModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19683a;
    /* renamed from: b */
    private final DappRepository f19684b;

    @Inject
    public DashboardVeiwModelFactory(SessionRepository sessionRepository, DappRepository dappRepository) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(dappRepository, "dappRepository");
        this.f19683a = sessionRepository;
        this.f19684b = dappRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new DashboardViewModel(this.f19683a, this.f19684b);
    }
}
