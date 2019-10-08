package com.wallet.crypto.trustapp.service.rpc.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransactionResponse.kt */
public final class ValueOut {
    private final String[] addresses;
    /* renamed from: n */
    private final int f16719n;
    private final boolean spent;
    private final String value;

    public ValueOut(String str, int i, String[] strArr, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        Intrinsics.checkParameterIsNotNull(strArr, "addresses");
        this.value = str;
        this.f16719n = i;
        this.addresses = strArr;
        this.spent = z;
    }

    public static /* synthetic */ ValueOut copy$default(ValueOut valueOut, String str, int i, String[] strArr, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = valueOut.value;
        }
        if ((i2 & 2) != 0) {
            i = valueOut.f16719n;
        }
        if ((i2 & 4) != 0) {
            strArr = valueOut.addresses;
        }
        if ((i2 & 8) != 0) {
            z = valueOut.spent;
        }
        return valueOut.copy(str, i, strArr, z);
    }

    public final String component1() {
        return this.value;
    }

    public final int component2() {
        return this.f16719n;
    }

    public final String[] component3() {
        return this.addresses;
    }

    public final boolean component4() {
        return this.spent;
    }

    public final ValueOut copy(String str, int i, String[] strArr, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        Intrinsics.checkParameterIsNotNull(strArr, "addresses");
        return new ValueOut(str, i, strArr, z);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ValueOut) {
                ValueOut valueOut = (ValueOut) obj;
                if (Intrinsics.areEqual(this.value, valueOut.value)) {
                    if ((this.f16719n == valueOut.f16719n) && Intrinsics.areEqual(this.addresses, valueOut.addresses)) {
                        if (this.spent == valueOut.spent) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String[] getAddresses() {
        return this.addresses;
    }

    public final int getN() {
        return this.f16719n;
    }

    public final boolean getSpent() {
        return this.spent;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.value;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.f16719n) * 31;
        String[] strArr = this.addresses;
        if (strArr != null) {
            i = Arrays.hashCode(strArr);
        }
        hashCode = (hashCode + i) * 31;
        i = 1;
        if (this.spent != false) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ValueOut(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", n=");
        stringBuilder.append(this.f16719n);
        stringBuilder.append(", addresses=");
        stringBuilder.append(Arrays.toString(this.addresses));
        stringBuilder.append(", spent=");
        stringBuilder.append(this.spent);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
