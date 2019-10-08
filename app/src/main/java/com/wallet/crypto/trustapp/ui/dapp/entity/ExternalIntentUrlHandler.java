package com.wallet.crypto.trustapp.ui.dapp.entity;

import android.net.Uri;

public class ExternalIntentUrlHandler implements UrlHandler {
    public String getScheme() {
        return "intent";
    }

    public String handle(Uri uri) {
        return null;
    }
}
