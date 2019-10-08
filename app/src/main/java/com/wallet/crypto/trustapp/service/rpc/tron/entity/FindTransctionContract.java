package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransctionContract {
    private final FindTransactionParameter parameter;

    public FindTransctionContract(FindTransactionParameter findTransactionParameter) {
        Intrinsics.checkParameterIsNotNull(findTransactionParameter, "parameter");
        this.parameter = findTransactionParameter;
    }

    public static /* synthetic */ FindTransctionContract copy$default(FindTransctionContract findTransctionContract, FindTransactionParameter findTransactionParameter, int i, Object obj) {
        if ((i & 1) != 0) {
            findTransactionParameter = findTransctionContract.parameter;
        }
        return findTransctionContract.copy(findTransactionParameter);
    }

    public final FindTransactionParameter component1() {
        return this.parameter;
    }

    public final FindTransctionContract copy(FindTransactionParameter findTransactionParameter) {
        Intrinsics.checkParameterIsNotNull(findTransactionParameter, "parameter");
        return new FindTransctionContract(findTransactionParameter);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransctionContract) {
                if (Intrinsics.areEqual(this.parameter, ((FindTransctionContract) obj).parameter)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final FindTransactionParameter getParameter() {
        return this.parameter;
    }

    public int hashCode() {
        FindTransactionParameter findTransactionParameter = this.parameter;
        return findTransactionParameter != null ? findTransactionParameter.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransctionContract(parameter=");
        stringBuilder.append(this.parameter);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
