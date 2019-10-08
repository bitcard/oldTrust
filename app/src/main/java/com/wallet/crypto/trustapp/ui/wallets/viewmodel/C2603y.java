package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.ViewData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.y */
public final /* synthetic */ class C2603y implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f20168a;

    public /* synthetic */ C2603y(MutableLiveData mutableLiveData) {
        this.f20168a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f20168a.postValue((ViewData[]) obj);
    }
}
