package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.session.h */
public final /* synthetic */ class C2253h implements Function {
    /* renamed from: a */
    private final /* synthetic */ WalletsRepository f19261a;

    public /* synthetic */ C2253h(WalletsRepository walletsRepository) {
        this.f19261a = walletsRepository;
    }

    public final Object apply(Object obj) {
        return this.f19261a.findWalletById((String) obj);
    }
}
