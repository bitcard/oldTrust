package com.wallet.crypto.trustapp.ui.settings.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.activity.b */
public final /* synthetic */ class C1553b implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ AboutActivity f16941a;
    /* renamed from: b */
    private final /* synthetic */ String f16942b;

    public /* synthetic */ C1553b(AboutActivity aboutActivity, String str) {
        this.f16941a = aboutActivity;
        this.f16942b = str;
    }

    public final void onClick(View view) {
        this.f16941a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f16942b)));
    }
}
