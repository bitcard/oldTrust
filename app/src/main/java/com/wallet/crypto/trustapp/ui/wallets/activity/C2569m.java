package com.wallet.crypto.trustapp.ui.wallets.activity;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.m */
public final /* synthetic */ class C2569m implements Function {
    /* renamed from: a */
    private final /* synthetic */ AddWalletActivity f20103a;

    public /* synthetic */ C2569m(AddWalletActivity addWalletActivity) {
        this.f20103a = addWalletActivity;
    }

    public final Object apply(Object obj) {
        return this.f20103a.f23112f.add((Wallet) obj);
    }
}
