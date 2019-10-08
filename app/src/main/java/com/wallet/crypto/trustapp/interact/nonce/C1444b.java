package com.wallet.crypto.trustapp.interact.nonce;

import java.util.Comparator;
import trust.blockchain.entity.Transaction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.nonce.b */
public final /* synthetic */ class C1444b implements Comparator {
    /* renamed from: a */
    public static final /* synthetic */ C1444b f16640a = new C1444b();

    private /* synthetic */ C1444b() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((((Transaction) obj).nonce - ((Transaction) obj2).nonce) * -1);
    }
}
