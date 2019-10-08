package com.wallet.crypto.trustapp.entity;

import trust.blockchain.Slip;

public class DappLink {
    public final long addTime;
    public final Slip coin;
    public final String name;
    public final Type type;
    public final String url;

    public enum Type {
        bookmark(0),
        history(1);
        
        private int value;

        private Type(int i) {
            this.value = i;
        }

        public static Type fromInt(int i) {
            for (Type type : values()) {
                if (type.getValue() == i) {
                    return type;
                }
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    public DappLink(String url, String name, long addTime, Type type, Slip coin) {
        this.url = url;
        this.name = name;
        this.addTime = addTime;
        this.type = type;
        this.coin = coin;
    }
}
