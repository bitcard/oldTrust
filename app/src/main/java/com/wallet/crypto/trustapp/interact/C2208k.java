package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.k */
public final /* synthetic */ class C2208k implements Function {
    /* renamed from: a */
    private final /* synthetic */ TransactionUnsigned f19137a;

    public /* synthetic */ C2208k(TransactionUnsigned transactionUnsigned) {
        this.f19137a = transactionUnsigned;
    }

    public final Object apply(Object obj) {
        return new TransactionSigned(this.f19137a, (byte[]) obj, null);
    }
}
