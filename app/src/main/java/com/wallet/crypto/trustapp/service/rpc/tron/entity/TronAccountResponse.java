package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TronAccountResponse.kt */
public final class TronAccountResponse {
    private final String address;
    private final TronAssetResponse[] assetV2;
    private final long balance;

    public TronAccountResponse(String str, long j, TronAssetResponse[] tronAssetResponseArr) {
        this.address = str;
        this.balance = j;
        this.assetV2 = tronAssetResponseArr;
    }

    public static /* synthetic */ TronAccountResponse copy$default(TronAccountResponse tronAccountResponse, String str, long j, TronAssetResponse[] tronAssetResponseArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tronAccountResponse.address;
        }
        if ((i & 2) != 0) {
            j = tronAccountResponse.balance;
        }
        if ((i & 4) != 0) {
            tronAssetResponseArr = tronAccountResponse.assetV2;
        }
        return tronAccountResponse.copy(str, j, tronAssetResponseArr);
    }

    public final String component1() {
        return this.address;
    }

    public final long component2() {
        return this.balance;
    }

    public final TronAssetResponse[] component3() {
        return this.assetV2;
    }

    public final TronAccountResponse copy(String str, long j, TronAssetResponse[] tronAssetResponseArr) {
        return new TronAccountResponse(str, j, tronAssetResponseArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TronAccountResponse) {
                TronAccountResponse tronAccountResponse = (TronAccountResponse) obj;
                if (Intrinsics.areEqual(this.address, tronAccountResponse.address)) {
                    if ((this.balance == tronAccountResponse.balance) && Intrinsics.areEqual(this.assetV2, tronAccountResponse.assetV2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getAddress() {
        return this.address;
    }

    public final TronAssetResponse[] getAssetV2() {
        return this.assetV2;
    }

    public final long getBalance() {
        return this.balance;
    }

    public int hashCode() {
        String str = this.address;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.balance;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        TronAssetResponse[] tronAssetResponseArr = this.assetV2;
        if (tronAssetResponseArr != null) {
            i = Arrays.hashCode(tronAssetResponseArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TronAccountResponse(address=");
        stringBuilder.append(this.address);
        stringBuilder.append(", balance=");
        stringBuilder.append(this.balance);
        stringBuilder.append(", assetV2=");
        stringBuilder.append(Arrays.toString(this.assetV2));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
