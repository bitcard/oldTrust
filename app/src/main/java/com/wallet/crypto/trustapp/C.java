package com.wallet.crypto.trustapp;

import android.net.Uri;
import okhttp3.MediaType;
import trust.blockchain.Slip;
import wallet.core.jni.CoinTypeConfiguration;

/* renamed from: com.wallet.crypto.trustapp.C C1438C */
public abstract class C {
    /* renamed from: a */
    public static final Uri DAPP_LINK_ICON_URI = Uri.parse("https://assets.trustwalletapp.com/dapps");  // f16598a
    /* renamed from: b */   //JSON_MIME
    public static final MediaType f16599b = MediaType.parse("application/json; charset=utf-8");
    /* renamed from: c */
    public static final MediaType f16600c = MediaType.parse("application/json");
    /* renamed from: d */
    public static final MediaType f16601d = MediaType.parse("text/plain; charset=utf-8");
    /* renamed from: e */
    public static final MediaType f16602e = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    /* renamed from: f */
    public static String f16603f = "trustwallet";

    public static String rpcUrl(Slip slip) {
        return String.format("https://%s-rpc.trustwalletapp.com", new Object[]{CoinTypeConfiguration.getID(slip.coinType())});
    }
}
