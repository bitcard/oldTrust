package com.wallet.crypto.trustapp.ui.dapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.dapp.controller.TrustJavaScriptInterface;
import com.wallet.crypto.trustapp.ui.dapp.network.InterceptBrowserClient;
import com.wallet.crypto.trustapp.ui.dapp.network.TrustChromeClient;
import com.wallet.crypto.trustapp.util.EIP712TypedData;
import com.wallet.crypto.trustapp.widget.OnProgressListener;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.SignedMessage;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

public class BrowserView extends RelativeLayout implements OnProgressListener {
    /* renamed from: a */
    private WebView f19739a;
    /* renamed from: b */
    private ProgressBar f19740b;
    /* renamed from: c */
    private TrustJavaScriptInterface f19741c;
    /* renamed from: d */
    private WebViewClient f19742d;

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.view.BrowserView$1 */
    class C15331 extends ServiceWorkerClient {
        C15331() {
        }

        public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
            return BrowserView.this.f19742d.shouldInterceptRequest(BrowserView.this.f19739a, webResourceRequest);
        }
    }

    public BrowserView(Context context) {
        super(context);
    }

    private static void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }

    private void enableCookies() {
        CookieManager.getInstance().setAcceptCookie(true);
    }

    private void getWebView(SwipeRefreshLayout swipeRefreshLayout) {
        for (int i = 0; i < swipeRefreshLayout.getChildCount(); i++) {
            if (swipeRefreshLayout.getChildAt(i) instanceof WebView) {
                this.f19739a = (WebView) swipeRefreshLayout.getChildAt(i);
            }
        }
        if (this.f19739a == null) {
            this.f19739a = new WebView(getContext());
            this.f19739a.setLayoutParams(new LayoutParams(-1, -1));
            swipeRefreshLayout.addView(this.f19739a);
        }
    }

    private void initServiceWorker() {
        if (VERSION.SDK_INT >= 24) {
            try {
                ServiceWorkerController.getInstance().setServiceWorkerClient(new C15331());
            } catch (Throwable unused) {
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebSettings(Context context, WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setMixedContentMode(1);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        initServiceWorker();
    }

    private void initWebView() {
        WebView.setWebContentsDebuggingEnabled("s3".equals("s3"));
        initWebSettings(this.f19739a.getContext(), this.f19739a.getSettings());
        enableCookies();
    }

    public void callbackToJS(long j, String str, String str2) {
        String format;
        if (j == -1) {
            j = this.f19741c.getCallbackId();
        }
        if (TextUtils.isEmpty(str)) {
            format = String.format("window.ethereum.sendResponse(%1$s, '%2$s')", new Object[]{Long.valueOf(j), str2});
        } else {
            format = String.format("window.ethereum.sendError(%1$s, '%2$s')", new Object[]{Long.valueOf(j), str});
        }
        this.f19739a.post(() -> f19739a.evaluateJavascript(format, s -> Log.d("WEB_VIEW", s)));
    }

    public boolean canGoBack() {
        return this.f19739a.canGoBack();
    }

    public boolean canGoForward() {
        return this.f19739a.canGoForward();
    }

    public void clearCache() {
        clearCookies();
        this.f19739a.clearCache(true);
        this.f19739a.clearFormData();
        this.f19739a.clearHistory();
        this.f19739a.clearMatches();
        this.f19739a.clearSslPreferences();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        this.f19739a.evaluateJavascript(str, valueCallback);
    }

    public String getCurrentLink() {
        return this.f19739a.getUrl();
    }

    public String getTitle() {
        return this.f19739a.getTitle();
    }

    public void goBack() {
        if (this.f19739a.canGoBack()) {
            this.f19739a.goBack();
        }
    }

    public void goForward() {
        if (this.f19739a.canGoForward()) {
            this.f19739a.goForward();
        }
    }

    public void hideProgress() {
        this.f19740b.setVisibility(GONE);
    }

    public void loadUrl(String str) {
        this.f19739a.requestFocus();
        if (TextUtils.isEmpty(str) || !str.equals(this.f19739a.getUrl())) {
            this.f19739a.loadUrl(str);
        } else {
            this.f19739a.reload();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        getWebView((SwipeRefreshLayout) findViewById(R.id.refresh_layout));
        this.f19740b = (ProgressBar) findViewById(R.id.progress);
        this.f19740b.setIndeterminate(false);
        this.f19739a.requestFocus();
        initWebView();
    }

    public void onPause() {
        this.f19739a.onPause();
    }

    public void onProgress(int i) {
        if (i >= 100) {
            hideProgress();
            return;
        }
        showProgress();
        this.f19740b.setProgress(i);
    }

    public void onReload() {
        WebViewClient webViewClient = this.f19742d;
        if (webViewClient instanceof InterceptBrowserClient) {
            ((InterceptBrowserClient) webViewClient).onReload();
        }
        this.f19739a.reload();
    }

    public void onResume() {
        this.f19739a.onResume();
    }

    public void onSignCancel(Message message) {
        onSignError(message, "cancelled");
    }

    public void onSignError(Message message, String str) {
        callbackToJS(message.leafPosition, str, null);
    }

    public void onSignMessageSuccessful(SignedMessage<String> signedMessage) {
        callbackToJS(signedMessage.leafPosition, null, signedMessage.signHex);
    }

    public void onSignPersonalMessageSuccessful(SignedMessage<String> signedMessage) {
        callbackToJS(signedMessage.leafPosition, null, signedMessage.signHex);
    }

    public void onSignSuccessful(TransactionSigned transactionSigned) {
        callbackToJS(-1, null, transactionSigned.hash);
    }

    public void onSignTypedMessageSuccessful(SignedMessage<EIP712TypedData> signedMessage) {
        callbackToJS(signedMessage.leafPosition, null, signedMessage.signHex);
    }

    public void setChromeClient(TrustChromeClient trustChromeClient) {
        this.f19739a.setWebChromeClient(trustChromeClient);
    }

    public void setJsInterface(TrustJavaScriptInterface trustJavaScriptInterface) {
        this.f19741c = trustJavaScriptInterface;
        this.f19739a.addJavascriptInterface(trustJavaScriptInterface, "trust");
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.f19742d = webViewClient;
        this.f19739a.setWebViewClient(webViewClient);
    }

    public void showProgress() {
        this.f19740b.setVisibility(VISIBLE);
    }

    public void webViewRequestFocus() {
        this.f19739a.requestFocus();
    }

    public BrowserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onSignCancel(TransactionUnsigned transactionUnsigned) {
        onSignError(transactionUnsigned, "cancelled");
    }

    public void onSignError(TransactionUnsigned transactionUnsigned, String str) {
        callbackToJS(-1, str, null);
    }

    public BrowserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
