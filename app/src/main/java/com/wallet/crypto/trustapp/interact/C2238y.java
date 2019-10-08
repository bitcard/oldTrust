package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.y */
public final /* synthetic */ class C2238y implements Function {
    /* renamed from: a */
    public static final /* synthetic */ C2238y f19195a = new C2238y();

    private /* synthetic */ C2238y() {
    }

    public final Object apply(Object obj) {
        byte[] bArr = (byte[]) obj;
        bArr[64] = (byte) (bArr[64] + 27);
        return bArr;
    }
}
