package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetTrasactionsToThisResponse.kt */
public final class GetTrasactionsToThisResponse {
    private final GetTrasactionsToThisTransaction[] transaction;

    public GetTrasactionsToThisResponse(GetTrasactionsToThisTransaction[] getTrasactionsToThisTransactionArr) {
        Intrinsics.checkParameterIsNotNull(getTrasactionsToThisTransactionArr, "transaction");
        this.transaction = getTrasactionsToThisTransactionArr;
    }

    public static /* synthetic */ GetTrasactionsToThisResponse copy$default(GetTrasactionsToThisResponse getTrasactionsToThisResponse, GetTrasactionsToThisTransaction[] getTrasactionsToThisTransactionArr, int i, Object obj) {
        if ((i & 1) != 0) {
            getTrasactionsToThisTransactionArr = getTrasactionsToThisResponse.transaction;
        }
        return getTrasactionsToThisResponse.copy(getTrasactionsToThisTransactionArr);
    }

    public final GetTrasactionsToThisTransaction[] component1() {
        return this.transaction;
    }

    public final GetTrasactionsToThisResponse copy(GetTrasactionsToThisTransaction[] getTrasactionsToThisTransactionArr) {
        Intrinsics.checkParameterIsNotNull(getTrasactionsToThisTransactionArr, "transaction");
        return new GetTrasactionsToThisResponse(getTrasactionsToThisTransactionArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof GetTrasactionsToThisResponse) {
                if (Intrinsics.areEqual(this.transaction, ((GetTrasactionsToThisResponse) obj).transaction)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final GetTrasactionsToThisTransaction[] getTransaction() {
        return this.transaction;
    }

    public int hashCode() {
        GetTrasactionsToThisTransaction[] getTrasactionsToThisTransactionArr = this.transaction;
        return getTrasactionsToThisTransactionArr != null ? Arrays.hashCode(getTrasactionsToThisTransactionArr) : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GetTrasactionsToThisResponse(transaction=");
        stringBuilder.append(Arrays.toString(this.transaction));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
