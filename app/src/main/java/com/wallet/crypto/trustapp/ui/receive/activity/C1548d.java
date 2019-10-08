package com.wallet.crypto.trustapp.ui.receive.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.activity.d */
public final /* synthetic */ class C1548d implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ ReceiveActivity f16926a;
    /* renamed from: b */
    private final /* synthetic */ EditText f16927b;

    public /* synthetic */ C1548d(ReceiveActivity receiveActivity, EditText editText) {
        this.f16926a = receiveActivity;
        this.f16927b = editText;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        ReceiveActivity.m358a(this.f16926a, this.f16927b, dialogInterface, i);
    }
}
