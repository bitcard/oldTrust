package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: AddressRequest.kt */
public final class AddressRequest {
    private final String address;

    public AddressRequest(String str) {
        Intrinsics.checkParameterIsNotNull(str, Address.TYPE_NAME);
        this.address = str;
    }

    public static /* synthetic */ AddressRequest copy$default(AddressRequest addressRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addressRequest.address;
        }
        return addressRequest.copy(str);
    }

    public final String component1() {
        return this.address;
    }

    public final AddressRequest copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, Address.TYPE_NAME);
        return new AddressRequest(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof AddressRequest) {
                if (Intrinsics.areEqual(this.address, ((AddressRequest) obj).address)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getAddress() {
        return this.address;
    }

    public int hashCode() {
        String str = this.address;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AddressRequest(address=");
        stringBuilder.append(this.address);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
