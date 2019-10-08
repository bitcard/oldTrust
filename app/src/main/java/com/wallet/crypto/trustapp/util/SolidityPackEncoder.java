package com.wallet.crypto.trustapp.util;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.BytesType;
import org.web3j.abi.datatypes.NumericType;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Ufixed;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Hash;

public class SolidityPackEncoder {
    private SolidityPackEncoder() {
    }

    public static byte[] encodeAddress(Address address) {
        if (address != null) {
            return address.toUint160().getValue().toByteArray();
        }
        throw new IllegalArgumentException();
    }

    public static byte[] encodeBool(Bool bool) {
        if (bool != null) {
            return new byte[]{(byte) (bool.getValue().booleanValue() ? 1 : 0)};
        }
        throw new IllegalArgumentException();
    }

    public static byte[] encodeBytes(BytesType bytesType) {
        if (bytesType != null) {
            return bytesType.getValue();
        }
        throw new IllegalArgumentException();
    }

    public static byte[] encodeInt32(Int32 int32) {
        if (int32 != null) {
            byte[] toByteArray = toByteArray(int32);
            byte paddingValue = getPaddingValue(int32);
            byte[] bArr = new byte[4];
            if (paddingValue != (byte) 0) {
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = paddingValue;
                }
            }
            System.arraycopy(toByteArray, 0, bArr, 4 - toByteArray.length, toByteArray.length);
            return bArr;
        }
        throw new IllegalArgumentException();
    }

    public static byte[] encodeNumeric(NumericType numericType) {
        if (numericType != null) {
            byte[] toByteArray = toByteArray(numericType);
            byte paddingValue = getPaddingValue(numericType);
            byte[] bArr = new byte[32];
            if (paddingValue != (byte) 0) {
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = paddingValue;
                }
            }
            System.arraycopy(toByteArray, 0, bArr, 32 - toByteArray.length, toByteArray.length);
            return bArr;
        }
        throw new IllegalArgumentException();
    }

    public static byte[] encodeType(Type type) {
        if (type.getValue() == null) {
            return new byte[0];
        }
        if (type instanceof Utf8String) {
            return encodeUtf8String((Utf8String) type);
        }
        if (type instanceof Bool) {
            return encodeBool((Bool) type);
        }
        if (type instanceof Int32) {
            return encodeInt32((Int32) type);
        }
        if (type instanceof Uint32) {
            return encodeUInt32((Uint32) type);
        }
        if (type instanceof NumericType) {
            return encodeNumeric((NumericType) type);
        }
        if (type instanceof BytesType) {
            return encodeBytes((BytesType) type);
        }
        if (type instanceof Address) {
            return encodeAddress((Address) type);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Type cannot be encoded: ");
        stringBuilder.append(type.getClass());
        throw new UnsupportedOperationException(stringBuilder.toString());
    }

    public static byte[] encodeUInt32(Uint32 uint32) {
        return uint32.getValue().toByteArray();
    }

    public static byte[] encodeUtf8String(Utf8String utf8String) {
        if (utf8String != null) {
            return utf8String.getValue().getBytes();
        }
        throw new IllegalArgumentException();
    }

    private static byte getPaddingValue(NumericType numericType) {
        return numericType.getValue().signum() == -1 ? (byte) -1 : (byte) 0;
    }

    public static byte[] solidityPack(Type[] typeArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (Type encodeType : typeArr) {
            byte[] encodeType2 = encodeType(encodeType);
            byteArrayOutputStream.write(encodeType2, 0, encodeType2.length);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] soliditySHA3(Type[] typeArr) {
        return Hash.sha3(solidityPack(typeArr));
    }

    private static byte[] toByteArray(NumericType numericType) {
        BigInteger value = numericType.getValue();
        if ((!(numericType instanceof Ufixed) && !(numericType instanceof Uint)) || value.bitLength() != Type.MAX_BIT_LENGTH) {
            return value.toByteArray();
        }
        byte[] bArr = new byte[32];
        System.arraycopy(value.toByteArray(), 1, bArr, 0, 32);
        return bArr;
    }
}
