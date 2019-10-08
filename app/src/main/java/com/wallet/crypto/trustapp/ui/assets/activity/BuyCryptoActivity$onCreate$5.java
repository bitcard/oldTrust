package com.wallet.crypto.trustapp.ui.assets.activity;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.BuyCryptoQuote;

/* compiled from: BuyCryptoActivity.kt */
final class BuyCryptoActivity$onCreate$5<T> implements Observer<BuyCryptoQuote> {
    /* renamed from: a */
    final /* synthetic */ BuyCryptoActivity f19430a;

    BuyCryptoActivity$onCreate$5(BuyCryptoActivity buyCryptoActivity) {
        this.f19430a = buyCryptoActivity;
    }

    public final void onChanged(BuyCryptoQuote buyCryptoQuote) {
        CharSequence charSequence;
        TextView access$getFiatAmount$p = BuyCryptoActivity.access$getFiatAmount$p(this.f19430a);
        if (buyCryptoQuote == null) {
            charSequence = "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('~');
            stringBuilder.append(buyCryptoQuote.getDigitalMoney().getAmount());
            stringBuilder.append(' ');
            stringBuilder.append(buyCryptoQuote.getDigitalMoney().getCurrency());
            charSequence = stringBuilder.toString();
        }
        access$getFiatAmount$p.setText(charSequence);
    }
}
