package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation;

/* compiled from: WalletConnectActivity.kt */
final class WalletConnectActivity$onOperation$2 implements OnClickListener {
    final /* synthetic */ WCOperation $data;

    WalletConnectActivity$onOperation$2(WCOperation wCOperation) {
        this.$data = wCOperation;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.$data.getRejectCall().invoke();
    }
}
