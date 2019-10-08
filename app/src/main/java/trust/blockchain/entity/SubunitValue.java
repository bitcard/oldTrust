package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SubunitValue extends Value implements Parcelable {
    public static final Creator<SubunitValue> CREATOR = new C21011();
    private boolean isShowSymbol;
    public final Unit unit;

    /* renamed from: trust.blockchain.entity.SubunitValue$1 */
    static class C21011 implements Creator<SubunitValue> {
        C21011() {
        }

        public SubunitValue createFromParcel(Parcel parcel) {
            return new SubunitValue(parcel);
        }

        public SubunitValue[] newArray(int i) {
            return new SubunitValue[i];
        }
    }

    public SubunitValue(String str, Unit unit) {
        super(str);
        this.isShowSymbol = true;
        this.unit = unit;
    }

    public BigDecimal convert() {
        return this.unit.decimals > 0 ? bigDecimal().divide(BigDecimal.TEN.pow(this.unit.decimals)) : new BigDecimal(rawString());
    }

    public String format(int i, char c, String str, int i2) {
        String format = super.format(i, c, str, i2);
        if (!this.isShowSymbol || TextUtils.isEmpty(this.unit.symbol)) {
            return format;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(format);
        stringBuilder.append(" ");
        stringBuilder.append(this.unit.symbol);
        return stringBuilder.toString();
    }

    public SubunitValue setShowSymbol(boolean z) {
        this.isShowSymbol = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.unit, i);
        parcel.writeInt(this.isShowSymbol ? 1 : 0);
    }

    public SubunitValue(String str, Unit unit, boolean z) {
        this(str, unit);
        setShowSymbol(z);
    }

    public SubunitValue(Value value, Unit unit) {
        super(value.rawString());
        this.isShowSymbol = true;
        this.unit = unit;
    }

    public SubunitValue(Value value, Unit unit, boolean z) {
        this(value, unit);
        setShowSymbol(z);
    }

    public SubunitValue(BigDecimal bigDecimal, Unit unit) {
        this(bigDecimal.toString(), unit);
    }

    public SubunitValue(BigDecimal bigDecimal, Unit unit, boolean z) {
        this(bigDecimal, unit);
        setShowSymbol(z);
    }

    public SubunitValue(BigInteger bigInteger, Unit unit) {
        this(bigInteger.toString(), unit);
    }

    public SubunitValue(BigInteger bigInteger, Unit unit, boolean z) {
        this(bigInteger, unit);
        setShowSymbol(z);
    }

    public SubunitValue(double d, Unit unit) {
        this(Double.toString(d), unit);
    }

    public SubunitValue(double d, Unit unit, boolean z) {
        this(d, unit);
        setShowSymbol(z);
    }

    protected SubunitValue(Parcel parcel) {
        super(parcel);
        boolean z = true;
        this.isShowSymbol = true;
        this.unit = (Unit) parcel.readParcelable(Unit.class.getClassLoader());
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.isShowSymbol = z;
    }
}
