package com.wallet.crypto.trustapp.ui.importwallet.viewmodel;

import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.functions.Action;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.importwallet.viewmodel.j */
public final /* synthetic */ class C2447j implements Action {
    /* renamed from: a */
    private final /* synthetic */ ProgressLiveData f19800a;

    public /* synthetic */ C2447j(ProgressLiveData progressLiveData) {
        this.f19800a = progressLiveData;
    }

    public final void run() {
        this.f19800a.hide();
    }
}
