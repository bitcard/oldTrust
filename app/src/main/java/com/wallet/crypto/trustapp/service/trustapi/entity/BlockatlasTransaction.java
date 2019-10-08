package com.wallet.crypto.trustapp.service.trustapi.entity;

import com.google.gson.JsonObject;

import kotlin.jvm.internal.Intrinsics;

public final class BlockatlasTransaction {
    private final long block;
    private final int coin;
    private final long date;
    private final Long destinationTag;
    private final String error;
    private final String fee;
    private final String from;
    private final String id;
    private final String memo;
    private final JsonObject metadata;
    private final long sequence;
    private final String status;
    private final String to;
    private final String type;
    
    public BlockatlasTransaction(final String id, final String error, final int coin, final String from,
                                 final String to, final String fee, final long date, final String type,
                                 final String memo, final Long destinationTag, final long block,
                                 final String status, final long sequence, final JsonObject metadata) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        
        this.id = id;
        this.error = error;
        this.coin = coin;
        this.from = from;
        this.to = to;
        this.fee = fee;
        this.date = date;
        this.type = type;
        this.memo = memo;
        this.destinationTag = destinationTag;
        this.block = block;
        this.status = status;
        this.sequence = sequence;
        this.metadata = metadata;
    }
    
    public static /* synthetic */ BlockatlasTransaction copy$default(final BlockatlasTransaction blockatlasTransaction, String id, String error, int coin, String from, String to, String fee, long date, String type, String memo, Long destinationTag, long block, String status, long sequence, JsonObject metadata, final int n, final Object o) {
        if ((n & 0x1) != 0x0) {
            id = blockatlasTransaction.id;
        }
        if ((n & 0x2) != 0x0) {
            error = blockatlasTransaction.error;
        }
        if ((n & 0x4) != 0x0) {
            coin = blockatlasTransaction.coin;
        }
        if ((n & 0x8) != 0x0) {
            from = blockatlasTransaction.from;
        }
        if ((n & 0x10) != 0x0) {
            to = blockatlasTransaction.to;
        }
        if ((n & 0x20) != 0x0) {
            fee = blockatlasTransaction.fee;
        }
        if ((n & 0x40) != 0x0) {
            date = blockatlasTransaction.date;
        }
        if ((n & 0x80) != 0x0) {
            type = blockatlasTransaction.type;
        }
        if ((n & 0x100) != 0x0) {
            memo = blockatlasTransaction.memo;
        }
        if ((n & 0x200) != 0x0) {
            destinationTag = blockatlasTransaction.destinationTag;
        }
        if ((n & 0x400) != 0x0) {
            block = blockatlasTransaction.block;
        }
        if ((n & 0x800) != 0x0) {
            status = blockatlasTransaction.status;
        }
        if ((n & 0x1000) != 0x0) {
            sequence = blockatlasTransaction.sequence;
        }
        if ((n & 0x2000) != 0x0) {
            metadata = blockatlasTransaction.metadata;
        }
        return blockatlasTransaction.copy(id, error, coin, from, to, fee, date, type, memo, destinationTag, block, status, sequence, metadata);
    }
    
    public final String component1() {
        return this.id;
    }
    
    public final Long component10() {
        return this.destinationTag;
    }
    
    public final long component11() {
        return this.block;
    }
    
    public final String component12() {
        return this.status;
    }
    
    public final long component13() {
        return this.sequence;
    }
    
    public final JsonObject component14() {
        return this.metadata;
    }
    
    public final String component2() {
        return this.error;
    }
    
    public final int component3() {
        return this.coin;
    }
    
    public final String component4() {
        return this.from;
    }
    
    public final String component5() {
        return this.to;
    }
    
    public final String component6() {
        return this.fee;
    }
    
    public final long component7() {
        return this.date;
    }
    
    public final String component8() {
        return this.type;
    }
    
    public final String component9() {
        return this.memo;
    }
    
    public final BlockatlasTransaction copy(final String s, final String s2, final int n, final String s3, final String s4, final String s5, final long n2, final String s6, final String s7, final Long n3, final long n4, final String s8, final long n5, final JsonObject jsonObject) {
        Intrinsics.checkParameterIsNotNull(s, "id");
        Intrinsics.checkParameterIsNotNull(s3, "from");
        Intrinsics.checkParameterIsNotNull(s6, "type");
        Intrinsics.checkParameterIsNotNull(s8, "status");
        Intrinsics.checkParameterIsNotNull(jsonObject, "metadata");
        return new BlockatlasTransaction(s, s2, n, s3, s4, s5, n2, s6, s7, n3, n4, s8, n5, jsonObject);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof BlockatlasTransaction) {
                final BlockatlasTransaction blockatlasTransaction = (BlockatlasTransaction) o;
                if (Intrinsics.areEqual(this.id, blockatlasTransaction.id) && Intrinsics.areEqual(this.error, blockatlasTransaction.error) && this.coin == blockatlasTransaction.coin && Intrinsics.areEqual(this.from, blockatlasTransaction.from) && Intrinsics.areEqual(this.to, blockatlasTransaction.to) && Intrinsics.areEqual(this.fee, blockatlasTransaction.fee) && this.date == blockatlasTransaction.date && Intrinsics.areEqual(this.type, blockatlasTransaction.type) && Intrinsics.areEqual(this.memo, blockatlasTransaction.memo) && Intrinsics.areEqual(this.destinationTag, blockatlasTransaction.destinationTag) && this.block == blockatlasTransaction.block && Intrinsics.areEqual(this.status, blockatlasTransaction.status) && this.sequence == blockatlasTransaction.sequence && Intrinsics.areEqual(this.metadata, blockatlasTransaction.metadata)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public final long getBlock() {
        return this.block;
    }
    
    public final int getCoin() {
        return this.coin;
    }
    
    public final long getDate() {
        return this.date;
    }
    
    public final Long getDestinationTag() {
        return this.destinationTag;
    }
    
    public final String getError() {
        return this.error;
    }
    
    public final String getFee() {
        return this.fee;
    }
    
    public final String getFrom() {
        return this.from;
    }
    
    public final String getId() {
        return this.id;
    }
    
    public final String getMemo() {
        return this.memo;
    }
    
    public final JsonObject getMetadata() {
        return this.metadata;
    }
    
    public final long getSequence() {
        return this.sequence;
    }
    
    public final String getStatus() {
        return this.status;
    }
    
    public final String getTo() {
        return this.to;
    }
    
    public final String getType() {
        return this.type;
    }
    
    @Override
    public int hashCode() {
        final String id = this.id;
        int hashCode = 0;
        int hashCode2;
        if (id != null) {
            hashCode2 = id.hashCode();
        } else {
            hashCode2 = 0;
        }
        final String error = this.error;
        int hashCode3;
        if (error != null) {
            hashCode3 = error.hashCode();
        } else {
            hashCode3 = 0;
        }
        final int coin = this.coin;
        final String from = this.from;
        int hashCode4;
        if (from != null) {
            hashCode4 = from.hashCode();
        } else {
            hashCode4 = 0;
        }
        final String to = this.to;
        int hashCode5;
        if (to != null) {
            hashCode5 = to.hashCode();
        } else {
            hashCode5 = 0;
        }
        final String fee = this.fee;
        int hashCode6;
        if (fee != null) {
            hashCode6 = fee.hashCode();
        } else {
            hashCode6 = 0;
        }
        final long date = this.date;
        final int n = (int) (date ^ date >>> 32);
        final String type = this.type;
        int hashCode7;
        if (type != null) {
            hashCode7 = type.hashCode();
        } else {
            hashCode7 = 0;
        }
        final String memo = this.memo;
        int hashCode8;
        if (memo != null) {
            hashCode8 = memo.hashCode();
        } else {
            hashCode8 = 0;
        }
        final Long destinationTag = this.destinationTag;
        int hashCode9;
        if (destinationTag != null) {
            hashCode9 = destinationTag.hashCode();
        } else {
            hashCode9 = 0;
        }
        final long block = this.block;
        final int n2 = (int) (block ^ block >>> 32);
        final String status = this.status;
        int hashCode10;
        if (status != null) {
            hashCode10 = status.hashCode();
        } else {
            hashCode10 = 0;
        }
        final long sequence = this.sequence;
        final int n3 = (int) (sequence ^ sequence >>> 32);
        final JsonObject metadata = this.metadata;
        if (metadata != null) {
            hashCode = metadata.hashCode();
        }
        return ((((((((((((hashCode2 * 31 + hashCode3) * 31 + coin) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + n) * 31 + hashCode7) * 31 + hashCode8) * 31 + hashCode9) * 31 + n2) * 31 + hashCode10) * 31 + n3) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BlockatlasTransaction(id=");
        sb.append(this.id);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", from=");
        sb.append(this.from);
        sb.append(", to=");
        sb.append(this.to);
        sb.append(", fee=");
        sb.append(this.fee);
        sb.append(", date=");
        sb.append(this.date);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", memo=");
        sb.append(this.memo);
        sb.append(", destinationTag=");
        sb.append(this.destinationTag);
        sb.append(", block=");
        sb.append(this.block);
        sb.append(", status=");
        sb.append(this.status);
        sb.append(", sequence=");
        sb.append(this.sequence);
        sb.append(", metadata=");
        sb.append(this.metadata);
        sb.append(")");
        return sb.toString();
    }
}