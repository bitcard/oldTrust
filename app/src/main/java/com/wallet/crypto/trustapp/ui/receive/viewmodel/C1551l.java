package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import java.io.File;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.viewmodel.l */
public final /* synthetic */ class C1551l implements Callable {
    /* renamed from: a */
    private final /* synthetic */ File f16939a;

    public /* synthetic */ C1551l(File file) {
        this.f16939a = file;
    }

    public final Object call() {
        return Boolean.valueOf(this.f16939a.exists());
    }
}
