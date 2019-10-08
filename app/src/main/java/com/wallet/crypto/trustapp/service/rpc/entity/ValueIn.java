package com.wallet.crypto.trustapp.service.rpc.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransactionResponse.kt */
public final class ValueIn {
    private final String[] addresses;
    /* renamed from: n */
    private final int f16718n;
    private final long sequence;
    private final String txid;
    private final String value;
    private final int vout;

    public ValueIn(String str, int i, long j, int i2, String[] strArr, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "txid");
        Intrinsics.checkParameterIsNotNull(strArr, "addresses");
        Intrinsics.checkParameterIsNotNull(str2, "value");
        this.txid = str;
        this.vout = i;
        this.sequence = j;
        this.f16718n = i2;
        this.addresses = strArr;
        this.value = str2;
    }

    public static /* synthetic */ ValueIn copy$default(ValueIn valueIn, String str, int i, long j, int i2, String[] strArr, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = valueIn.txid;
        }
        if ((i3 & 2) != 0) {
            i = valueIn.vout;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            j = valueIn.sequence;
        }
        long j2 = j;
        if ((i3 & 8) != 0) {
            i2 = valueIn.f16718n;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            strArr = valueIn.addresses;
        }
        String[] strArr2 = strArr;
        if ((i3 & 32) != 0) {
            str2 = valueIn.value;
        }
        return valueIn.copy(str, i4, j2, i5, strArr2, str2);
    }

    public final String component1() {
        return this.txid;
    }

    public final int component2() {
        return this.vout;
    }

    public final long component3() {
        return this.sequence;
    }

    public final int component4() {
        return this.f16718n;
    }

    public final String[] component5() {
        return this.addresses;
    }

    public final String component6() {
        return this.value;
    }

    public final ValueIn copy(String str, int i, long j, int i2, String[] strArr, String str2) {
        String str3 = str;
        Intrinsics.checkParameterIsNotNull(str, "txid");
        String[] strArr2 = strArr;
        Intrinsics.checkParameterIsNotNull(strArr, "addresses");
        String str4 = str2;
        Intrinsics.checkParameterIsNotNull(str4, "value");
        return new ValueIn(str3, i, j, i2, strArr2, str4);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ValueIn) {
                ValueIn valueIn = (ValueIn) obj;
                if (Intrinsics.areEqual(this.txid, valueIn.txid)) {
                    if (this.vout == valueIn.vout) {
                        if (this.sequence == valueIn.sequence) {
                            if ((this.f16718n == valueIn.f16718n) && Intrinsics.areEqual(this.addresses, valueIn.addresses) && Intrinsics.areEqual(this.value, valueIn.value)) {
                                return true;
                            }
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
        return this.f16718n;
    }

    public final long getSequence() {
        return this.sequence;
    }

    public final String getTxid() {
        return this.txid;
    }

    public final String getValue() {
        return this.value;
    }

    public final int getVout() {
        return this.vout;
    }

    public int hashCode() {
        String str = this.txid;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.vout) * 31;
        long j = this.sequence;
        hashCode = (((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.f16718n) * 31;
        String[] strArr = this.addresses;
        hashCode = (hashCode + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String str2 = this.value;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ValueIn(txid=");
        stringBuilder.append(this.txid);
        stringBuilder.append(", vout=");
        stringBuilder.append(this.vout);
        stringBuilder.append(", sequence=");
        stringBuilder.append(this.sequence);
        stringBuilder.append(", n=");
        stringBuilder.append(this.f16718n);
        stringBuilder.append(", addresses=");
        stringBuilder.append(Arrays.toString(this.addresses));
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
