package com.wallet.crypto.trustapp.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoRequest.kt */
public final class BuyCryptoRequest {
    private final String url;

    public BuyCryptoRequest(String str) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        this.url = str;
    }

    public static /* synthetic */ BuyCryptoRequest copy$default(BuyCryptoRequest buyCryptoRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buyCryptoRequest.url;
        }
        return buyCryptoRequest.copy(str);
    }

    public final String component1() {
        return this.url;
    }

    public final BuyCryptoRequest copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        return new BuyCryptoRequest(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoRequest) {
                if (Intrinsics.areEqual(this.url, ((BuyCryptoRequest) obj).url)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.url;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoRequest(url=");
        stringBuilder.append(this.url);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
