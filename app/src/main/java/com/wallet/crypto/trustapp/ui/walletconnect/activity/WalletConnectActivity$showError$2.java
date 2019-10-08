package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: WalletConnectActivity.kt */
final class WalletConnectActivity$showError$2 implements OnCancelListener {
    final /* synthetic */ WalletConnectActivity this$0;

    WalletConnectActivity$showError$2(WalletConnectActivity walletConnectActivity) {
        this.this$0 = walletConnectActivity;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.this$0.getViewModel().rejectOperation();
    }
}
