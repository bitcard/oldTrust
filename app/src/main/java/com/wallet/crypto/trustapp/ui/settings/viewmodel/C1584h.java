package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.h */
public final /* synthetic */ class C1584h implements Callable {
    /* renamed from: a */
    private final /* synthetic */ PreferenceRepositoryType f16980a;

    public /* synthetic */ C1584h(PreferenceRepositoryType preferenceRepositoryType) {
        this.f16980a = preferenceRepositoryType;
    }

    public final Object call() {
        return this.f16980a.getCurrencyCode();
    }
}
