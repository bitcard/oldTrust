package com.wallet.crypto.trustapp.ui.assets.activity;

import android.net.Uri;
import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.entity.BuyCryptoRequest;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;

/* compiled from: BuyCryptoActivity.kt */
final class BuyCryptoActivity$onCreate$6<T> implements Observer<BuyCryptoRequest> {
    /* renamed from: a */
    final /* synthetic */ BuyCryptoActivity f19431a;

    BuyCryptoActivity$onCreate$6(BuyCryptoActivity buyCryptoActivity) {
        this.f19431a = buyCryptoActivity;
    }

    public final void onChanged(BuyCryptoRequest buyCryptoRequest) {
        new ExternalBrowserRouter().open(this.f19431a, Uri.parse(buyCryptoRequest.getUrl()));
        this.f19431a.finish();
    }
}
