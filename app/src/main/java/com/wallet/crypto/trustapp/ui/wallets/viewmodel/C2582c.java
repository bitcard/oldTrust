package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.c */
public final /* synthetic */ class C2582c implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f20144a;

    public /* synthetic */ C2582c(MutableLiveData mutableLiveData) {
        this.f20144a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f20144a.postValue((Throwable) obj);
    }
}
