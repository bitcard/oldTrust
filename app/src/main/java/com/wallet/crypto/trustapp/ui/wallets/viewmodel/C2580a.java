package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.a */
public final /* synthetic */ class C2580a implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f20142a;

    public /* synthetic */ C2580a(MutableLiveData mutableLiveData) {
        this.f20142a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f20142a.postValue((Wallet) obj);
    }
}
