package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import android.content.Context;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.viewmodel.h */
public final /* synthetic */ class C1550h implements Callable {
    /* renamed from: a */
    private final /* synthetic */ Context f16937a;
    /* renamed from: b */
    private final /* synthetic */ File f16938b;

    public /* synthetic */ C1550h(Context context, File file) {
        this.f16937a = context;
        this.f16938b = file;
    }

    public final Object call() {
        return FileProvider.getUriForFile(this.f16937a, "com.wallet.crypto.trustapp.fileProvider", this.f16938b);
    }
}
