package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransctionRawData {
    private final FindTransctionContract[] contract;
    private final long expiration;

    public FindTransctionRawData(FindTransctionContract[] findTransctionContractArr, long j) {
        Intrinsics.checkParameterIsNotNull(findTransctionContractArr, "contract");
        this.contract = findTransctionContractArr;
        this.expiration = j;
    }

    public static /* synthetic */ FindTransctionRawData copy$default(FindTransctionRawData findTransctionRawData, FindTransctionContract[] findTransctionContractArr, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            findTransctionContractArr = findTransctionRawData.contract;
        }
        if ((i & 2) != 0) {
            j = findTransctionRawData.expiration;
        }
        return findTransctionRawData.copy(findTransctionContractArr, j);
    }

    public final FindTransctionContract[] component1() {
        return this.contract;
    }

    public final long component2() {
        return this.expiration;
    }

    public final FindTransctionRawData copy(FindTransctionContract[] findTransctionContractArr, long j) {
        Intrinsics.checkParameterIsNotNull(findTransctionContractArr, "contract");
        return new FindTransctionRawData(findTransctionContractArr, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransctionRawData) {
                FindTransctionRawData findTransctionRawData = (FindTransctionRawData) obj;
                if (Intrinsics.areEqual(this.contract, findTransctionRawData.contract)) {
                    if (this.expiration == findTransctionRawData.expiration) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final FindTransctionContract[] getContract() {
        return this.contract;
    }

    public final long getExpiration() {
        return this.expiration;
    }

    public int hashCode() {
        FindTransctionContract[] findTransctionContractArr = this.contract;
        int hashCode = (findTransctionContractArr != null ? Arrays.hashCode(findTransctionContractArr) : 0) * 31;
        long j = this.expiration;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransctionRawData(contract=");
        stringBuilder.append(Arrays.toString(this.contract));
        stringBuilder.append(", expiration=");
        stringBuilder.append(this.expiration);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
