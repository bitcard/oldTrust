package com.wallet.crypto.trustapp.ui.dapp.network;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.wallet.crypto.trustapp.interact.UrlHandlerInteract;
import com.wallet.crypto.trustapp.ui.dapp.entity.JsInjectorResponse;
import com.wallet.crypto.trustapp.ui.dapp.interact.JsInjectorInteract;
import java.io.ByteArrayInputStream;
import okhttp3.HttpUrl;

public class InterceptBrowserClient extends WebViewClient {
    /* renamed from: a */
    private final Object f16874a = new Object();
    /* renamed from: b */
    private final JsInjectorInteract f16875b;
    /* renamed from: c */
    private final UrlHandlerInteract f16876c;
    /* renamed from: d */
    private BrowserClientListener f16877d;
    /* renamed from: e */
    private boolean f16878e;
    /* renamed from: f */
    private boolean f16879f = true;

    public InterceptBrowserClient(JsInjectorInteract jsInjectorInteract, BrowserClientListener browserClientListener, UrlHandlerInteract urlHandlerInteract) {
        this.f16875b = jsInjectorInteract;
        this.f16876c = urlHandlerInteract;
        this.f16877d = browserClientListener;
    }

    private void injectScriptFile(WebView webView) {
        webView.post(new C1532a(webView, Base64.encodeToString(((String) this.f16875b.assembleJs(webView.getContext(), "%1$s%2$s").blockingGet()).getBytes(), 2)));
    }

    static /* synthetic */ void lambda$injectScriptFile$0(WebView webView, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = window.atob('");
        stringBuilder.append(str);
        stringBuilder.append("');parent.appendChild(script)})()");
        webView.loadUrl(stringBuilder.toString());
    }

    public void onPause() {
        this.f16879f = false;
    }

    public void onReload() {
        synchronized (this.f16874a) {
            this.f16878e = false;
        }
    }

    public void onResume() {
        this.f16879f = true;
    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.cancel();
    }

    @Override
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.f16877d.updateUrl(str);
        this.f16877d.updateHistory(str);
    }

    @Override
    public void onPageFinished(WebView webView, String str) {
        this.f16877d.updateUrl(str);
        this.f16877d.onLoaded();
    }

    @Override
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f16877d.updateUrl(str);
        this.f16877d.onPageStarted();
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest != null && webResourceRequest.getUrl() != null) {
            if (this.f16879f || !webResourceRequest.getUrl().toString().contains(this.f16875b.getRpc())) {
                if (webResourceRequest.getMethod().equalsIgnoreCase("GET") && webResourceRequest.isForMainFrame()) {
                    HttpUrl parse = HttpUrl.parse(webResourceRequest.getUrl().toString());
                    if (parse == null) {
                        return null;
                    }
                    try {
                        JsInjectorResponse jsInjectorResponse = (JsInjectorResponse) this.f16875b.loadUrl(parse.toString(), webResourceRequest.getRequestHeaders()).blockingGet();
                        if (jsInjectorResponse.f16842e) {
                            return null;
                        }
                        WebResourceResponse webResourceResponse = new WebResourceResponse(jsInjectorResponse.f16840c, jsInjectorResponse.f16841d, new ByteArrayInputStream(jsInjectorResponse.f16838a.getBytes()));
                        synchronized (this.f16874a) {
                            this.f16878e = true;
                        }
                        return webResourceResponse;
                    } catch (Exception unused) {
                        return null;
                    }
                }
                if (webResourceRequest.getMethod().equalsIgnoreCase("GET") && (webResourceRequest.getUrl().toString().contains(".js") || webResourceRequest.getUrl().toString().contains("json") || webResourceRequest.getUrl().toString().contains("css"))) {
                    synchronized (this.f16874a) {
                        if (!this.f16878e) {
                            injectScriptFile(webView);
                            this.f16878e = true;
                        }
                    }
                }
                super.shouldInterceptRequest(webView, webResourceRequest);
                return null;
            }
            WebResourceResponse webResourceResponse2 = new WebResourceResponse("text/plain", "charset=UTF-8", new ByteArrayInputStream(new byte[0]));
            webResourceResponse2.setStatusCodeAndReasonPhrase(429, "Stop on background");
            return webResourceResponse2;
        }
        return null;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return shouldOverrideUrlLoading(str, false, false);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean z = false;
        if (webResourceRequest != null) {
            if (webView != null) {
                String uri = webResourceRequest.getUrl().toString();
                boolean isForMainFrame = webResourceRequest.isForMainFrame();
                if (VERSION.SDK_INT >= 24 && webResourceRequest.isRedirect()) {
                    z = true;
                }
                return shouldOverrideUrlLoading(uri, isForMainFrame, z);
            }
        }
        return false;
    }

    private boolean shouldOverrideUrlLoading(String str, boolean z, boolean z2) {
        boolean z3;
        synchronized (this.f16874a) {
            z3 = false;
            this.f16878e = false;
        }
        String handle = this.f16876c.handle(str);
        if (TextUtils.isEmpty(handle)) {
            z3 = true;
        } else if (!str.startsWith("http")) {
            z3 = true;
        }
        if (z && z2) {
            z3 = true;
        } else {
            str = handle;
        }
        if (z3 && !TextUtils.isEmpty(str)) {
            this.f16877d.updateUrl(str);
            this.f16877d.newPageLoad(str);
        }
        return z3;
    }
}
