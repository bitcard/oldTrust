package com.wallet.crypto.trustapp.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MarketInfoGraphValue implements Parcelable {
    public static final Creator<MarketInfoGraphValue> CREATOR = new C14391();
    public final float date;
    public final float value;

    /* renamed from: com.wallet.crypto.trustapp.entity.MarketInfoGraphValue$1 */
    static class C14391 implements Creator<MarketInfoGraphValue> {
        C14391() {
        }

        public MarketInfoGraphValue createFromParcel(Parcel parcel) {
            return new MarketInfoGraphValue(parcel);
        }

        public MarketInfoGraphValue[] newArray(int i) {
            return new MarketInfoGraphValue[i];
        }
    }

    public MarketInfoGraphValue(float f, float f2) {
        this.date = f;
        this.value = f2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.date);
        parcel.writeFloat(this.value);
    }

    protected MarketInfoGraphValue(Parcel parcel) {
        this.date = parcel.readFloat();
        this.value = parcel.readFloat();
    }
}
