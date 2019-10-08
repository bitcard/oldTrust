package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: WalletConnectActivity.kt */
final class WalletConnectActivity$onShowApproveDialog$2 implements OnClickListener {
    final /* synthetic */ WalletConnectActivity this$0;

    WalletConnectActivity$onShowApproveDialog$2(WalletConnectActivity walletConnectActivity) {
        this.this$0 = walletConnectActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.getViewModel().rejectSession();
        this.this$0.finish();
    }
}
