package com.wallet.crypto.trustapp.service.trustapi.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappRequestBody.kt */
public final class DappRequestBody {
    private final int[] networks;

    public DappRequestBody(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "networks");
        this.networks = iArr;
    }

    public static /* synthetic */ DappRequestBody copy$default(DappRequestBody dappRequestBody, int[] iArr, int i, Object obj) {
        if ((i & 1) != 0) {
            iArr = dappRequestBody.networks;
        }
        return dappRequestBody.copy(iArr);
    }

    public final int[] component1() {
        return this.networks;
    }

    public final DappRequestBody copy(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "networks");
        return new DappRequestBody(iArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DappRequestBody) {
                if (Intrinsics.areEqual(this.networks, ((DappRequestBody) obj).networks)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int[] getNetworks() {
        return this.networks;
    }

    public int hashCode() {
        int[] iArr = this.networks;
        return iArr != null ? Arrays.hashCode(iArr) : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DappRequestBody(networks=");
        stringBuilder.append(Arrays.toString(this.networks));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
