package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AlertDialog;
import com.wallet.crypto.trustapp.widget.BackupView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.wallets.activity.z */
public final /* synthetic */ class C1616z implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ WalletInfoActivity f17043a;
    /* renamed from: b */
    private final /* synthetic */ BackupView f17044b;
    /* renamed from: c */
    private final /* synthetic */ AlertDialog f17045c;

    public /* synthetic */ C1616z(WalletInfoActivity walletInfoActivity, BackupView backupView, AlertDialog alertDialog) {
        this.f17043a = walletInfoActivity;
        this.f17044b = backupView;
        this.f17045c = alertDialog;
    }

    public final void onClick(View view) {
        WalletInfoActivity.m376a(this.f17043a, this.f17044b, this.f17045c, view);
    }
}
