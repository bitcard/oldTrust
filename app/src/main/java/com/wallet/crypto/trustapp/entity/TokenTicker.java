package com.wallet.crypto.trustapp.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.PlainAddress;
import trust.blockchain.entity.Ticker;

public class TokenTicker implements Ticker, Parcelable {
    public static final Creator<TokenTicker> CREATOR = new C14421();
    private final PlainAddress contract;
    private String currencyCode;
    private final String percentChange24h;
    private final String price;
    private final long updateTime;

    /* renamed from: com.wallet.crypto.trustapp.entity.TokenTicker$1 */
    static class C14421 implements Creator<TokenTicker> {
        C14421() {
        }

        public TokenTicker createFromParcel(Parcel parcel) {
            return new TokenTicker(parcel, null);
        }

        public TokenTicker[] newArray(int i) {
            return new TokenTicker[i];
        }
    }

    /* synthetic */ TokenTicker(Parcel parcel, C14421 c14421) {
        this(parcel);
    }

    public boolean compare(Ticker ticker) {
        return this.contract.equals(ticker.getContract()) && this.currencyCode.equals(ticker.getCurrencyCode()) && this.price.equals(ticker.getPrice());
    }

    public int describeContents() {
        return 0;
    }

    public Address getContract() {
        return this.contract;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getPrice() {
        return TextUtils.isEmpty(this.price) ? "0" : this.price;
    }

    public String getSymbol() {
        return CurrencyInfo.codeToSymbol(this.currencyCode);
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public Double percentChange24h() {
        try {
            return Double.valueOf(this.percentChange24h);
        } catch (Exception unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.contract, i);
        parcel.writeString(this.price);
        parcel.writeString(this.percentChange24h);
        parcel.writeString(this.currencyCode);
        parcel.writeLong(this.updateTime);
    }

    public TokenTicker(PlainAddress plainAddress, String str, String str2, String str3, long j) {
        this.contract = plainAddress;
        this.price = str;
        this.percentChange24h = str2;
        this.currencyCode = str3;
        this.updateTime = j;
    }

    private TokenTicker(Parcel parcel) {
        this.contract = (PlainAddress) parcel.readParcelable(PlainAddress.class.getClassLoader());
        this.price = parcel.readString();
        this.percentChange24h = parcel.readString();
        this.currencyCode = parcel.readString();
        this.updateTime = parcel.readLong();
    }
}
