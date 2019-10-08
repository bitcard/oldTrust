package com.wallet.crypto.trustapp.ui.dex.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: DexModels.kt */
public final class TradeOrderStatus {
    /* renamed from: a */
    private final String status;

    public TradeOrderStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TradeOrderStatus) {
                if (Intrinsics.areEqual(this.status, ((TradeOrderStatus) obj).status)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TradeOrderStatus(status=");
        stringBuilder.append(this.status);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
