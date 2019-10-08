package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import trust.blockchain.Slip;

public class Contract implements Parcelable {
    public static final Creator<Contract> CREATOR = new C20801();
    public final Address address;
    public final Slip coin;
    public final String contract;
    public final String name;
    public final String tokenId;
    public final Unit unit;

    /* renamed from: trust.blockchain.entity.Contract$1 */
    static class C20801 implements Creator<Contract> {
        C20801() {
        }

        public Contract createFromParcel(Parcel parcel) {
            return new Contract(parcel);
        }

        public Contract[] newArray(int i) {
            return new Contract[i];
        }
    }

    public Contract(String str, String str2, Unit unit, Slip slip) {
        this(str, "", str2, unit, slip);
    }

    public int describeContents() {
        return 0;
    }

    public String getTokenType() {
        return String.format(this.coin.unit().tokenSymbol, new Object[]{""});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.coin.name());
        parcel.writeString(this.address.data());
        parcel.writeString(this.contract);
        parcel.writeString(this.name);
        parcel.writeParcelable(this.unit, i);
        parcel.writeString(this.tokenId);
    }

    public Contract(String str, String str2, String str3, Unit unit, Slip slip) {
        this(slip.toAddress(str), str2, str3, unit, slip);
    }

    public Contract(Address address, String str, Unit unit, Slip slip) {
        this(address, "", str, unit, slip);
    }

    public Contract(Address address, String str, String str2, Unit unit, Slip slip) {
        this(address, str, str2, unit, slip, str);
    }

    public Contract(Address address, String str, String str2, Unit unit, Slip slip, String str3) {
        this.address = address;
        this.contract = str;
        this.name = str2;
        this.unit = unit;
        this.coin = slip;
        this.tokenId = str3;
    }

    protected Contract(Parcel parcel) {
        this.coin = Slip.valueOf(parcel.readString());
        this.address = this.coin.toAddress(parcel.readString());
        this.contract = parcel.readString();
        this.name = parcel.readString();
        this.unit = (Unit) parcel.readParcelable(Unit.class.getClassLoader());
        this.tokenId = parcel.readString();
    }
}
