package com.wallet.crypto.trustapp.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoFiatMoney.kt */
public final class BuyCryptoFiatMoney {
    @SerializedName("base_amount")
    private final double baseAmount;
    private final String currency;
    @SerializedName("total_amount")
    private final double totalAmount;

    public BuyCryptoFiatMoney(String str, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(str, "currency");
        this.currency = str;
        this.baseAmount = d;
        this.totalAmount = d2;
    }

    public static /* synthetic */ BuyCryptoFiatMoney copy$default(BuyCryptoFiatMoney buyCryptoFiatMoney, String str, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buyCryptoFiatMoney.currency;
        }
        if ((i & 2) != 0) {
            d = buyCryptoFiatMoney.baseAmount;
        }
        double d3 = d;
        if ((i & 4) != 0) {
            d2 = buyCryptoFiatMoney.totalAmount;
        }
        return buyCryptoFiatMoney.copy(str, d3, d2);
    }

    public final String component1() {
        return this.currency;
    }

    public final double component2() {
        return this.baseAmount;
    }

    public final double component3() {
        return this.totalAmount;
    }

    public final BuyCryptoFiatMoney copy(String str, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(str, "currency");
        return new BuyCryptoFiatMoney(str, d, d2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoFiatMoney) {
                BuyCryptoFiatMoney buyCryptoFiatMoney = (BuyCryptoFiatMoney) obj;
                if (Intrinsics.areEqual(this.currency, buyCryptoFiatMoney.currency) && Double.compare(this.baseAmount, buyCryptoFiatMoney.baseAmount) == 0 && Double.compare(this.totalAmount, buyCryptoFiatMoney.totalAmount) == 0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final double getBaseAmount() {
        return this.baseAmount;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final double getTotalAmount() {
        return this.totalAmount;
    }

    public int hashCode() {
        String str = this.currency;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.baseAmount);
        hashCode = (hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        doubleToLongBits = Double.doubleToLongBits(this.totalAmount);
        return hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoFiatMoney(currency=");
        stringBuilder.append(this.currency);
        stringBuilder.append(", baseAmount=");
        stringBuilder.append(this.baseAmount);
        stringBuilder.append(", totalAmount=");
        stringBuilder.append(this.totalAmount);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
