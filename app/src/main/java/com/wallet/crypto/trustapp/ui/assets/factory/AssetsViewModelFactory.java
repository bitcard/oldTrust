package com.wallet.crypto.trustapp.ui.assets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.migration.UpdateAccountsInteractType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.service.tick.TickCoordinatorService;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetsViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetsViewModelFactory.kt */
public final class AssetsViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19474a;
    /* renamed from: b */
    private final TickCoordinatorService f19475b;
    /* renamed from: c */
    private final AssetsController f19476c;
    /* renamed from: d */
    private final TransactionsRepository f19477d;
    /* renamed from: e */
    private final UpdateAccountsInteractType f19478e;

    @Inject
    public AssetsViewModelFactory(SessionRepository sessionRepository, TickCoordinatorService tickCoordinatorService, AssetsController assetsController, TransactionsRepository transactionsRepository, UpdateAccountsInteractType updateAccountsInteractType) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(tickCoordinatorService, "tickCoordinatorService");
        Intrinsics.checkParameterIsNotNull(assetsController, "assetsController");
        Intrinsics.checkParameterIsNotNull(transactionsRepository, "transactionRepository");
        Intrinsics.checkParameterIsNotNull(updateAccountsInteractType, "updateAccountsInteract");
        this.f19474a = sessionRepository;
        this.f19475b = tickCoordinatorService;
        this.f19476c = assetsController;
        this.f19477d = transactionsRepository;
        this.f19478e = updateAccountsInteractType;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new AssetsViewModel(this.f19474a, this.f19475b, this.f19476c, this.f19477d, this.f19478e);
    }
}
