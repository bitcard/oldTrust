package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetTrasactionsToThisResponse.kt */
public final class GetTrasactionsToThisTransaction {
    private final String txID;

    public GetTrasactionsToThisTransaction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "txID");
        this.txID = str;
    }

    public static /* synthetic */ GetTrasactionsToThisTransaction copy$default(GetTrasactionsToThisTransaction getTrasactionsToThisTransaction, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = getTrasactionsToThisTransaction.txID;
        }
        return getTrasactionsToThisTransaction.copy(str);
    }

    public final String component1() {
        return this.txID;
    }

    public final GetTrasactionsToThisTransaction copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "txID");
        return new GetTrasactionsToThisTransaction(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof GetTrasactionsToThisTransaction) {
                if (Intrinsics.areEqual(this.txID, ((GetTrasactionsToThisTransaction) obj).txID)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getTxID() {
        return this.txID;
    }

    public int hashCode() {
        String str = this.txID;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GetTrasactionsToThisTransaction(txID=");
        stringBuilder.append(this.txID);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
