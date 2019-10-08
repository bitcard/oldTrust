package com.wallet.crypto.trustapp.ui.transfer.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.transfer.fragment.e */
public final /* synthetic */ class C1600e implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ ConfirmTransactionFragment f17015a;
    /* renamed from: b */
    private final /* synthetic */ ErrorEnvelope f17016b;

    public /* synthetic */ C1600e(ConfirmTransactionFragment confirmTransactionFragment, ErrorEnvelope errorEnvelope) {
        this.f17015a = confirmTransactionFragment;
        this.f17016b = errorEnvelope;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        ConfirmTransactionFragment.m312a(this.f17015a, this.f17016b, dialogInterface, i);
    }
}
