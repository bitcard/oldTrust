package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.s */
public final /* synthetic */ class C2233s implements Function {
    /* renamed from: a */
    public static final /* synthetic */ C2233s f19186a = new C2233s();

    private /* synthetic */ C2233s() {
    }

    public final Object apply(Object obj) {
        byte[] bArr = (byte[]) obj;
        bArr[64] = (byte) (bArr[64] + 27);
        return bArr;
    }
}
