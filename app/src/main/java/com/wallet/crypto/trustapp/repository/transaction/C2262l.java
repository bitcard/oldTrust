package com.wallet.crypto.trustapp.repository.transaction;

import io.reactivex.functions.Function;
import java.util.List;
import trust.blockchain.entity.Transaction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.transaction.l */
public final /* synthetic */ class C2262l implements Function {
    /* renamed from: a */
    public static final /* synthetic */ C2262l f19280a = new C2262l();

    private /* synthetic */ C2262l() {
    }

    public final Object apply(Object obj) {
        return ((Transaction[]) ((List) obj).toArray(new Transaction[0]));
    }
}
