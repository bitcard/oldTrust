package com.wallet.crypto.trustapp.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BuyCryptoQuote.kt */
public final class BuyCryptoQuote {
    @SerializedName("digital_money")
    private final BuyCryptoDigitalMoney digitalMoney;
    @SerializedName("fiat_money")
    private final BuyCryptoFiatMoney fiatMoney;
    private final String id;

    public BuyCryptoQuote(String str, BuyCryptoDigitalMoney buyCryptoDigitalMoney, BuyCryptoFiatMoney buyCryptoFiatMoney) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(buyCryptoDigitalMoney, "digitalMoney");
        Intrinsics.checkParameterIsNotNull(buyCryptoFiatMoney, "fiatMoney");
        this.id = str;
        this.digitalMoney = buyCryptoDigitalMoney;
        this.fiatMoney = buyCryptoFiatMoney;
    }

    public static /* synthetic */ BuyCryptoQuote copy$default(BuyCryptoQuote buyCryptoQuote, String str, BuyCryptoDigitalMoney buyCryptoDigitalMoney, BuyCryptoFiatMoney buyCryptoFiatMoney, int i, Object obj) {
        if ((i & 1) != 0) {
            str = buyCryptoQuote.id;
        }
        if ((i & 2) != 0) {
            buyCryptoDigitalMoney = buyCryptoQuote.digitalMoney;
        }
        if ((i & 4) != 0) {
            buyCryptoFiatMoney = buyCryptoQuote.fiatMoney;
        }
        return buyCryptoQuote.copy(str, buyCryptoDigitalMoney, buyCryptoFiatMoney);
    }

    public final String component1() {
        return this.id;
    }

    public final BuyCryptoDigitalMoney component2() {
        return this.digitalMoney;
    }

    public final BuyCryptoFiatMoney component3() {
        return this.fiatMoney;
    }

    public final BuyCryptoQuote copy(String str, BuyCryptoDigitalMoney buyCryptoDigitalMoney, BuyCryptoFiatMoney buyCryptoFiatMoney) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(buyCryptoDigitalMoney, "digitalMoney");
        Intrinsics.checkParameterIsNotNull(buyCryptoFiatMoney, "fiatMoney");
        return new BuyCryptoQuote(str, buyCryptoDigitalMoney, buyCryptoFiatMoney);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoQuote) {
                BuyCryptoQuote buyCryptoQuote = (BuyCryptoQuote) obj;
                if (Intrinsics.areEqual(this.id, buyCryptoQuote.id) && Intrinsics.areEqual(this.digitalMoney, buyCryptoQuote.digitalMoney) && Intrinsics.areEqual(this.fiatMoney, buyCryptoQuote.fiatMoney)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BuyCryptoDigitalMoney getDigitalMoney() {
        return this.digitalMoney;
    }

    public final BuyCryptoFiatMoney getFiatMoney() {
        return this.fiatMoney;
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        BuyCryptoDigitalMoney buyCryptoDigitalMoney = this.digitalMoney;
        hashCode = (hashCode + (buyCryptoDigitalMoney != null ? buyCryptoDigitalMoney.hashCode() : 0)) * 31;
        BuyCryptoFiatMoney buyCryptoFiatMoney = this.fiatMoney;
        if (buyCryptoFiatMoney != null) {
            i = buyCryptoFiatMoney.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoQuote(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", digitalMoney=");
        stringBuilder.append(this.digitalMoney);
        stringBuilder.append(", fiatMoney=");
        stringBuilder.append(this.fiatMoney);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
