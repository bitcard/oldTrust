package com.wallet.crypto.trustapp.ui.dapp.controller;

import io.reactivex.functions.Consumer;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.controller.k */
public final /* synthetic */ class C2409k implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ OnSignTransactionListener f19658a;

    public /* synthetic */ C2409k(OnSignTransactionListener onSignTransactionListener) {
        this.f19658a = onSignTransactionListener;
    }

    public final void accept(Object obj) {
        this.f19658a.onSignTransaction((TransactionUnsigned) obj);
    }
}
