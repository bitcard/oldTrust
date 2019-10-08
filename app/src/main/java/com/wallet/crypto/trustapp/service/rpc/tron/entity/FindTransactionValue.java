package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindTransactionResponse.kt */
public final class FindTransactionValue {
    private final long amount;
    private final String owner_address;
    private final String to_address;

    public FindTransactionValue(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "owner_address");
        Intrinsics.checkParameterIsNotNull(str2, "to_address");
        this.amount = j;
        this.owner_address = str;
        this.to_address = str2;
    }

    public static /* synthetic */ FindTransactionValue copy$default(FindTransactionValue findTransactionValue, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = findTransactionValue.amount;
        }
        if ((i & 2) != 0) {
            str = findTransactionValue.owner_address;
        }
        if ((i & 4) != 0) {
            str2 = findTransactionValue.to_address;
        }
        return findTransactionValue.copy(j, str, str2);
    }

    public final long component1() {
        return this.amount;
    }

    public final String component2() {
        return this.owner_address;
    }

    public final String component3() {
        return this.to_address;
    }

    public final FindTransactionValue copy(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "owner_address");
        Intrinsics.checkParameterIsNotNull(str2, "to_address");
        return new FindTransactionValue(j, str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FindTransactionValue) {
                FindTransactionValue findTransactionValue = (FindTransactionValue) obj;
                if ((this.amount == findTransactionValue.amount) && Intrinsics.areEqual(this.owner_address, findTransactionValue.owner_address) && Intrinsics.areEqual(this.to_address, findTransactionValue.to_address)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getAmount() {
        return this.amount;
    }

    public final String getOwner_address() {
        return this.owner_address;
    }

    public final String getTo_address() {
        return this.to_address;
    }

    public int hashCode() {
        long j = this.amount;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.owner_address;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.to_address;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FindTransactionValue(amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(", owner_address=");
        stringBuilder.append(this.owner_address);
        stringBuilder.append(", to_address=");
        stringBuilder.append(this.to_address);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
