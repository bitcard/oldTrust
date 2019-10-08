package com.wallet.crypto.trustapp.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoDigitalMoney.kt */
public final class BuyCryptoDigitalMoney {
    private final double amount;
    private final String currency;

    public BuyCryptoDigitalMoney(String str, double d) {
        Intrinsics.checkParameterIsNotNull(str, "currency");
        this.currency = str;
        this.amount = d;
    }

    public static /* synthetic */ BuyCryptoDigitalMoney copy$default(BuyCryptoDigitalMoney buyCryptoDigitalMoney, String str, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buyCryptoDigitalMoney.currency;
        }
        if ((i & 2) != 0) {
            d = buyCryptoDigitalMoney.amount;
        }
        return buyCryptoDigitalMoney.copy(str, d);
    }

    public final String component1() {
        return this.currency;
    }

    public final double component2() {
        return this.amount;
    }

    public final BuyCryptoDigitalMoney copy(String str, double d) {
        Intrinsics.checkParameterIsNotNull(str, "currency");
        return new BuyCryptoDigitalMoney(str, d);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoDigitalMoney) {
                BuyCryptoDigitalMoney buyCryptoDigitalMoney = (BuyCryptoDigitalMoney) obj;
                if (Intrinsics.areEqual(this.currency, buyCryptoDigitalMoney.currency) && Double.compare(this.amount, buyCryptoDigitalMoney.amount) == 0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public int hashCode() {
        String str = this.currency;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.amount);
        return hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoDigitalMoney(currency=");
        stringBuilder.append(this.currency);
        stringBuilder.append(", amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
