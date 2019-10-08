package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.e */
public final /* synthetic */ class C1583e implements Callable {
    /* renamed from: a */
    private final /* synthetic */ PasscodeRepositoryType f16979a;

    public /* synthetic */ C1583e(PasscodeRepositoryType passcodeRepositoryType) {
        this.f16979a = passcodeRepositoryType;
    }

    public final Object call() {
        return Boolean.valueOf(this.f16979a.has());
    }
}
