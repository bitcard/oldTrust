package com.wallet.crypto.trustapp.repository.transaction;

import io.reactivex.functions.Predicate;
import trust.blockchain.entity.Transaction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.transaction.f */
public final /* synthetic */ class C2259f implements Predicate {
    /* renamed from: a */
    private final /* synthetic */ String f19276a;

    public /* synthetic */ C2259f(String str) {
        this.f19276a = str;
    }

    public final boolean test(Object obj) {
        return !(((Transaction) obj).to.data().equals(this.f19276a));
    }
}
