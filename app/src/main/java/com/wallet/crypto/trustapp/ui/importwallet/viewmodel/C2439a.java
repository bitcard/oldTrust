package com.wallet.crypto.trustapp.ui.importwallet.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.importwallet.viewmodel.a */
public final /* synthetic */ class C2439a implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f19789a;

    public /* synthetic */ C2439a(MutableLiveData mutableLiveData) {
        this.f19789a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f19789a.postValue((Wallet) obj);
    }
}
