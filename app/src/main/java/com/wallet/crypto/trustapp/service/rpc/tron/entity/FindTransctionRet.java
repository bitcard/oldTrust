package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransctionRet {
    private final String contractRet;

    public FindTransctionRet(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contractRet");
        this.contractRet = str;
    }

    public static /* synthetic */ FindTransctionRet copy$default(FindTransctionRet findTransctionRet, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = findTransctionRet.contractRet;
        }
        return findTransctionRet.copy(str);
    }

    public final String component1() {
        return this.contractRet;
    }

    public final FindTransctionRet copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contractRet");
        return new FindTransctionRet(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransctionRet) {
                if (Intrinsics.areEqual(this.contractRet, ((FindTransctionRet) obj).contractRet)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getContractRet() {
        return this.contractRet;
    }

    public int hashCode() {
        String str = this.contractRet;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransctionRet(contractRet=");
        stringBuilder.append(this.contractRet);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
