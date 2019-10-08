package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.session.b */
public final /* synthetic */ class C1451b implements Callable {
    /* renamed from: a */
    private final /* synthetic */ PreferenceRepositoryType f16673a;

    public /* synthetic */ C1451b(PreferenceRepositoryType preferenceRepositoryType) {
        this.f16673a = preferenceRepositoryType;
    }

    public final Object call() {
        return this.f16673a.getWalletId();
    }
}
