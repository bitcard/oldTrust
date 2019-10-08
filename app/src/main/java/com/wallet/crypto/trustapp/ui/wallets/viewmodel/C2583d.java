package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.functions.Action;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.viewmodel.d */
public final /* synthetic */ class C2583d implements Action {
    /* renamed from: a */
    private final /* synthetic */ ProgressLiveData f20145a;

    public /* synthetic */ C2583d(ProgressLiveData progressLiveData) {
        this.f20145a = progressLiveData;
    }

    public final void run() {
        this.f20145a.hide();
    }
}
