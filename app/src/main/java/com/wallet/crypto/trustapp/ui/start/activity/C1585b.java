package com.wallet.crypto.trustapp.ui.start.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.start.activity.b */
public final /* synthetic */ class C1585b implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ MainActivity f16981a;

    public /* synthetic */ C1585b(MainActivity mainActivity) {
        this.f16981a = mainActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        new ExternalBrowserRouter().open(this.f16981a, Uri.parse("https://medium.com/p/fa50f258274b"));
    }
}
