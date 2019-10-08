package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Value implements Parcelable {
    public static final int ALL_SIGN = 1;
    public static final Creator<Value> CREATOR = new C21081();
    public static final int MINUS_ONLY = -1;
    private static final int NONE_ROUND = -1;
    public static final int NO_SIGN = 0;
    private final String raw;

    /* renamed from: trust.blockchain.entity.Value$1 */
    static class C21081 implements Creator<Value> {
        C21081() {
        }

        public Value createFromParcel(Parcel parcel) {
            return new Value(parcel);
        }

        public Value[] newArray(int i) {
            return new Value[i];
        }
    }

    public Value(String str) {
        this.raw = str;
    }

    public BigDecimal bigDecimal() {
        return new BigDecimal(this.raw);
    }

    public BigInteger bigInteger() {
        return bigDecimal().toBigInteger();
    }

    public BigDecimal convert() {
        return bigDecimal();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null || !(obj instanceof Value)) {
            return false;
        }
        String str = ((Value) obj).raw;
        if (str != null && str.equalsIgnoreCase(this.raw)) {
            z = true;
        }
        return z;
    }

    public String format(int i, char c, String str, int i2) {
        String bigDecimal;
        String format;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
        BigDecimal convert = convert();
        if (i == -1) {
            bigDecimal = convert.abs().toString();
            format = decimalFormat.format(convert.abs());
        } else {
            format = convert.stripTrailingZeros().abs().toPlainString();
            String bigInteger = convert.toBigInteger().abs().toString();
            String format2 = decimalFormat.format(convert.toBigInteger().abs());
            format = format.substring(bigInteger.length());
            i = Math.min(i, format.length() - 1);
            if (i > 0) {
                bigDecimal = format.substring(0, i + 1);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(bigInteger);
                stringBuilder.append(bigDecimal);
                bigDecimal = stringBuilder.toString();
                format = decimalFormat.format(new BigDecimal(bigDecimal));
            } else {
                bigDecimal = bigInteger;
                format = format2;
            }
        }
        decimalFormat.setMaximumFractionDigits(2);
        if (convert.compareTo(BigDecimal.ZERO) == 0) {
            format = str == null ? decimalFormat.format(0.0d) : str;
        } else if (new BigDecimal(bigDecimal).compareTo(BigDecimal.ZERO) == 0) {
            format = decimalFormat.format(0.0d);
        }
        if (format == null || format.equals(str)) {
            return format;
        }
        StringBuilder stringBuilder2;
        if (i2 == -1) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(convert.compareTo(BigDecimal.ZERO) < 0 ? "-" : "");
            stringBuilder2.append(format);
            return stringBuilder2.toString();
        } else if (i2 != 1) {
            return format;
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(convert.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-");
            stringBuilder2.append(format);
            return stringBuilder2.toString();
        }
    }

    public String fullFormat() {
        return fullFormat("", -1);
    }

    public String mediumFormat() {
        return mediumFormat("", -1);
    }

    public String rawString() {
        return this.raw;
    }

    public String shortFormat(String str, int i) {
        return format(2, ',', str, i);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.raw);
    }

    public String fullFormat(String str, int i) {
        return format(-1, ',', str, i);
    }

    public String mediumFormat(String str, int i) {
        return format(5, ',', str, i);
    }

    public Value(BigDecimal bigDecimal) {
        this(bigDecimal.toString());
    }

    public Value(BigInteger bigInteger) {
        this(bigInteger.toString());
    }

    public Value(double d) {
        this(Double.toString(d));
    }

    public Value(long j) {
        this(Long.toString(j));
    }

    protected Value(Parcel parcel) {
        this.raw = parcel.readString();
    }
}
