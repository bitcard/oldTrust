package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BroadcastTransactionResponse.kt */
public final class BroadcastTransactionResponse {
    private final String message;
    private final boolean result;

    public BroadcastTransactionResponse(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.result = z;
        this.message = str;
    }

    public static /* synthetic */ BroadcastTransactionResponse copy$default(BroadcastTransactionResponse broadcastTransactionResponse, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = broadcastTransactionResponse.result;
        }
        if ((i & 2) != 0) {
            str = broadcastTransactionResponse.message;
        }
        return broadcastTransactionResponse.copy(z, str);
    }

    public final boolean component1() {
        return this.result;
    }

    public final String component2() {
        return this.message;
    }

    public final BroadcastTransactionResponse copy(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        return new BroadcastTransactionResponse(z, str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BroadcastTransactionResponse) {
                BroadcastTransactionResponse broadcastTransactionResponse = (BroadcastTransactionResponse) obj;
                if ((this.result == broadcastTransactionResponse.result) && Intrinsics.areEqual(this.message, broadcastTransactionResponse.message)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getMessage() {
        return this.message;
    }

    public final boolean getResult() {
        return this.result;
    }

    public int hashCode() {
        int i = 0;
        if (this.result != false) {
            i = 1;
        }
        i *= 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BroadcastTransactionResponse(result=");
        stringBuilder.append(this.result);
        stringBuilder.append(", message=");
        stringBuilder.append(this.message);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
