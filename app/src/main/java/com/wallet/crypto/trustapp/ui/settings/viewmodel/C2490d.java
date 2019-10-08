package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.d */
public final /* synthetic */ class C2490d implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f19922a;

    public /* synthetic */ C2490d(MutableLiveData mutableLiveData) {
        this.f19922a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f19922a.postValue((String) obj);
    }
}
