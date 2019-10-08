package com.wallet.crypto.trustapp.service.rpc.iotex.entity;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IotexModels.kt */
public final class IoTexTransaction {
    private final List<IoTexAction> actionInfo;

    public IoTexTransaction(List<IoTexAction> list) {
        Intrinsics.checkParameterIsNotNull(list, "actionInfo");
        this.actionInfo = list;
    }

    public static /* synthetic */ IoTexTransaction copy$default(IoTexTransaction ioTexTransaction, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = ioTexTransaction.actionInfo;
        }
        return ioTexTransaction.copy(list);
    }

    public final List<IoTexAction> component1() {
        return this.actionInfo;
    }

    public final IoTexTransaction copy(List<IoTexAction> list) {
        Intrinsics.checkParameterIsNotNull(list, "actionInfo");
        return new IoTexTransaction(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IoTexTransaction) {
                if (Intrinsics.areEqual(this.actionInfo, ((IoTexTransaction) obj).actionInfo)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final List<IoTexAction> getActionInfo() {
        return this.actionInfo;
    }

    public int hashCode() {
        List list = this.actionInfo;
        return list != null ? list.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IoTexTransaction(actionInfo=");
        stringBuilder.append(this.actionInfo);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
