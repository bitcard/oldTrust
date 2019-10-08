package com.wallet.crypto.trustapp.service.rpc.iotex.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IotexModels.kt */
public final class IotexAccountMeta {
    private final String balance;
    private final String pendingNonce;

    public IotexAccountMeta(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        Intrinsics.checkParameterIsNotNull(str2, "pendingNonce");
        this.balance = str;
        this.pendingNonce = str2;
    }

    public static /* synthetic */ IotexAccountMeta copy$default(IotexAccountMeta iotexAccountMeta, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = iotexAccountMeta.balance;
        }
        if ((i & 2) != 0) {
            str2 = iotexAccountMeta.pendingNonce;
        }
        return iotexAccountMeta.copy(str, str2);
    }

    public final String component1() {
        return this.balance;
    }

    public final String component2() {
        return this.pendingNonce;
    }

    public final IotexAccountMeta copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        Intrinsics.checkParameterIsNotNull(str2, "pendingNonce");
        return new IotexAccountMeta(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IotexAccountMeta) {
                IotexAccountMeta iotexAccountMeta = (IotexAccountMeta) obj;
                if (Intrinsics.areEqual(this.balance, iotexAccountMeta.balance) && Intrinsics.areEqual(this.pendingNonce, iotexAccountMeta.pendingNonce)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getBalance() {
        return this.balance;
    }

    public final String getPendingNonce() {
        return this.pendingNonce;
    }

    public int hashCode() {
        String str = this.balance;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.pendingNonce;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IotexAccountMeta(balance=");
        stringBuilder.append(this.balance);
        stringBuilder.append(", pendingNonce=");
        stringBuilder.append(this.pendingNonce);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
