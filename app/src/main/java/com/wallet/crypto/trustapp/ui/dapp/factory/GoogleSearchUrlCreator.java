package com.wallet.crypto.trustapp.ui.dapp.factory;

import android.net.Uri;

public class GoogleSearchUrlCreator implements SearchUrlCreator {
    /* renamed from: a */
    public static final Uri f19686a = Uri.parse("https://www.google.com/search");

    public String create(String str) {
        return f19686a.buildUpon().appendQueryParameter("q", str).build().toString();
    }
}
