package com.wallet.crypto.trustapp.ui.dex.fragment;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderStatus;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrderDialogFragment.kt */
final class OrderDialogFragment$onResume$1<T> implements Observer<TradeOrderStatus> {
    /* renamed from: a */
    final /* synthetic */ OrderDialogFragment f19608a;

    OrderDialogFragment$onResume$1(OrderDialogFragment orderDialogFragment) {
        this.f19608a = orderDialogFragment;
    }

    public final void onChanged(TradeOrderStatus tradeOrderStatus) {
        OrderDialogFragment orderDialogFragment = this.f19608a;
        Intrinsics.checkExpressionValueIsNotNull(tradeOrderStatus, "it");
        orderDialogFragment.bind(tradeOrderStatus);
    }
}
