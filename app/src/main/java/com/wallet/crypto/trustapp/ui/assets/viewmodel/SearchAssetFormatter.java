package com.wallet.crypto.trustapp.ui.assets.viewmodel;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Value;

/* compiled from: SearchAssetFormatter.kt */
public final class SearchAssetFormatter extends BaseAssetFormatter {
    /* renamed from: c */
    private final int f21324c = Color.parseColor("#7F7F7F");

    public SubunitValue calculateBalance(Contract contract, Value value) {
        Intrinsics.checkParameterIsNotNull(contract, "contract");
        return null;
    }

    public Spannable formatCurrencyBalance(Ticker ticker, SubunitValue subunitValue) {
        return new SpannableString("");
    }

    public Spannable formatName(Asset asset) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        if (TextUtils.isEmpty(asset.contract.name)) {
            return new SpannableString(TextUtils.isEmpty((CharSequence) asset.contract.unit.symbol) ? "" : asset.contract.unit.symbol);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(asset.contract.name);
        stringBuilder.append(' ');
        stringBuilder.append(TextUtils.isEmpty((CharSequence) asset.contract.unit.symbol) ? "" : asset.contract.unit.symbol);
        String stringBuilder2 = stringBuilder.toString();
        SpannableString spannableString = new SpannableString(stringBuilder2);
        spannableString.setSpan(new ForegroundColorSpan(this.f21324c), asset.contract.name.length(), stringBuilder2.length(), 0);
        return spannableString;
    }

    public Spannable formatPrice(Ticker ticker) {
        return new SpannableString("");
    }

    public boolean shouldShowCoinAddress() {
        return false;
    }
}
