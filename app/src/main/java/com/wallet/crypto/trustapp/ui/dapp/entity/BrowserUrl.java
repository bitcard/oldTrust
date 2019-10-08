package com.wallet.crypto.trustapp.ui.dapp.entity;

import android.util.Patterns;
import com.wallet.crypto.trustapp.ui.dapp.factory.SearchUrlCreator;
import java.util.regex.Matcher;

public class BrowserUrl {
    /* renamed from: a */
    protected final String f16836a;
    /* renamed from: b */
    private final SearchUrlCreator f16837b;

    public BrowserUrl(String str, SearchUrlCreator searchUrlCreator) {
        if (str == null) {
            str = "";
        }
        this.f16836a = str;
        this.f16837b = searchUrlCreator;
    }

    public String toString() {
        String str = this.f16836a;
        Matcher matcher = Patterns.WEB_URL.matcher(str);
        if (!matcher.find() || matcher.start(0) != 0) {
            return this.f16837b.create(str);
        }
        if (matcher.start(3) != 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
