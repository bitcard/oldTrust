package com.wallet.crypto.trustapp.ui.wallets.activity;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.BackupView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.s */
public final /* synthetic */ class C1611s implements Runnable {
    /* renamed from: a */
    private final /* synthetic */ BackupView f17038a;

    public /* synthetic */ C1611s(BackupView backupView) {
        this.f17038a = backupView;
    }

    public final void run() {
        KeyboardUtils.showKeyboard(this.f17038a.findViewById(R.id.password));
    }
}
