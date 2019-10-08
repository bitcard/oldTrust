package com.wallet.crypto.trustapp.util;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.utils.Numeric;

public class Numbers {
    private static String cleanHexPrefix(String str) {
        return str.substring(2);
    }

    private static boolean containsHexPrefix(String str) {
        return str.length() > 1 && str.charAt(0) == '0' && str.charAt(1) == 'x';
    }

    public static BigInteger hexToBigInteger(String r3) {
        if (TextUtils.isEmpty(r3)) {
            return null;
        }

        try {
            boolean r0 = containsHexPrefix(r3);
            if (r0) {
                r3 = cleanHexPrefix(r3);
            }
            return new BigInteger(r3, r0 ? 16 : 10);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static byte[] hexToByteArray(String str) {
        return TextUtils.isEmpty(str) ? null : Numeric.hexStringToByteArray(str);
    }

    public static String hexToDecimal(String str) {
        BigInteger hexToBigInteger = hexToBigInteger(str);
        if (hexToBigInteger == null) {
            return null;
        }
        return hexToBigInteger.toString(10);
    }

    public static Integer hexToInteger(String str) {
        try {
            return Integer.decode(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static BigDecimal parse(String str) {
        String[] split = str.replace(",", ".").replace(" ", "").split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < split.length) {
            stringBuilder.append(split[i]);
            i++;
            if (i == split.length - 1) {
                stringBuilder.append(".");
            }
        }
        try {
            return new BigDecimal(stringBuilder.toString());
        } catch (Exception unused) {
            return null;
        }
    }

    public static Integer hexToInteger(String str, int i) {
        Integer hexToInteger = hexToInteger(str);
        if (hexToInteger != null) {
            i = hexToInteger.intValue();
        }
        return Integer.valueOf(i);
    }

    public static BigInteger hexToBigInteger(String str, BigInteger bigInteger) {
        BigInteger hexToBigInteger = hexToBigInteger(str);
        return hexToBigInteger == null ? bigInteger : hexToBigInteger;
    }
}
