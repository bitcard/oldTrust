package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation;
import com.wallet.crypto.trustapp.ui.walletconnect.view.WCOperationAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WalletConnectActivity.kt */
final class WalletConnectActivity$onCreate$6<T> implements Observer<List<WCOperation>> {
    final /* synthetic */ WalletConnectActivity this$0;

    WalletConnectActivity$onCreate$6(WalletConnectActivity walletConnectActivity) {
        this.this$0 = walletConnectActivity;
    }

    public final void onChanged(List<WCOperation> list) {
        WalletConnectActivity.access$getOperations$p(this.this$0).setText(String.valueOf(list.size()));
        WCOperationAdapter access$getAdapter$p = WalletConnectActivity.access$getAdapter$p(this.this$0);
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        access$getAdapter$p.setData(list);
    }
}
