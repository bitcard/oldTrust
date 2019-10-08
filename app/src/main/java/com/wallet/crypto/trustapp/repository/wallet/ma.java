package com.wallet.crypto.trustapp.repository.wallet;

import io.reactivex.functions.Function;
import trust.blockchain.Slip;

/* compiled from: lambda */
public final /* synthetic */ class ma implements Function {
    /* renamed from: a */
    private final /* synthetic */ WatchAdapter f19377a;
    /* renamed from: b */
    private final /* synthetic */ byte[] f19378b;

    public /* synthetic */ ma(WatchAdapter watchAdapter, byte[] bArr) {
        this.f19377a = watchAdapter;
        this.f19378b = bArr;
    }

    public final Object apply(Object obj) {
        return ((Slip) obj).toAddress(new String(this.f19378b));
    }
}
