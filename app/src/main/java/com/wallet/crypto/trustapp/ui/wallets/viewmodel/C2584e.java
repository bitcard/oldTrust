package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.e */
public final /* synthetic */ class C2584e implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f20146a;

    public /* synthetic */ C2584e(MutableLiveData mutableLiveData) {
        this.f20146a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f20146a.postValue((String) obj);
    }
}
