package com.wallet.crypto.trustapp.ui.assets.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.BuyCryptoViewModel;
import kotlin.text.Regex;

/* compiled from: BuyCryptoActivity.kt */
final class BuyCryptoActivity$onCreate$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ BuyCryptoActivity f16761a;

    BuyCryptoActivity$onCreate$1(BuyCryptoActivity buyCryptoActivity) {
        this.f16761a = buyCryptoActivity;
    }

    public final void onClick(View view) {
        BuyCryptoViewModel viewModel$v1_7_010_s3Release = this.f16761a.getViewModel$v1_7_010_s3Release();
        CharSequence obj = BuyCryptoActivity.access$getAmount$p(this.f16761a).getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        stringBuilder.append(this.f16761a.getViewModel$v1_7_010_s3Release().getCurrencySymbol());
        stringBuilder.append("A-Za-z ]");
        viewModel$v1_7_010_s3Release.buyCryptoRequest(new Regex(stringBuilder.toString()).replace(obj, ""));
    }
}
