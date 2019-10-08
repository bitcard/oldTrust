package com.wallet.crypto.trustapp.ui.dex.fragment;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: OrderDialogFragment.kt */
final class OrderDialogFragment$onViewCreated$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ OrderDialogFragment f16906a;

    OrderDialogFragment$onViewCreated$1(OrderDialogFragment orderDialogFragment) {
        this.f16906a = orderDialogFragment;
    }

    public final void onClick(View view) {
        OnClickListener onClickListener = this.f16906a.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
        this.f16906a.dismiss();
    }
}
