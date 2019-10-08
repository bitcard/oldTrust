package com.wallet.crypto.trustapp.service.rpc.icon.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IconResponse.kt */
public final class IconResponse<T> {
    private final IconFailure error;
    private final T result;

    public IconResponse(T t, IconFailure iconFailure) {
        this.result = t;
        this.error = iconFailure;
    }

    public static /* synthetic */ IconResponse copy$default(IconResponse iconResponse, Object obj, IconFailure iconFailure, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = iconResponse.result;
        }
        if ((i & 2) != 0) {
            iconFailure = iconResponse.error;
        }
        return iconResponse.copy(obj, iconFailure);
    }

    public final T component1() {
        return this.result;
    }

    public final IconFailure component2() {
        return this.error;
    }

    public final IconResponse<T> copy(T t, IconFailure iconFailure) {
        return new IconResponse(t, iconFailure);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IconResponse) {
                IconResponse iconResponse = (IconResponse) obj;
                if (Intrinsics.areEqual(this.result, iconResponse.result) && Intrinsics.areEqual(this.error, iconResponse.error)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final IconFailure getError() {
        return this.error;
    }

    public final T getResult() {
        return this.result;
    }

    public int hashCode() {
        Object obj = this.result;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        IconFailure iconFailure = this.error;
        if (iconFailure != null) {
            i = iconFailure.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IconResponse(result=");
        stringBuilder.append(this.result);
        stringBuilder.append(", error=");
        stringBuilder.append(this.error);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
