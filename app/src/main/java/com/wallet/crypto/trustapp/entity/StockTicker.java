package com.wallet.crypto.trustapp.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;

public class StockTicker implements Parcelable {
    public static final Creator<StockTicker> CREATOR = new C14401();
    public final String circulatingSupply;
    public final int coin;
    public final String contract;
    public final int id;
    public final String marketCap;
    public final String name;
    public final SparseArray<Object> percentage;
    public final String price;
    public final int rank;
    public final String symbol;
    public final String totalSupply;
    public final String volume24h;
    public final String websiteSlug;

    /* renamed from: com.wallet.crypto.trustapp.entity.StockTicker$1 */
    static class C14401 implements Creator<StockTicker> {
        C14401() {
        }

        public StockTicker createFromParcel(Parcel parcel) {
            return new StockTicker(parcel);
        }

        public StockTicker[] newArray(int i) {
            return new StockTicker[i];
        }
    }

    public StockTicker(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, SparseArray<Object> sparseArray, int i2, int i3, String str9) {
        this.id = i;
        this.name = str;
        this.symbol = str2;
        this.price = str3;
        this.marketCap = str4;
        this.volume24h = str5;
        this.websiteSlug = str6;
        this.circulatingSupply = str7;
        this.totalSupply = str8;
        this.percentage = sparseArray;
        this.rank = i2;
        this.coin = i3;
        this.contract = str9;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.symbol);
        parcel.writeString(this.price);
        parcel.writeString(this.marketCap);
        parcel.writeString(this.volume24h);
        parcel.writeString(this.websiteSlug);
        parcel.writeString(this.price);
        parcel.writeString(this.circulatingSupply);
        parcel.writeString(this.totalSupply);
        parcel.writeSparseArray(this.percentage);
        parcel.writeInt(this.rank);
        parcel.writeInt(this.coin);
        parcel.writeString(this.contract);
    }

    protected StockTicker(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.symbol = parcel.readString();
        this.price = parcel.readString();
        this.marketCap = parcel.readString();
        this.volume24h = parcel.readString();
        this.websiteSlug = parcel.readString();
        this.circulatingSupply = parcel.readString();
        this.totalSupply = parcel.readString();
        this.percentage = parcel.readSparseArray(getClass().getClassLoader());
        this.rank = parcel.readInt();
        this.coin = parcel.readInt();
        this.contract = parcel.readString();
    }
}
