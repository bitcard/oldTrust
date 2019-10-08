package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.z */
public final /* synthetic */ class C2239z implements Function {
    /* renamed from: a */
    public static final /* synthetic */ C2239z f19196a = new C2239z();

    private /* synthetic */ C2239z() {
    }

    public final Object apply(Object obj) {
        byte[] bArr = (byte[]) obj;
        bArr[64] = (byte) (bArr[64] + 27);
        return bArr;
    }
}
