package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransactionParameter {
    private final FindTransactionValue value;

    public FindTransactionParameter(FindTransactionValue findTransactionValue) {
        Intrinsics.checkParameterIsNotNull(findTransactionValue, "value");
        this.value = findTransactionValue;
    }

    public static /* synthetic */ FindTransactionParameter copy$default(FindTransactionParameter findTransactionParameter, FindTransactionValue findTransactionValue, int i, Object obj) {
        if ((i & 1) != 0) {
            findTransactionValue = findTransactionParameter.value;
        }
        return findTransactionParameter.copy(findTransactionValue);
    }

    public final FindTransactionValue component1() {
        return this.value;
    }

    public final FindTransactionParameter copy(FindTransactionValue findTransactionValue) {
        Intrinsics.checkParameterIsNotNull(findTransactionValue, "value");
        return new FindTransactionParameter(findTransactionValue);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransactionParameter) {
                if (Intrinsics.areEqual(this.value, ((FindTransactionParameter) obj).value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final FindTransactionValue getValue() {
        return this.value;
    }

    public int hashCode() {
        FindTransactionValue findTransactionValue = this.value;
        return findTransactionValue != null ? findTransactionValue.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransactionParameter(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
