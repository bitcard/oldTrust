package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.ui.receive.view.ReceiveViewData;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.viewmodel.a */
public final /* synthetic */ class C2469a implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f19880a;

    public /* synthetic */ C2469a(MutableLiveData mutableLiveData) {
        this.f19880a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f19880a.postValue((ReceiveViewData) obj);
    }
}
