package com.wallet.crypto.trustapp.entity;

/* compiled from: StatusLink.kt */
public enum LinkType {
    DAPP("dappBrowser"),
    BROWSER("browser");
    
    private final String type;

    private LinkType(String str) {
        this.type = str;
    }

    public final String getType() {
        return this.type;
    }
}
