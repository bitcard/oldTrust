package com.wallet.crypto.trustapp.ui.collection.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.ui.collection.view.CollectiblesItemsAdapter;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.collection.fragment.a */
public final /* synthetic */ class C2384a implements Observer {
    /* renamed from: a */
    private final /* synthetic */ CollectiblesItemsAdapter f19599a;

    public /* synthetic */ C2384a(CollectiblesItemsAdapter collectiblesItemsAdapter) {
        this.f19599a = collectiblesItemsAdapter;
    }

    public final void onChanged(Object obj) {
        this.f19599a.onItems((CollectiblesItem[]) obj);
    }
}
