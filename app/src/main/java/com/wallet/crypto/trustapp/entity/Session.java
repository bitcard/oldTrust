package com.wallet.crypto.trustapp.entity;

import trust.blockchain.entity.Wallet;

public class Session {
    public final String currencyCode;
    public final Wallet wallet;

    public Session(Wallet wallet) {
        this(wallet, CurrencyInfo.DEFAULT.currencyCode);
    }

    public Session(Wallet wallet, String str) {
        this.wallet = wallet;
        this.currencyCode = str;
    }
}
