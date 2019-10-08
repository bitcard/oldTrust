package com.wallet.crypto.trustapp.ui.start.viewmodel;

import android.app.Activity;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.router.OpenWalletInfoRouter;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.start.viewmodel.i */
public final /* synthetic */ class C2516i implements Consumer {
    /* renamed from: a */
    private final /* synthetic */ Activity f19982a;

    public /* synthetic */ C2516i(Activity activity) {
        this.f19982a = activity;
    }

    public final void accept(Object obj) {
        new OpenWalletInfoRouter().open(this.f19982a, ((Session) obj).wallet);
    }
}
