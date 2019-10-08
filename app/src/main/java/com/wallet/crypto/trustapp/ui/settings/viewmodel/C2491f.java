package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType.LockAfterVariants;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.viewmodel.f */
public final /* synthetic */ class C2491f implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ MutableLiveData f19923a;

    public /* synthetic */ C2491f(MutableLiveData mutableLiveData) {
        this.f19923a = mutableLiveData;
    }

    public final void accept(Object obj) {
        this.f19923a.postValue((LockAfterVariants) obj);
    }
}
