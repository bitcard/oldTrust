package com.wallet.crypto.trustapp.service.rpc.iotex.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IotexModels.kt */
public final class IoTexAction {
    private final String blkHeight;

    public IoTexAction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "blkHeight");
        this.blkHeight = str;
    }

    public static /* synthetic */ IoTexAction copy$default(IoTexAction ioTexAction, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ioTexAction.blkHeight;
        }
        return ioTexAction.copy(str);
    }

    public final String component1() {
        return this.blkHeight;
    }

    public final IoTexAction copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "blkHeight");
        return new IoTexAction(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IoTexAction) {
                if (Intrinsics.areEqual(this.blkHeight, ((IoTexAction) obj).blkHeight)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getBlkHeight() {
        return this.blkHeight;
    }

    public int hashCode() {
        String str = this.blkHeight;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IoTexAction(blkHeight=");
        stringBuilder.append(this.blkHeight);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
