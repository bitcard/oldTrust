package com.wallet.crypto.trustapp.service.trustapi.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: BuyCryptoUrlRequest.kt */
public final class BuyCryptoUrlRequest {
    private final String address;
    private final String id;

    public BuyCryptoUrlRequest(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str2, Address.TYPE_NAME);
        this.id = str;
        this.address = str2;
    }

    public static /* synthetic */ BuyCryptoUrlRequest copy$default(BuyCryptoUrlRequest buyCryptoUrlRequest, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buyCryptoUrlRequest.id;
        }
        if ((i & 2) != 0) {
            str2 = buyCryptoUrlRequest.address;
        }
        return buyCryptoUrlRequest.copy(str, str2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.address;
    }

    public final BuyCryptoUrlRequest copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str2, Address.TYPE_NAME);
        return new BuyCryptoUrlRequest(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoUrlRequest) {
                BuyCryptoUrlRequest buyCryptoUrlRequest = (BuyCryptoUrlRequest) obj;
                if (Intrinsics.areEqual(this.id, buyCryptoUrlRequest.id) && Intrinsics.areEqual(this.address, buyCryptoUrlRequest.address)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.address;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoUrlRequest(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", address=");
        stringBuilder.append(this.address);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
