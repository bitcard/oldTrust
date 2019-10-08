package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TronAssetResponse.kt */
public final class TronAssetResponse {
    private final String key;
    private final long value;

    public TronAssetResponse(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        this.key = str;
        this.value = j;
    }

    public static /* synthetic */ TronAssetResponse copy$default(TronAssetResponse tronAssetResponse, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tronAssetResponse.key;
        }
        if ((i & 2) != 0) {
            j = tronAssetResponse.value;
        }
        return tronAssetResponse.copy(str, j);
    }

    public final String component1() {
        return this.key;
    }

    public final long component2() {
        return this.value;
    }

    public final TronAssetResponse copy(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return new TronAssetResponse(str, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TronAssetResponse) {
                TronAssetResponse tronAssetResponse = (TronAssetResponse) obj;
                if (Intrinsics.areEqual(this.key, tronAssetResponse.key)) {
                    if (this.value == tronAssetResponse.value) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getKey() {
        return this.key;
    }

    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.key;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.value;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TronAssetResponse(key=");
        stringBuilder.append(this.key);
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
