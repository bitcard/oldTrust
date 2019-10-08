package com.wallet.crypto.trustapp.ui.dapp.network;

import android.text.TextUtils;
import android.webkit.CookieManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class WebViewCookieJar implements CookieJar {
    /* renamed from: a */
    private CookieManager f19731a;

    public WebViewCookieJar() {
        try {
            this.f19731a = CookieManager.getInstance();
        } catch (Exception unused) {
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        if (this.f19731a != null) {
            String cookie = this.f19731a.getCookie(httpUrl.toString());
            if (!(cookie == null || TextUtils.isEmpty(cookie))) {
                String[] split = cookie.split(";");
                ArrayList arrayList = new ArrayList();
                for (String parse : split) {
                    arrayList.add(Cookie.parse(httpUrl, parse));
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (this.f19731a != null) {
            String httpUrl2 = httpUrl.toString();
            for (Cookie cookie : list) {
                this.f19731a.setCookie(httpUrl2, cookie.toString());
            }
        }
    }
}
