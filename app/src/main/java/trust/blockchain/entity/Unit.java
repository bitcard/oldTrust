package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import wallet.core.jni.CoinType;
import wallet.core.jni.CoinTypeConfiguration;

public class Unit implements Parcelable {
    public static final Creator<Unit> CREATOR = new C21071();
    public static final Unit DEFAULT = new Unit(18, "");
    public final int decimals;
    public final String symbol;
    public final String tokenSymbol;

    /* renamed from: trust.blockchain.entity.Unit$1 */
    static class C21071 implements Creator<Unit> {
        C21071() {
        }

        public Unit createFromParcel(Parcel parcel) {
            return new Unit(parcel);
        }

        public Unit[] newArray(int i) {
            return new Unit[i];
        }
    }

    public Unit(CoinType coinType, String str) {
        this(CoinTypeConfiguration.getDecimals(coinType), CoinTypeConfiguration.getSymbol(coinType), str);
    }

    public int describeContents() {
        return 0;
    }

    public SubunitValue toSubunit(BigDecimal bigDecimal) {
        return new SubunitValue(toUnit(bigDecimal), this);
    }

    public BigInteger toUnit(String str) {
        return toUnit(new BigDecimal(str));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.decimals);
        parcel.writeString(this.symbol);
        parcel.writeString(this.tokenSymbol);
    }

    public Unit(int i, String str) {
        this(i, str, str);
    }

    public BigInteger toUnit(BigDecimal bigDecimal) {
        return bigDecimal.multiply(BigDecimal.TEN.pow(this.decimals)).toBigInteger();
    }

    public Unit(int i, String str, String str2) {
        this.decimals = i;
        this.symbol = str;
        this.tokenSymbol = str2;
    }

    protected Unit(Parcel parcel) {
        this.decimals = parcel.readInt();
        this.symbol = parcel.readString();
        this.tokenSymbol = parcel.readString();
    }
}
