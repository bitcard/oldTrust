package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.a */
public final /* synthetic */ class C1582a implements Callable {
    /* renamed from: a */
    private final /* synthetic */ PreferenceRepositoryType f16978a;

    public /* synthetic */ C1582a(PreferenceRepositoryType preferenceRepositoryType) {
        this.f16978a = preferenceRepositoryType;
    }

    public final Object call() {
        return this.f16978a.getLockAfterTime();
    }
}
