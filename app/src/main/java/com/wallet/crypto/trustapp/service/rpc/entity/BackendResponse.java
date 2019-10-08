package com.wallet.crypto.trustapp.service.rpc.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackendResponse.kt */
public final class BackendResponse {
    private final String bestblockhash;
    private final long blocks;
    private final long headers;
    private final String protocolversion;
    private final String version;

    public BackendResponse(long j, long j2, String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "bestblockhash");
        Intrinsics.checkParameterIsNotNull(str2, "version");
        Intrinsics.checkParameterIsNotNull(str3, "protocolversion");
        this.blocks = j;
        this.headers = j2;
        this.bestblockhash = str;
        this.version = str2;
        this.protocolversion = str3;
    }

    public static /* synthetic */ BackendResponse copy$default(BackendResponse backendResponse, long j, long j2, String str, String str2, String str3, int i, Object obj) {
        BackendResponse backendResponse2 = backendResponse;
        return backendResponse.copy((i & 1) != 0 ? backendResponse2.blocks : j, (i & 2) != 0 ? backendResponse2.headers : j2, (i & 4) != 0 ? backendResponse2.bestblockhash : str, (i & 8) != 0 ? backendResponse2.version : str2, (i & 16) != 0 ? backendResponse2.protocolversion : str3);
    }

    public final long component1() {
        return this.blocks;
    }

    public final long component2() {
        return this.headers;
    }

    public final String component3() {
        return this.bestblockhash;
    }

    public final String component4() {
        return this.version;
    }

    public final String component5() {
        return this.protocolversion;
    }

    public final BackendResponse copy(long j, long j2, String str, String str2, String str3) {
        String str4 = str;
        Intrinsics.checkParameterIsNotNull(str, "bestblockhash");
        String str5 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "version");
        String str6 = str3;
        Intrinsics.checkParameterIsNotNull(str6, "protocolversion");
        return new BackendResponse(j, j2, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BackendResponse) {
                BackendResponse backendResponse = (BackendResponse) obj;
                if (this.blocks == backendResponse.blocks) {
                    if ((this.headers == backendResponse.headers) && Intrinsics.areEqual(this.bestblockhash, backendResponse.bestblockhash) && Intrinsics.areEqual(this.version, backendResponse.version) && Intrinsics.areEqual(this.protocolversion, backendResponse.protocolversion)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getBestblockhash() {
        return this.bestblockhash;
    }

    public final long getBlocks() {
        return this.blocks;
    }

    public final long getHeaders() {
        return this.headers;
    }

    public final String getProtocolversion() {
        return this.protocolversion;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        long j = this.blocks;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.headers;
        i = (i + ((int) ((j2 >>> 32) ^ j2))) * 31;
        String str = this.bestblockhash;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.version;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.protocolversion;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BackendResponse(blocks=");
        stringBuilder.append(this.blocks);
        stringBuilder.append(", headers=");
        stringBuilder.append(this.headers);
        stringBuilder.append(", bestblockhash=");
        stringBuilder.append(this.bestblockhash);
        stringBuilder.append(", version=");
        stringBuilder.append(this.version);
        stringBuilder.append(", protocolversion=");
        stringBuilder.append(this.protocolversion);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
