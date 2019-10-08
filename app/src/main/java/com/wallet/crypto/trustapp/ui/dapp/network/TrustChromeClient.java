package com.wallet.crypto.trustapp.ui.dapp.network;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import com.wallet.crypto.trustapp.widget.OnProgressListener;

public class TrustChromeClient extends WebChromeClient {
    /* renamed from: a */
    private final OnProgressListener f16880a;
    /* renamed from: b */
    private final FileChooseListener f16881b;

    public TrustChromeClient(OnProgressListener onProgressListener, FileChooseListener fileChooseListener) {
        this.f16880a = onProgressListener;
        this.f16881b = fileChooseListener;
    }

    public void onProgressChanged(WebView webView, int i) {
        this.f16880a.onProgress(i);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        return this.f16881b.onShowFileChooser(valueCallback);
    }
}
