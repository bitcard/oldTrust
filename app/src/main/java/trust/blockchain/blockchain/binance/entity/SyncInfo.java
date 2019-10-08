package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class SyncInfo {
    private final String latest_block_hash;
    private final long latest_block_height;
    private final String latest_block_time;

    public SyncInfo(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "latest_block_time");
        Intrinsics.checkParameterIsNotNull(str2, "latest_block_hash");
        this.latest_block_height = j;
        this.latest_block_time = str;
        this.latest_block_hash = str2;
    }

    public static /* synthetic */ SyncInfo copy$default(SyncInfo syncInfo, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = syncInfo.latest_block_height;
        }
        if ((i & 2) != 0) {
            str = syncInfo.latest_block_time;
        }
        if ((i & 4) != 0) {
            str2 = syncInfo.latest_block_hash;
        }
        return syncInfo.copy(j, str, str2);
    }

    public final long component1() {
        return this.latest_block_height;
    }

    public final String component2() {
        return this.latest_block_time;
    }

    public final String component3() {
        return this.latest_block_hash;
    }

    public final SyncInfo copy(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "latest_block_time");
        Intrinsics.checkParameterIsNotNull(str2, "latest_block_hash");
        return new SyncInfo(j, str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SyncInfo) {
                SyncInfo syncInfo = (SyncInfo) obj;
                if ((this.latest_block_height == syncInfo.latest_block_height) && Intrinsics.areEqual(this.latest_block_time, syncInfo.latest_block_time) && Intrinsics.areEqual(this.latest_block_hash, syncInfo.latest_block_hash)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getLatest_block_hash() {
        return this.latest_block_hash;
    }

    public final long getLatest_block_height() {
        return this.latest_block_height;
    }

    public final String getLatest_block_time() {
        return this.latest_block_time;
    }

    public int hashCode() {
        long j = this.latest_block_height;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.latest_block_time;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.latest_block_hash;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SyncInfo(latest_block_height=");
        stringBuilder.append(this.latest_block_height);
        stringBuilder.append(", latest_block_time=");
        stringBuilder.append(this.latest_block_time);
        stringBuilder.append(", latest_block_hash=");
        stringBuilder.append(this.latest_block_hash);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
