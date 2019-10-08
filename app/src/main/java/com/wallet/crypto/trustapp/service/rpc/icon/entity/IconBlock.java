package com.wallet.crypto.trustapp.service.rpc.icon.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: IconBlock.kt */
public final class IconBlock {
    private final String block_hash;
    private final long height;
    private final String merkle_tree_root_hash;
    private final String peer_id;
    private final String prev_block_hash;
    private final String signature;
    private final String time_stamp;
    private final String version;

    public IconBlock(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7) {
        Intrinsics.checkParameterIsNotNull(str, "version");
        Intrinsics.checkParameterIsNotNull(str2, "prev_block_hash");
        Intrinsics.checkParameterIsNotNull(str3, "merkle_tree_root_hash");
        Intrinsics.checkParameterIsNotNull(str4, "time_stamp");
        Intrinsics.checkParameterIsNotNull(str5, "block_hash");
        Intrinsics.checkParameterIsNotNull(str6, "peer_id");
        Intrinsics.checkParameterIsNotNull(str7, "signature");
        this.version = str;
        this.prev_block_hash = str2;
        this.merkle_tree_root_hash = str3;
        this.time_stamp = str4;
        this.block_hash = str5;
        this.height = j;
        this.peer_id = str6;
        this.signature = str7;
    }

    public static /* synthetic */ IconBlock copy$default(IconBlock iconBlock, String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, int i, Object obj) {
        IconBlock iconBlock2 = iconBlock;
        int i2 = i;
        return iconBlock.copy((i2 & 1) != 0 ? iconBlock2.version : str, (i2 & 2) != 0 ? iconBlock2.prev_block_hash : str2, (i2 & 4) != 0 ? iconBlock2.merkle_tree_root_hash : str3, (i2 & 8) != 0 ? iconBlock2.time_stamp : str4, (i2 & 16) != 0 ? iconBlock2.block_hash : str5, (i2 & 32) != 0 ? iconBlock2.height : j, (i2 & 64) != 0 ? iconBlock2.peer_id : str6, (i2 & 128) != 0 ? iconBlock2.signature : str7);
    }

    public final String component1() {
        return this.version;
    }

    public final String component2() {
        return this.prev_block_hash;
    }

    public final String component3() {
        return this.merkle_tree_root_hash;
    }

    public final String component4() {
        return this.time_stamp;
    }

    public final String component5() {
        return this.block_hash;
    }

    public final long component6() {
        return this.height;
    }

    public final String component7() {
        return this.peer_id;
    }

    public final String component8() {
        return this.signature;
    }

    public final IconBlock copy(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7) {
        String str8 = str;
        Intrinsics.checkParameterIsNotNull(str, "version");
        String str9 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "prev_block_hash");
        String str10 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "merkle_tree_root_hash");
        String str11 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "time_stamp");
        String str12 = str5;
        Intrinsics.checkParameterIsNotNull(str12, "block_hash");
        String str13 = str6;
        Intrinsics.checkParameterIsNotNull(str13, "peer_id");
        String str14 = str7;
        Intrinsics.checkParameterIsNotNull(str14, "signature");
        return new IconBlock(str8, str9, str10, str11, str12, j, str13, str14);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IconBlock) {
                IconBlock iconBlock = (IconBlock) obj;
                if (Intrinsics.areEqual(this.version, iconBlock.version) && Intrinsics.areEqual(this.prev_block_hash, iconBlock.prev_block_hash) && Intrinsics.areEqual(this.merkle_tree_root_hash, iconBlock.merkle_tree_root_hash) && Intrinsics.areEqual(this.time_stamp, iconBlock.time_stamp) && Intrinsics.areEqual(this.block_hash, iconBlock.block_hash)) {
                    if ((this.height == iconBlock.height) && Intrinsics.areEqual(this.peer_id, iconBlock.peer_id) && Intrinsics.areEqual(this.signature, iconBlock.signature)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getBlock_hash() {
        return this.block_hash;
    }

    public final long getHeight() {
        return this.height;
    }

    public final String getMerkle_tree_root_hash() {
        return this.merkle_tree_root_hash;
    }

    public final String getPeer_id() {
        return this.peer_id;
    }

    public final String getPrev_block_hash() {
        return this.prev_block_hash;
    }

    public final String getSignature() {
        return this.signature;
    }

    public final String getTime_stamp() {
        return this.time_stamp;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.version;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.prev_block_hash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.merkle_tree_root_hash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.time_stamp;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.block_hash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j = this.height;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        str2 = this.peer_id;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.signature;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IconBlock(version=");
        stringBuilder.append(this.version);
        stringBuilder.append(", prev_block_hash=");
        stringBuilder.append(this.prev_block_hash);
        stringBuilder.append(", merkle_tree_root_hash=");
        stringBuilder.append(this.merkle_tree_root_hash);
        stringBuilder.append(", time_stamp=");
        stringBuilder.append(this.time_stamp);
        stringBuilder.append(", block_hash=");
        stringBuilder.append(this.block_hash);
        stringBuilder.append(", height=");
        stringBuilder.append(this.height);
        stringBuilder.append(", peer_id=");
        stringBuilder.append(this.peer_id);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.signature);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
