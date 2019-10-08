package trust.blockchain.util;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.spongycastle.util.encoders.Hex;
import org.web3j.utils.Numeric;

/* compiled from: Numbers.kt */
public final class Numbers {
    public static final Numbers INSTANCE = new Numbers();

    private Numbers() {
    }

    public static final BigDecimal hexToBigDecimal(String str, BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "def");
        Numbers numbers = INSTANCE;
        BigInteger toBigInteger = bigDecimal.toBigInteger();
        Intrinsics.checkExpressionValueIsNotNull(toBigInteger, "def.toBigInteger()");
        return new BigDecimal(numbers.hexToBigInteger(str, toBigInteger));
    }

    public static final byte[] hexToByteArray(String str) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        return TextUtils.isEmpty((CharSequence) str) ? null : Numeric.hexStringToByteArray(str);
    }

    public static final BigDecimal toBigDecimal(String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        try {
            return new BigDecimal(str);
        } catch (Throwable unused) {
            BigDecimal bigDecimal = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "BigDecimal.ZERO");
            return bigDecimal;
        }
    }

    public final String cleanHexPrefix(String str) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        if (!containsHexPrefix(str)) {
            return str;
        }
        str = str.substring(2);
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
        return str;
    }

    public final boolean containsHexPrefix(String input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        return input.length() > 1 && input.charAt(0) == '0' && input.charAt(1) == 'x';
    }

    public final byte[] hexStringToByteArray(String str) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        str = cleanHexPrefix(str);
        int length = str.length();
        int i = 0;
        if (length == 0) {
            return new byte[0];
        }
        byte[] bArr;
        if (length % 2 != 0) {
            bArr = new byte[((length / 2) + 1)];
            bArr[0] = (byte) Character.digit(str.charAt(0), 16);
            i = 1;
        } else {
            bArr = new byte[(length / 2)];
        }
        while (i < length) {
            int i2 = i + 1;
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i2), 16));
            i += 2;
        }
        return bArr;
    }

    public final BigInteger hexToBigInteger(String r4) {
        Intrinsics.checkParameterIsNotNull(r4, "hex");
        if (TextUtils.isEmpty(r4)) {
            return null;
        }

        try {
            boolean r0 = containsHexPrefix(r4);
            if (r0) {
                r4 = cleanHexPrefix(r4);
            }
            return new BigInteger(r4, r0 ? 16 : 10);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public final byte[] reverseBytes(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "value");
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[(length - i) - 1] = bArr[i];
        }
        return bArr2;
    }

    public final String toHexString(byte[] bArr, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(bArr, "input");
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            stringBuilder.append("0x");
        }
        i2 += i;
        while (i < i2) {
            Object[] objArr = new Object[]{Byte.valueOf((byte) (bArr[i] & ((byte) 255)))};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            stringBuilder.append(format);
            i++;
        }
        String stringBuilder2 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder2, "stringBuilder.toString()");
        return stringBuilder2;
    }

    public final String toHexStringNoPrefix(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "input");
        return toHexString(bArr, 0, bArr.length, false);
    }

    public final String toHexStringWithPrefix(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "input");
        return toHexString(bArr, 0, bArr.length, true);
    }

    public final String reverseBytes(String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        byte[] decode = Hex.decode(str);
        Intrinsics.checkExpressionValueIsNotNull(decode, "bytes");
        str = Hex.toHexString(reverseBytes(decode));
        Intrinsics.checkExpressionValueIsNotNull(str, "Hex.toHexString(res)");
        return str;
    }

    public final BigInteger hexToBigInteger(String str, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(str, "input");
        Intrinsics.checkParameterIsNotNull(bigInteger, "def");
        BigInteger hexToBigInteger = hexToBigInteger(str);
        return hexToBigInteger != null ? hexToBigInteger : bigInteger;
    }
}
