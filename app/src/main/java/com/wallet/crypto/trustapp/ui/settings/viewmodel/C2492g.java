package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.g */
public final /* synthetic */ class C2492g implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f19924a;

    public /* synthetic */ C2492g(MutableLiveData mutableLiveData) {
        this.f19924a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f19924a.postValue((Boolean) obj);
    }
}
