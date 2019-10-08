package com.wallet.crypto.trustapp.service.trustapi.entity;

import java.util.List;

public class PushNotificationEntry {
    public static final String ANDROID = "android";
    public String _id;
    public String createdAt;
    public Preferences preferences;
    public String token;
    public String type;
    public String updatedAt;
    public List<String> wallets;

    class Preferences {
        boolean isAirDrop;

        Preferences() {
        }
    }

    PushNotificationEntry() {
    }

    PushNotificationEntry(String str) {
        this(str, null);
    }

    PushNotificationEntry(String str, List<String> list) {
        this.token = str;
        this.type = ANDROID;
        this.wallets = list;
    }
}
