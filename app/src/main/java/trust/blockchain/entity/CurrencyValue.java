package trust.blockchain.entity;

import android.os.Parcel;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyValue extends SubunitValue {
    private boolean isShowSymbol = true;
    public final Ticker ticker;

    public CurrencyValue(SubunitValue subunitValue, Ticker ticker) {
        super(subunitValue.rawString(), subunitValue.unit);
        this.ticker = ticker;
        super.setShowSymbol(false);
    }

    public static String formatCurrency(String str, String str2, String str3) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
        try {
            Currency instance = Currency.getInstance(str2);
            Number parse = decimalFormat.parse(str);
            DecimalFormat decimalFormat2 = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.getDefault());
            decimalFormat2.setMaximumFractionDigits(Integer.MAX_VALUE);
            decimalFormat2.setCurrency(instance);
            str = decimalFormat2.format(parse);
            return str;
        } catch (Exception unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
    }

    public BigDecimal convert() {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        Ticker ticker = this.ticker;
        if (ticker != null) {
            try {
                bigDecimal = new BigDecimal(ticker.getPrice());
            } catch (NumberFormatException unused) {
            }
        }
        return super.convert().multiply(bigDecimal);
    }

    public String format(int i, char c, String str, int i2) {
        BigDecimal stripTrailingZeros = convert().stripTrailingZeros();
        int length = stripTrailingZeros.stripTrailingZeros().abs().toPlainString().substring(stripTrailingZeros.toBigInteger().abs().toString().length()).length() - 1;
        i = Math.min(i, length);
        while (stripTrailingZeros.setScale(i, RoundingMode.DOWN).compareTo(BigDecimal.ZERO) == 0) {
            if (i == length) {
                break;
            }
            i = Math.min(i + 4, length);
            if (length < i) {
                i = length;
            }
        }
        String format = super.format(i, c, str, i2);
        if (!this.isShowSymbol || format == null || format.equals(str)) {
            return format;
        }
        Ticker ticker = this.ticker;
        return (ticker == null || TextUtils.isEmpty(ticker.getSymbol())) ? format : formatCurrency(format, this.ticker.getCurrencyCode(), this.ticker.getSymbol());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.ticker, i);
    }

    public CurrencyValue setShowSymbol(boolean z) {
        this.isShowSymbol = z;
        return this;
    }
}
