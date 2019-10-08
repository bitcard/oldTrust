package com.wallet.crypto.trustapp.entity;

import java.util.Currency;
import java.util.Locale;

public class CurrencyInfo {
    public static final CurrencyInfo DEFAULT = new CurrencyInfo("USD", 1);
    public String currencyCode;
    public int rating;

    public CurrencyInfo(String str, int i) {
        this.currencyCode = str;
        this.rating = i;
    }

    public static String codeToSymbol(String str) {
        try {
            str = Currency.getInstance(str).getSymbol(Locale.getDefault());
            return str;
        } catch (RuntimeException unused) {
            return str;
        }
    }
}
