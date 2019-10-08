package com.wallet.crypto.trustapp.repository.session;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.session.i */
public final /* synthetic */ class C2254i implements Function {
    /* renamed from: a */
    private final /* synthetic */ PreferenceSessionRepository f19262a;

    public /* synthetic */ C2254i(PreferenceSessionRepository preferenceSessionRepository) {
        this.f19262a = preferenceSessionRepository;
    }

    public final Object apply(Object obj) {
        return this.f19262a.setWallet((Wallet) obj);
    }
}
