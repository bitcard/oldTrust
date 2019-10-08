package com.wallet.crypto.trustapp.service.rpc.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeeResponse.kt */
public final class FeeResponse {
    private final String result;

    public FeeResponse(String str) {
        Intrinsics.checkParameterIsNotNull(str, "result");
        this.result = str;
    }

    public static /* synthetic */ FeeResponse copy$default(FeeResponse feeResponse, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = feeResponse.result;
        }
        return feeResponse.copy(str);
    }

    public final String component1() {
        return this.result;
    }

    public final FeeResponse copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "result");
        return new FeeResponse(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FeeResponse) {
                if (Intrinsics.areEqual(this.result, ((FeeResponse) obj).result)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getResult() {
        return this.result;
    }

    public int hashCode() {
        String str = this.result;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FeeResponse(result=");
        stringBuilder.append(this.result);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
