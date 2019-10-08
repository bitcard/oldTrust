package com.wallet.crypto.trustapp.repository.transaction;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import trust.blockchain.entity.Transaction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.transaction.a */
public final /* synthetic */ class C2255a implements Function {
    /* renamed from: a */
    public static final /* synthetic */ C2255a f19268a = new C2255a();

    private /* synthetic */ C2255a() {
    }

    public final Object apply(Object obj) {
        return Observable.fromArray((Transaction[]) obj);
    }
}
