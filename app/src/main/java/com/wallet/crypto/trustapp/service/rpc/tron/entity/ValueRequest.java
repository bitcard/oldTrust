package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ValueRequest.kt */
public final class ValueRequest {
    private final String value;

    public ValueRequest(String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        this.value = str;
    }

    public static /* synthetic */ ValueRequest copy$default(ValueRequest valueRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = valueRequest.value;
        }
        return valueRequest.copy(str);
    }

    public final String component1() {
        return this.value;
    }

    public final ValueRequest copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new ValueRequest(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ValueRequest) {
                if (Intrinsics.areEqual(this.value, ((ValueRequest) obj).value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.value;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ValueRequest(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
