package com.wallet.crypto.trustapp.ui.dapp.network;

import android.webkit.WebView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.dapp.network.a */
public final /* synthetic */ class C1532a implements Runnable {
    /* renamed from: a */
    private final /* synthetic */ WebView f16882a;
    /* renamed from: b */
    private final /* synthetic */ String f16883b;

    public /* synthetic */ C1532a(WebView webView, String str) {
        this.f16882a = webView;
        this.f16883b = str;
    }

    public final void run() {
        InterceptBrowserClient.lambda$injectScriptFile$0(this.f16882a, this.f16883b);
    }
}
