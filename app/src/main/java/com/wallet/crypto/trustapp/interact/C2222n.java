package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.n */
public final /* synthetic */ class C2222n implements Function {
    /* renamed from: a */
    private final /* synthetic */ TransactionUnsigned f19163a;
    /* renamed from: b */
    private final /* synthetic */ byte[] f19164b;

    public /* synthetic */ C2222n(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        this.f19163a = transactionUnsigned;
        this.f19164b = bArr;
    }

    public final Object apply(Object obj) {
        return new TransactionSigned(this.f19163a, this.f19164b, (String) obj);
    }
}
