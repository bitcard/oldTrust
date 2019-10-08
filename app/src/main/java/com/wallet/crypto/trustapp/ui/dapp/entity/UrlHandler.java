package com.wallet.crypto.trustapp.ui.dapp.entity;

import android.net.Uri;

public interface UrlHandler {
    String getScheme();

    String handle(Uri uri);
}
