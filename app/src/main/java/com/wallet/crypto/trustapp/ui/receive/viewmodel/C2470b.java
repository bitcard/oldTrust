package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.functions.Action;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.viewmodel.b */
public final /* synthetic */ class C2470b implements Action {
    /* renamed from: a */
    private final /* synthetic */ ProgressLiveData f19881a;

    public /* synthetic */ C2470b(ProgressLiveData progressLiveData) {
        this.f19881a = progressLiveData;
    }

    public final void run() {
        this.f19881a.hide();
    }
}
