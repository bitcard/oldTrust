package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesItemViewModel;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.collection.fragment.j */
public final /* synthetic */ class C2392j implements Observer {
    /* renamed from: a */
    private final /* synthetic */ CollectiblesItemViewModel f19607a;

    public /* synthetic */ C2392j(CollectiblesItemViewModel collectiblesItemViewModel) {
        this.f19607a = collectiblesItemViewModel;
    }

    public final void onChanged(Object obj) {
        this.f19607a.update((CollectiblesItem) obj);
    }
}
