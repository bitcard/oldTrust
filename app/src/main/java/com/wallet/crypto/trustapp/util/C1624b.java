package com.wallet.crypto.trustapp.util;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.util.b */
public final /* synthetic */ class C1624b implements OnFocusChangeListener {
    /* renamed from: a */
    private final /* synthetic */ EditText f17092a;

    public /* synthetic */ C1624b(EditText editText) {
        this.f17092a = editText;
    }

    public final void onFocusChange(View view, boolean z) {
        KeyboardUtils.lambda$focusDialogField$0(this.f17092a, view, z);
    }
}
