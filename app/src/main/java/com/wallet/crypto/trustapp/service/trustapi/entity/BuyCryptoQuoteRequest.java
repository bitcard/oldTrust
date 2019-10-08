package com.wallet.crypto.trustapp.service.trustapi.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: BuyCryptoQuoteRequest.kt */
public final class BuyCryptoQuoteRequest {
    private final String address;
    private final double amount;
    private final int coin;
    private final String contract;
    private final String currency;

    public BuyCryptoQuoteRequest(int i, double d, String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "contract");
        Intrinsics.checkParameterIsNotNull(str2, Address.TYPE_NAME);
        Intrinsics.checkParameterIsNotNull(str3, "currency");
        this.coin = i;
        this.amount = d;
        this.contract = str;
        this.address = str2;
        this.currency = str3;
    }

    public static /* synthetic */ BuyCryptoQuoteRequest copy$default(BuyCryptoQuoteRequest buyCryptoQuoteRequest, int i, double d, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = buyCryptoQuoteRequest.coin;
        }
        if ((i2 & 2) != 0) {
            d = buyCryptoQuoteRequest.amount;
        }
        double d2 = d;
        if ((i2 & 4) != 0) {
            str = buyCryptoQuoteRequest.contract;
        }
        String str4 = str;
        if ((i2 & 8) != 0) {
            str2 = buyCryptoQuoteRequest.address;
        }
        String str5 = str2;
        if ((i2 & 16) != 0) {
            str3 = buyCryptoQuoteRequest.currency;
        }
        return buyCryptoQuoteRequest.copy(i, d2, str4, str5, str3);
    }

    public final int component1() {
        return this.coin;
    }

    public final double component2() {
        return this.amount;
    }

    public final String component3() {
        return this.contract;
    }

    public final String component4() {
        return this.address;
    }

    public final String component5() {
        return this.currency;
    }

    public final BuyCryptoQuoteRequest copy(int i, double d, String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "contract");
        Intrinsics.checkParameterIsNotNull(str2, Address.TYPE_NAME);
        Intrinsics.checkParameterIsNotNull(str3, "currency");
        return new BuyCryptoQuoteRequest(i, d, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BuyCryptoQuoteRequest) {
                BuyCryptoQuoteRequest buyCryptoQuoteRequest = (BuyCryptoQuoteRequest) obj;
                if ((this.coin == buyCryptoQuoteRequest.coin) && Double.compare(this.amount, buyCryptoQuoteRequest.amount) == 0 && Intrinsics.areEqual(this.contract, buyCryptoQuoteRequest.contract) && Intrinsics.areEqual(this.address, buyCryptoQuoteRequest.address) && Intrinsics.areEqual(this.currency, buyCryptoQuoteRequest.currency)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getAddress() {
        return this.address;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final int getCoin() {
        return this.coin;
    }

    public final String getContract() {
        return this.contract;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public int hashCode() {
        int i = this.coin * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.amount);
        i = (i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        String str = this.contract;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.address;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.currency;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BuyCryptoQuoteRequest(coin=");
        stringBuilder.append(this.coin);
        stringBuilder.append(", amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(", contract=");
        stringBuilder.append(this.contract);
        stringBuilder.append(", address=");
        stringBuilder.append(this.address);
        stringBuilder.append(", currency=");
        stringBuilder.append(this.currency);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
