package com.wallet.crypto.trustapp.service.rpc.iotex.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IotexModels.kt */
public final class IotexAccount {
    private final IotexAccountMeta accountMeta;

    public IotexAccount(IotexAccountMeta iotexAccountMeta) {
        Intrinsics.checkParameterIsNotNull(iotexAccountMeta, "accountMeta");
        this.accountMeta = iotexAccountMeta;
    }

    public static /* synthetic */ IotexAccount copy$default(IotexAccount iotexAccount, IotexAccountMeta iotexAccountMeta, int i, Object obj) {
        if ((i & 1) != 0) {
            iotexAccountMeta = iotexAccount.accountMeta;
        }
        return iotexAccount.copy(iotexAccountMeta);
    }

    public final IotexAccountMeta component1() {
        return this.accountMeta;
    }

    public final IotexAccount copy(IotexAccountMeta iotexAccountMeta) {
        Intrinsics.checkParameterIsNotNull(iotexAccountMeta, "accountMeta");
        return new IotexAccount(iotexAccountMeta);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IotexAccount) {
                if (Intrinsics.areEqual(this.accountMeta, ((IotexAccount) obj).accountMeta)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final IotexAccountMeta getAccountMeta() {
        return this.accountMeta;
    }

    public int hashCode() {
        IotexAccountMeta iotexAccountMeta = this.accountMeta;
        return iotexAccountMeta != null ? iotexAccountMeta.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IotexAccount(accountMeta=");
        stringBuilder.append(this.accountMeta);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
