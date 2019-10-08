package com.wallet.crypto.trustapp.service.rpc.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockbookStateResponse.kt */
public final class BlockbookStateResponse {
    private final long bestHeight;
    private final String lastBlockTime;

    public BlockbookStateResponse(long j, String str) {
        Intrinsics.checkParameterIsNotNull(str, "lastBlockTime");
        this.bestHeight = j;
        this.lastBlockTime = str;
    }

    public static /* synthetic */ BlockbookStateResponse copy$default(BlockbookStateResponse blockbookStateResponse, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = blockbookStateResponse.bestHeight;
        }
        if ((i & 2) != 0) {
            str = blockbookStateResponse.lastBlockTime;
        }
        return blockbookStateResponse.copy(j, str);
    }

    public final long component1() {
        return this.bestHeight;
    }

    public final String component2() {
        return this.lastBlockTime;
    }

    public final BlockbookStateResponse copy(long j, String str) {
        Intrinsics.checkParameterIsNotNull(str, "lastBlockTime");
        return new BlockbookStateResponse(j, str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BlockbookStateResponse) {
                BlockbookStateResponse blockbookStateResponse = (BlockbookStateResponse) obj;
                if ((this.bestHeight == blockbookStateResponse.bestHeight) && Intrinsics.areEqual(this.lastBlockTime, blockbookStateResponse.lastBlockTime)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getBestHeight() {
        return this.bestHeight;
    }

    public final String getLastBlockTime() {
        return this.lastBlockTime;
    }

    public int hashCode() {
        long j = this.bestHeight;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.lastBlockTime;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BlockbookStateResponse(bestHeight=");
        stringBuilder.append(this.bestHeight);
        stringBuilder.append(", lastBlockTime=");
        stringBuilder.append(this.lastBlockTime);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
