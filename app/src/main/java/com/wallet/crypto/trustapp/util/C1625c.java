package com.wallet.crypto.trustapp.util;

import android.widget.EditText;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.util.c */
public final /* synthetic */ class C1625c implements Runnable {
    /* renamed from: a */
    private final /* synthetic */ EditText f17093a;

    public /* synthetic */ C1625c(EditText editText) {
        this.f17093a = editText;
    }

    public final void run() {
        KeyboardUtils.showKeyboard(this.f17093a);
    }
}
