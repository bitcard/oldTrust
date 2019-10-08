package com.wallet.crypto.trustapp.ui.importwallet.viewmodel;

import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.importwallet.viewmodel.k */
public final /* synthetic */ class C2448k implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ ImportWalletViewModel f19801a;

    public /* synthetic */ C2448k(ImportWalletViewModel importWalletViewModel) {
        this.f19801a = importWalletViewModel;
    }

    public final void accept(Object obj) {
        this.f19801a.onError((Throwable) obj);
    }
}
