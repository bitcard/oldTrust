package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.I */
public final /* synthetic */ class C1609I implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ WalletsActivity f17033a;
    /* renamed from: b */
    private final /* synthetic */ Wallet f17034b;

    public /* synthetic */ C1609I(WalletsActivity walletsActivity, Wallet wallet) {
        this.f17033a = walletsActivity;
        this.f17034b = wallet;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f17033a.f23297d.deleteWallet(this.f17034b);
    }
}
