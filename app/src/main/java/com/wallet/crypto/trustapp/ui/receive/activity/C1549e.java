package com.wallet.crypto.trustapp.ui.receive.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.EditText;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.activity.e */
public final /* synthetic */ class C1549e implements OnDismissListener {
    /* renamed from: a */
    private final /* synthetic */ EditText f16928a;

    public /* synthetic */ C1549e(EditText editText) {
        this.f16928a = editText;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.f16928a.clearFocus();
    }
}
