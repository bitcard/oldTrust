package com.wallet.crypto.trustapp.ui.assets.activity;

import android.view.View;
import androidx.lifecycle.Observer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoActivity.kt */
final class BuyCryptoActivity$onCreate$2<T> implements Observer<Boolean> {
    /* renamed from: a */
    final /* synthetic */ BuyCryptoActivity f19427a;

    BuyCryptoActivity$onCreate$2(BuyCryptoActivity buyCryptoActivity) {
        this.f19427a = buyCryptoActivity;
    }

    public final void onChanged(Boolean bool) {
        View access$getQuoteProgress$p = BuyCryptoActivity.access$getQuoteProgress$p(this.f19427a);
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        access$getQuoteProgress$p.setVisibility(bool.booleanValue() ? 0 : 8);
    }
}
