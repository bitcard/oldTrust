package com.wallet.crypto.trustapp.interact;

import android.net.Uri;
import android.text.TextUtils;
import com.wallet.crypto.trustapp.ui.dapp.entity.UrlHandler;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class UrlHandlerInteract {
    /* renamed from: a */
    private final Map<String, UrlHandler> f16626a = new HashMap<>();

    @Inject
    public UrlHandlerInteract(UrlHandler... urlHandlerArr) {
        for (UrlHandler urlHandler : urlHandlerArr) {
            this.f16626a.put(urlHandler.getScheme(), urlHandler);
        }
    }

    public String handle(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return handle(Uri.parse(str));
    }

    public String handle(Uri uri) {
        if (uri == null) {
            return null;
        }
        if (this.f16626a.containsKey(uri.getScheme())) {
            return ((UrlHandler) this.f16626a.get(uri.getScheme())).handle(uri);
        }
        return uri.toString();
    }
}
