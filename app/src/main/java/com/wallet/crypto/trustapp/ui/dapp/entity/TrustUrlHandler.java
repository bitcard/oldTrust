package com.wallet.crypto.trustapp.ui.dapp.entity;

import android.net.Uri;

public class TrustUrlHandler implements UrlHandler {
    private String extractFromUri(Uri uri) {
        return "open_url".equalsIgnoreCase(uri.getLastPathSegment()) ? uri.getQueryParameter("url") : null;
    }

    public String getScheme() {
        return "trust";
    }

    public String handle(Uri uri) {
        return extractFromUri(uri);
    }
}
