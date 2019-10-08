package com.wallet.crypto.trustapp.ui.wallets.activity;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.h */
public final /* synthetic */ class C2565h implements Function {
    /* renamed from: a */
    private final /* synthetic */ AddWalletActivity f20099a;

    public /* synthetic */ C2565h(AddWalletActivity addWalletActivity) {
        this.f20099a = addWalletActivity;
    }

    public final Object apply(Object obj) {
        this.f20099a.f23113g.setCoinVisibilityUpdated(((Wallet) obj).id);
        return obj;
    }
}
