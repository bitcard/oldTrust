package com.wallet.crypto.trustapp.interact.rx.operator;

import android.text.TextUtils;
import android.util.Patterns;
import com.wallet.crypto.trustapp.entity.DappLink;
import com.wallet.crypto.trustapp.entity.DappLink.Type;
import io.reactivex.functions.Predicate;

public class HistoryLinkPredicate implements Predicate<DappLink> {
    public boolean test(DappLink dappLink) {
        boolean z = true;
        if (dappLink.type != Type.history) {
            return true;
        }
        if (TextUtils.isEmpty(dappLink.url) || !Patterns.WEB_URL.matcher(dappLink.url).matches()) {
            z = false;
        }
        return z;
    }
}
