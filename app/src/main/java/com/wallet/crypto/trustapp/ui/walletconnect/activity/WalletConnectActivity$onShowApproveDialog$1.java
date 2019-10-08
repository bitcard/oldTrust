package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WalletConnectViewData;

/* compiled from: WalletConnectActivity.kt */
final class WalletConnectActivity$onShowApproveDialog$1 implements OnClickListener {
    final /* synthetic */ WalletConnectViewData $wcViewData;
    final /* synthetic */ WalletConnectActivity this$0;

    WalletConnectActivity$onShowApproveDialog$1(WalletConnectActivity walletConnectActivity, WalletConnectViewData walletConnectViewData) {
        this.this$0 = walletConnectActivity;
        this.$wcViewData = walletConnectViewData;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.getViewModel().approveSession(this.$wcViewData);
    }
}
