package com.wallet.crypto.trustapp.repository.wallet;

import io.reactivex.functions.Function;
import java.util.List;
import trust.blockchain.entity.WalletDescriptor;

/* compiled from: lambda */
public final /* synthetic */ class la implements Function {
    /* renamed from: a */
    public static final /* synthetic */ la f19376a = new la();

    private /* synthetic */ la() {
    }

    public final Object apply(Object obj) {
        return ((WalletDescriptor) ((List) obj).get(0));
    }
}
