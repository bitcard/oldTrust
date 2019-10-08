package com.wallet.crypto.trustapp.service.rpc.icon.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: IconTransactionResult.kt */
public final class IconTransactionResult {
    private final String blockHash;
    private final String blockHeight;
    private final IconFailure failure;
    private final String from;
    private final String nonce;
    private final String status;
    private final String stepPrice;
    private final String stepUsed;
    private final String timestamp;
    private final String to;
    private final String value;

    public IconTransactionResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, IconFailure iconFailure) {
        Intrinsics.checkParameterIsNotNull(str, "timestamp");
        Intrinsics.checkParameterIsNotNull(str2, "from");
        Intrinsics.checkParameterIsNotNull(str3, "to");
        Intrinsics.checkParameterIsNotNull(str4, "value");
        Intrinsics.checkParameterIsNotNull(str5, "blockHash");
        Intrinsics.checkParameterIsNotNull(str6, "status");
        Intrinsics.checkParameterIsNotNull(str7, "stepUsed");
        Intrinsics.checkParameterIsNotNull(str8, "stepPrice");
        Intrinsics.checkParameterIsNotNull(str9, "blockHeight");
        Intrinsics.checkParameterIsNotNull(str10, "nonce");
        this.timestamp = str;
        this.from = str2;
        this.to = str3;
        this.value = str4;
        this.blockHash = str5;
        this.status = str6;
        this.stepUsed = str7;
        this.stepPrice = str8;
        this.blockHeight = str9;
        this.nonce = str10;
        this.failure = iconFailure;
    }

    public static /* synthetic */ IconTransactionResult copy$default(IconTransactionResult iconTransactionResult, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, IconFailure iconFailure, int i, Object obj) {
        IconTransactionResult iconTransactionResult2 = iconTransactionResult;
        int i2 = i;
        return iconTransactionResult.copy((i2 & 1) != 0 ? iconTransactionResult2.timestamp : str, (i2 & 2) != 0 ? iconTransactionResult2.from : str2, (i2 & 4) != 0 ? iconTransactionResult2.to : str3, (i2 & 8) != 0 ? iconTransactionResult2.value : str4, (i2 & 16) != 0 ? iconTransactionResult2.blockHash : str5, (i2 & 32) != 0 ? iconTransactionResult2.status : str6, (i2 & 64) != 0 ? iconTransactionResult2.stepUsed : str7, (i2 & 128) != 0 ? iconTransactionResult2.stepPrice : str8, (i2 & Type.MAX_BIT_LENGTH) != 0 ? iconTransactionResult2.blockHeight : str9, (i2 & SimpleExporter.N) != 0 ? iconTransactionResult2.nonce : str10, (i2 & 1024) != 0 ? iconTransactionResult2.failure : iconFailure);
    }

    public final String component1() {
        return this.timestamp;
    }

    public final String component10() {
        return this.nonce;
    }

    public final IconFailure component11() {
        return this.failure;
    }

    public final String component2() {
        return this.from;
    }

    public final String component3() {
        return this.to;
    }

    public final String component4() {
        return this.value;
    }

    public final String component5() {
        return this.blockHash;
    }

    public final String component6() {
        return this.status;
    }

    public final String component7() {
        return this.stepUsed;
    }

    public final String component8() {
        return this.stepPrice;
    }

    public final String component9() {
        return this.blockHeight;
    }

    public final IconTransactionResult copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, IconFailure iconFailure) {
        String str11 = str;
        Intrinsics.checkParameterIsNotNull(str, "timestamp");
        String str12 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "from");
        String str13 = str3;
        Intrinsics.checkParameterIsNotNull(str13, "to");
        String str14 = str4;
        Intrinsics.checkParameterIsNotNull(str14, "value");
        String str15 = str5;
        Intrinsics.checkParameterIsNotNull(str15, "blockHash");
        String str16 = str6;
        Intrinsics.checkParameterIsNotNull(str16, "status");
        String str17 = str7;
        Intrinsics.checkParameterIsNotNull(str17, "stepUsed");
        String str18 = str8;
        Intrinsics.checkParameterIsNotNull(str18, "stepPrice");
        String str19 = str9;
        Intrinsics.checkParameterIsNotNull(str19, "blockHeight");
        String str20 = str10;
        Intrinsics.checkParameterIsNotNull(str20, "nonce");
        return new IconTransactionResult(str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, iconFailure);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IconTransactionResult) {
                IconTransactionResult iconTransactionResult = (IconTransactionResult) obj;
                if (Intrinsics.areEqual(this.timestamp, iconTransactionResult.timestamp) && Intrinsics.areEqual(this.from, iconTransactionResult.from) && Intrinsics.areEqual(this.to, iconTransactionResult.to) && Intrinsics.areEqual(this.value, iconTransactionResult.value) && Intrinsics.areEqual(this.blockHash, iconTransactionResult.blockHash) && Intrinsics.areEqual(this.status, iconTransactionResult.status) && Intrinsics.areEqual(this.stepUsed, iconTransactionResult.stepUsed) && Intrinsics.areEqual(this.stepPrice, iconTransactionResult.stepPrice) && Intrinsics.areEqual(this.blockHeight, iconTransactionResult.blockHeight) && Intrinsics.areEqual(this.nonce, iconTransactionResult.nonce) && Intrinsics.areEqual(this.failure, iconTransactionResult.failure)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getBlockHash() {
        return this.blockHash;
    }

    public final String getBlockHeight() {
        return this.blockHeight;
    }

    public final IconFailure getFailure() {
        return this.failure;
    }

    public final String getFrom() {
        return this.from;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getStepPrice() {
        return this.stepPrice;
    }

    public final String getStepUsed() {
        return this.stepUsed;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final String getTo() {
        return this.to;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.timestamp;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.from;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.to;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.value;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.blockHash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.status;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.stepUsed;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.stepPrice;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.blockHeight;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.nonce;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        IconFailure iconFailure = this.failure;
        if (iconFailure != null) {
            i = iconFailure.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IconTransactionResult(timestamp=");
        stringBuilder.append(this.timestamp);
        stringBuilder.append(", from=");
        stringBuilder.append(this.from);
        stringBuilder.append(", to=");
        stringBuilder.append(this.to);
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", blockHash=");
        stringBuilder.append(this.blockHash);
        stringBuilder.append(", status=");
        stringBuilder.append(this.status);
        stringBuilder.append(", stepUsed=");
        stringBuilder.append(this.stepUsed);
        stringBuilder.append(", stepPrice=");
        stringBuilder.append(this.stepPrice);
        stringBuilder.append(", blockHeight=");
        stringBuilder.append(this.blockHeight);
        stringBuilder.append(", nonce=");
        stringBuilder.append(this.nonce);
        stringBuilder.append(", failure=");
        stringBuilder.append(this.failure);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
