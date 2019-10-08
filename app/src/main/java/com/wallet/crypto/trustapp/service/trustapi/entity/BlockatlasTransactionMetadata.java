package com.wallet.crypto.trustapp.service.trustapi.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockatlasTransaction.kt */
public final class BlockatlasTransactionMetadata {
    private final int decimals;
    private final String from;
    private final String input;
    private final String name;
    private final String symbol;
    private final String to;
    @SerializedName("token_id")
    private final String tokenId;
    private final String value;

    public BlockatlasTransactionMetadata(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7) {
        this.name = str;
        this.symbol = str2;
        this.tokenId = str3;
        this.decimals = i;
        this.value = str4;
        this.input = str5;
        this.from = str6;
        this.to = str7;
    }

    public static /* synthetic */ BlockatlasTransactionMetadata copy$default(BlockatlasTransactionMetadata blockatlasTransactionMetadata, String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, int i2, Object obj) {
        BlockatlasTransactionMetadata blockatlasTransactionMetadata2 = blockatlasTransactionMetadata;
        int i3 = i2;
        return blockatlasTransactionMetadata.copy((i3 & 1) != 0 ? blockatlasTransactionMetadata2.name : str, (i3 & 2) != 0 ? blockatlasTransactionMetadata2.symbol : str2, (i3 & 4) != 0 ? blockatlasTransactionMetadata2.tokenId : str3, (i3 & 8) != 0 ? blockatlasTransactionMetadata2.decimals : i, (i3 & 16) != 0 ? blockatlasTransactionMetadata2.value : str4, (i3 & 32) != 0 ? blockatlasTransactionMetadata2.input : str5, (i3 & 64) != 0 ? blockatlasTransactionMetadata2.from : str6, (i3 & 128) != 0 ? blockatlasTransactionMetadata2.to : str7);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.symbol;
    }

    public final String component3() {
        return this.tokenId;
    }

    public final int component4() {
        return this.decimals;
    }

    public final String component5() {
        return this.value;
    }

    public final String component6() {
        return this.input;
    }

    public final String component7() {
        return this.from;
    }

    public final String component8() {
        return this.to;
    }

    public final BlockatlasTransactionMetadata copy(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7) {
        return new BlockatlasTransactionMetadata(str, str2, str3, i, str4, str5, str6, str7);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BlockatlasTransactionMetadata) {
                BlockatlasTransactionMetadata blockatlasTransactionMetadata = (BlockatlasTransactionMetadata) obj;
                if (Intrinsics.areEqual(this.name, blockatlasTransactionMetadata.name) && Intrinsics.areEqual(this.symbol, blockatlasTransactionMetadata.symbol) && Intrinsics.areEqual(this.tokenId, blockatlasTransactionMetadata.tokenId)) {
                    if ((this.decimals == blockatlasTransactionMetadata.decimals) && Intrinsics.areEqual(this.value, blockatlasTransactionMetadata.value) && Intrinsics.areEqual(this.input, blockatlasTransactionMetadata.input) && Intrinsics.areEqual(this.from, blockatlasTransactionMetadata.from) && Intrinsics.areEqual(this.to, blockatlasTransactionMetadata.to)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int getDecimals() {
        return this.decimals;
    }

    public final String getFrom() {
        return this.from;
    }

    public final String getInput() {
        return this.input;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSymbol() {
        return this.symbol;
    }

    public final String getTo() {
        return this.to;
    }

    public final String getTokenId() {
        return this.tokenId;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.symbol;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.tokenId;
        hashCode = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.decimals) * 31;
        str2 = this.value;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.input;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.from;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.to;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BlockatlasTransactionMetadata(name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", symbol=");
        stringBuilder.append(this.symbol);
        stringBuilder.append(", tokenId=");
        stringBuilder.append(this.tokenId);
        stringBuilder.append(", decimals=");
        stringBuilder.append(this.decimals);
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", input=");
        stringBuilder.append(this.input);
        stringBuilder.append(", from=");
        stringBuilder.append(this.from);
        stringBuilder.append(", to=");
        stringBuilder.append(this.to);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public BlockatlasTransactionMetadata(String str) {
        this(null, null, null, 0, str, null, null, null);
    }

    public BlockatlasTransactionMetadata(String str, String str2) {
        this(null, null, null, 0, str, str2, null, null);
    }
}
