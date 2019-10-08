package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.BackupView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.y */
public final /* synthetic */ class C1615y implements OnDismissListener {
    /* renamed from: a */
    private final /* synthetic */ BackupView f17042a;

    public /* synthetic */ C1615y(BackupView backupView) {
        this.f17042a = backupView;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        KeyboardUtils.hideKeyboard(this.f17042a.findViewById(R.id.password));
    }
}
