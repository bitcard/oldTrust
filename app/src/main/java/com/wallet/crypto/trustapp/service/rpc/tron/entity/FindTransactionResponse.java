package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransactionResponse {
    private final FindTransctionRawData raw_data;
    private final FindTransctionRet[] ret;

    public FindTransactionResponse(FindTransctionRet[] findTransctionRetArr, FindTransctionRawData findTransctionRawData) {
        Intrinsics.checkParameterIsNotNull(findTransctionRetArr, "ret");
        Intrinsics.checkParameterIsNotNull(findTransctionRawData, "raw_data");
        this.ret = findTransctionRetArr;
        this.raw_data = findTransctionRawData;
    }

    public static /* synthetic */ FindTransactionResponse copy$default(FindTransactionResponse findTransactionResponse, FindTransctionRet[] findTransctionRetArr, FindTransctionRawData findTransctionRawData, int i, Object obj) {
        if ((i & 1) != 0) {
            findTransctionRetArr = findTransactionResponse.ret;
        }
        if ((i & 2) != 0) {
            findTransctionRawData = findTransactionResponse.raw_data;
        }
        return findTransactionResponse.copy(findTransctionRetArr, findTransctionRawData);
    }

    public final FindTransctionRet[] component1() {
        return this.ret;
    }

    public final FindTransctionRawData component2() {
        return this.raw_data;
    }

    public final FindTransactionResponse copy(FindTransctionRet[] findTransctionRetArr, FindTransctionRawData findTransctionRawData) {
        Intrinsics.checkParameterIsNotNull(findTransctionRetArr, "ret");
        Intrinsics.checkParameterIsNotNull(findTransctionRawData, "raw_data");
        return new FindTransactionResponse(findTransctionRetArr, findTransctionRawData);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransactionResponse) {
                FindTransactionResponse findTransactionResponse = (FindTransactionResponse) obj;
                if (Intrinsics.areEqual(this.ret, findTransactionResponse.ret) && Intrinsics.areEqual(this.raw_data, findTransactionResponse.raw_data)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final FindTransctionRawData getRaw_data() {
        return this.raw_data;
    }

    public final FindTransctionRet[] getRet() {
        return this.ret;
    }

    public int hashCode() {
        FindTransctionRet[] findTransctionRetArr = this.ret;
        int i = 0;
        int hashCode = (findTransctionRetArr != null ? Arrays.hashCode(findTransctionRetArr) : 0) * 31;
        FindTransctionRawData findTransctionRawData = this.raw_data;
        if (findTransctionRawData != null) {
            i = findTransctionRawData.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransactionResponse(ret=");
        stringBuilder.append(Arrays.toString(this.ret));
        stringBuilder.append(", raw_data=");
        stringBuilder.append(this.raw_data);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
