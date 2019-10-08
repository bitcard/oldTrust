package com.wallet.crypto.trustapp.entity;

import java.math.BigInteger;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Lot.kt */
public final class LotInfo {
    private final String baseAssetContract;
    private final String baseAssetSymbol;
    private final BigInteger listPrice;
    private final BigInteger lotSize;
    private final String quoteAssetContract;
    private final String quoteAssetSymbol;
    private final BigInteger tickSize;

    public LotInfo(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(bigInteger, "lotSize");
        Intrinsics.checkParameterIsNotNull(bigInteger2, "listPrice");
        Intrinsics.checkParameterIsNotNull(bigInteger3, "tickSize");
        Intrinsics.checkParameterIsNotNull(str, "quoteAssetSymbol");
        Intrinsics.checkParameterIsNotNull(str2, "quoteAssetContract");
        Intrinsics.checkParameterIsNotNull(str3, "baseAssetSymbol");
        Intrinsics.checkParameterIsNotNull(str4, "baseAssetContract");
        this.lotSize = bigInteger;
        this.listPrice = bigInteger2;
        this.tickSize = bigInteger3;
        this.quoteAssetSymbol = str;
        this.quoteAssetContract = str2;
        this.baseAssetSymbol = str3;
        this.baseAssetContract = str4;
    }

    public static /* synthetic */ LotInfo copy$default(LotInfo lotInfo, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            bigInteger = lotInfo.lotSize;
        }
        if ((i & 2) != 0) {
            bigInteger2 = lotInfo.listPrice;
        }
        BigInteger bigInteger4 = bigInteger2;
        if ((i & 4) != 0) {
            bigInteger3 = lotInfo.tickSize;
        }
        BigInteger bigInteger5 = bigInteger3;
        if ((i & 8) != 0) {
            str = lotInfo.quoteAssetSymbol;
        }
        String str5 = str;
        if ((i & 16) != 0) {
            str2 = lotInfo.quoteAssetContract;
        }
        String str6 = str2;
        if ((i & 32) != 0) {
            str3 = lotInfo.baseAssetSymbol;
        }
        String str7 = str3;
        if ((i & 64) != 0) {
            str4 = lotInfo.baseAssetContract;
        }
        return lotInfo.copy(bigInteger, bigInteger4, bigInteger5, str5, str6, str7, str4);
    }

    public final BigInteger component1() {
        return this.lotSize;
    }

    public final BigInteger component2() {
        return this.listPrice;
    }

    public final BigInteger component3() {
        return this.tickSize;
    }

    public final String component4() {
        return this.quoteAssetSymbol;
    }

    public final String component5() {
        return this.quoteAssetContract;
    }

    public final String component6() {
        return this.baseAssetSymbol;
    }

    public final String component7() {
        return this.baseAssetContract;
    }

    public final LotInfo copy(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, String str2, String str3, String str4) {
        BigInteger bigInteger4 = bigInteger;
        Intrinsics.checkParameterIsNotNull(bigInteger, "lotSize");
        BigInteger bigInteger5 = bigInteger2;
        Intrinsics.checkParameterIsNotNull(bigInteger2, "listPrice");
        BigInteger bigInteger6 = bigInteger3;
        Intrinsics.checkParameterIsNotNull(bigInteger3, "tickSize");
        String str5 = str;
        Intrinsics.checkParameterIsNotNull(str, "quoteAssetSymbol");
        String str6 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "quoteAssetContract");
        String str7 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "baseAssetSymbol");
        String str8 = str4;
        Intrinsics.checkParameterIsNotNull(str8, "baseAssetContract");
        return new LotInfo(bigInteger4, bigInteger5, bigInteger6, str5, str6, str7, str8);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof LotInfo) {
                LotInfo lotInfo = (LotInfo) obj;
                if (Intrinsics.areEqual(this.lotSize, lotInfo.lotSize) && Intrinsics.areEqual(this.listPrice, lotInfo.listPrice) && Intrinsics.areEqual(this.tickSize, lotInfo.tickSize) && Intrinsics.areEqual(this.quoteAssetSymbol, lotInfo.quoteAssetSymbol) && Intrinsics.areEqual(this.quoteAssetContract, lotInfo.quoteAssetContract) && Intrinsics.areEqual(this.baseAssetSymbol, lotInfo.baseAssetSymbol) && Intrinsics.areEqual(this.baseAssetContract, lotInfo.baseAssetContract)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getBaseAssetContract() {
        return this.baseAssetContract;
    }

    public final String getBaseAssetSymbol() {
        return this.baseAssetSymbol;
    }

    public final BigInteger getListPrice() {
        return this.listPrice;
    }

    public final BigInteger getLotSize() {
        return this.lotSize;
    }

    public final String getQuoteAssetContract() {
        return this.quoteAssetContract;
    }

    public final String getQuoteAssetSymbol() {
        return this.quoteAssetSymbol;
    }

    public final BigInteger getTickSize() {
        return this.tickSize;
    }

    public int hashCode() {
        BigInteger bigInteger = this.lotSize;
        int i = 0;
        int hashCode = (bigInteger != null ? bigInteger.hashCode() : 0) * 31;
        BigInteger bigInteger2 = this.listPrice;
        hashCode = (hashCode + (bigInteger2 != null ? bigInteger2.hashCode() : 0)) * 31;
        bigInteger2 = this.tickSize;
        hashCode = (hashCode + (bigInteger2 != null ? bigInteger2.hashCode() : 0)) * 31;
        String str = this.quoteAssetSymbol;
        hashCode = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        str = this.quoteAssetContract;
        hashCode = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        str = this.baseAssetSymbol;
        hashCode = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        str = this.baseAssetContract;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LotInfo(lotSize=");
        stringBuilder.append(this.lotSize);
        stringBuilder.append(", listPrice=");
        stringBuilder.append(this.listPrice);
        stringBuilder.append(", tickSize=");
        stringBuilder.append(this.tickSize);
        stringBuilder.append(", quoteAssetSymbol=");
        stringBuilder.append(this.quoteAssetSymbol);
        stringBuilder.append(", quoteAssetContract=");
        stringBuilder.append(this.quoteAssetContract);
        stringBuilder.append(", baseAssetSymbol=");
        stringBuilder.append(this.baseAssetSymbol);
        stringBuilder.append(", baseAssetContract=");
        stringBuilder.append(this.baseAssetContract);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
