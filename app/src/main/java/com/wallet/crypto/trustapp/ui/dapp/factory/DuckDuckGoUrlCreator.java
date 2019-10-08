package com.wallet.crypto.trustapp.ui.dapp.factory;

import android.net.Uri;

public class DuckDuckGoUrlCreator implements SearchUrlCreator {
    /* renamed from: a */
    public static final Uri f19685a = Uri.parse("https://duckduckgo.com/");

    public String create(String str) {
        return f19685a.buildUpon().appendQueryParameter("q", str).build().toString();
    }
}
