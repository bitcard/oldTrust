package com.wallet.crypto.trustapp.service.rpc.icon.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IconFailure.kt */
public final class IconFailure {
    private final String message;

    public IconFailure(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.message = str;
    }

    public static /* synthetic */ IconFailure copy$default(IconFailure iconFailure, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = iconFailure.message;
        }
        return iconFailure.copy(str);
    }

    public final String component1() {
        return this.message;
    }

    public final IconFailure copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        return new IconFailure(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IconFailure) {
                if (Intrinsics.areEqual(this.message, ((IconFailure) obj).message)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String str = this.message;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IconFailure(message=");
        stringBuilder.append(this.message);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
