package com.wallet.crypto.trustapp.ui.dex.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.dex.LotRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.LotsViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LotsViewModelFactory.kt */
public final class LotsViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19754a;
    /* renamed from: b */
    private final LotRepository f19755b;

    @Inject
    public LotsViewModelFactory(SessionRepository sessionRepository, LotRepository lotRepository) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(lotRepository, "lotRepository");
        this.f19754a = sessionRepository;
        this.f19755b = lotRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new LotsViewModel(this.f19754a, this.f19755b);
    }
}
