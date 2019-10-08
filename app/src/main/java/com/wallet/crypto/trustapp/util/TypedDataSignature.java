package com.wallet.crypto.trustapp.util;

import android.text.TextUtils;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Int;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import trust.blockchain.entity.TypedData;

public abstract class TypedDataSignature {
    private static Type[] buildSchema(TypedData[] typedDataArr) {
        int length = typedDataArr.length;
        Type[] typeArr = new Type[length];
        for (int i = 0; i < length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(typedDataArr[i].type);
            stringBuilder.append(" ");
            stringBuilder.append(typedDataArr[i].name);
            typeArr[i] = new Utf8String(stringBuilder.toString());
        }
        return typeArr;
    }

    private static Type[] buildTypes(TypedData[] r8) {
        int len = r8.length;
        Type[] r1 = new Type[len];
//        /*
//        r0 = r8.length;
//        r1 = new org.web3j.abi.datatypes.Type[r0];
//        r2 = 0;
//        r3 = r2;
        for (int i = 0; i < len; i++) {
            TypedData typedData = r8[i];
//    L_0x0005:
//        if (r3 >= r0) goto L_0x017d;
//    L_0x0007:
//        r4 = r8[r3];
//        r5 = r4.type;
//        r6 = -1;
//        r7 = r5.hashCode();
//        switch(r7) {
            switch (typedData.type) {
                case "int256":
//            case -1183813628: goto L_0x007d;
//    L_0x007d:
//        r7 = "int256";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0085:
//        r5 = 9;
//        goto L_0x0089;
//            case 9: goto L_0x00a6;
//    L_0x00a6:
//        r5 = new org.web3j.abi.datatypes.generated.Int256;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Int256(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "address":
//            case -1147692044: goto L_0x0073;
//    L_0x0073:
//        r7 = "address";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x007b:
//        r5 = 6;
//        goto L_0x0089;
//            case 6: goto L_0x00e2;
//    L_0x00e2:
//        r5 = new org.web3j.abi.datatypes.Address;
//        r4 = r4.data;
//        r4 = (java.lang.String) r4;
//        r5.<init>(r4);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Address((String)typedData.data);
                    break;
                case "string":
//            case -891985903: goto L_0x0069;
//    L_0x0069:
//        r7 = "string";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0071:
//        r5 = r2;
//        goto L_0x0089;
//            case 0: goto L_0x016e;
//    L_0x016e:
//        r5 = new org.web3j.abi.datatypes.Utf8String;
//        r4 = r4.data;
//        r4 = (java.lang.String) r4;
//        r5.<init>(r4);
//        r1[r3] = r5;
                    r1[i] = new Utf8String((String)typedData.data);
                    break;
                case "uint32":
//            case -844996807: goto L_0x005e;
//    L_0x005e:
//        r7 = "uint32";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0066:
//        r5 = 8;
//        goto L_0x0089;
//            case 8: goto L_0x00ba;
//    L_0x00ba:
//        r5 = new org.web3j.abi.datatypes.generated.Uint32;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Uint32(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "uint256":
//            case -425098055: goto L_0x0053;
//    L_0x0053:
//        r7 = "uint256";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x005b:
//        r5 = 10;
//        goto L_0x0089;
//            case 10: goto L_0x0092;
//    L_0x0092:
//        r5 = new org.web3j.abi.datatypes.generated.Uint256;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Uint256(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "int":
//            case 104431: goto L_0x0049;
//    L_0x0049:
//        r7 = "int";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0051:
//        r5 = 3;
//        goto L_0x0089;
//            case 3: goto L_0x0125;
//    L_0x0125:
//        r5 = new org.web3j.abi.datatypes.Int;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Int(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "bool":
//            case 3029738: goto L_0x003f;
//    L_0x003f:
//        r7 = "bool";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0047:
//        r5 = 4;
//        goto L_0x0089;
//            case 4: goto L_0x0103;
//    L_0x0103:
//        r5 = new org.web3j.abi.datatypes.Bool;
//        r4 = r4.data;
//        r4 = (java.lang.Boolean) r4;
//        r4 = r4.booleanValue();
//        r5.<init>(r4);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Bool(((Boolean)typedData.data).booleanValue());
                    break;
                case "uint":
//            case 3589978: goto L_0x0035;
//    L_0x0035:
//        r7 = "uint";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x003d:
//        r5 = 2;
//        goto L_0x0089;
//            case 2: goto L_0x0113;
//    L_0x0113:
//        r5 = new org.web3j.abi.datatypes.Uint;
//        r6 = new java.math.BigInteger;
//        r7 = r4.data;
//        r7 = java.lang.String.valueOf(r7);
//        r6.<init>(r7);
//        r5.<init>(r6);
//        r1[r3] = r5;
                    r1[i] = new Uint(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "bytes":
//            case 94224491: goto L_0x002b;
//    L_0x002b:
//        r7 = "bytes";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0033:
//        r5 = 1;
//        goto L_0x0089;
//            case 1: goto L_0x0138;
//    L_0x0138:
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r5 = android.text.TextUtils.isEmpty(r4);
//        if (r5 == 0) goto L_0x0147;
//    L_0x0144:
//        r4 = new byte[r2];
//        goto L_0x0166;
//    L_0x0147:
//        r5 = org.web3j.utils.Numeric.containsHexPrefix(r4);
//        if (r5 == 0) goto L_0x0152;
//    L_0x014d:
//        r4 = org.web3j.utils.Numeric.hexStringToByteArray(r4);
//        goto L_0x0166;
//    L_0x0152:
//        r5 = android.text.TextUtils.isDigitsOnly(r4);
//        if (r5 == 0) goto L_0x0162;
//    L_0x0158:
//        r5 = new java.math.BigInteger;
//        r5.<init>(r4);
//        r4 = r5.toByteArray();
//        goto L_0x0166;
//    L_0x0162:
//        r4 = r4.getBytes();
//    L_0x0166:
//        r5 = new org.web3j.abi.datatypes.DynamicBytes;
//        r5.<init>(r4);
//        r1[r3] = r5;
//        goto L_0x0179;

                    byte[] r4;
                    String temp = String.valueOf(typedData.data);
                    if (TextUtils.isEmpty(temp)) {
                        r4 = new byte[0];
                    } else if (Numeric.containsHexPrefix(temp)){
                        r4 = Numeric.hexStringToByteArray(temp);
                    } else if (TextUtils.isDigitsOnly(temp)) {
                        r4 = new BigInteger(temp).toByteArray();
                    } else {
                        r4 = temp.getBytes();
                    }
                    r1[i] = new DynamicBytes(r4);
                    break;
                case "int32":
//            case 100359822: goto L_0x0020;
//    L_0x0020:
//        r7 = "int32";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x0028:
//        r5 = 7;
//        goto L_0x0089;
//            case 7: goto L_0x00ce;
//    L_0x00ce:
//        r5 = new org.web3j.abi.datatypes.generated.Int32;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Int32(new BigInteger(String.valueOf(typedData.data)));
                    break;
                case "uint8":
//            case 111289374: goto L_0x0015;
//    L_0x0015:
//        r7 = "uint8";
//        r5 = r5.equals(r7);
//        if (r5 == 0) goto L_0x0088;
//    L_0x001d:
//        r5 = 5;
//        goto L_0x0089;
//            case 5: goto L_0x00ef;
//    L_0x00ef:
//        r5 = new org.web3j.abi.datatypes.generated.Uint8;
//        r6 = new java.math.BigInteger;
//        r4 = r4.data;
//        r4 = java.lang.String.valueOf(r4);
//        r6.<init>(r4);
//        r5.<init>(r6);
//        r1[r3] = r5;
//        goto L_0x0179;
                    r1[i] = new Uint8(new BigInteger(String.valueOf(typedData.data)));
                    break;
                default:
                    throw new IllegalArgumentException();

            }
//            default: goto L_0x0013;
//        };
//    L_0x0013:
//        goto L_0x0088;

//    L_0x0088:
//        r5 = r6;
//    L_0x0089:
//        switch(r5) {
//            default: goto L_0x008c;
//        };
//    L_0x008c:
//        r8 = new java.lang.IllegalArgumentException;
//        r8.<init>();
//        throw r8;
//    L_0x0179:
//        r3 = r3 + 1;
//        goto L_0x0005;
        }
//    L_0x017d:
//        return r1;
//        */
        return r1;
    }

    public static byte[] generateSignatureHash(TypedData[] typedDataArr) {
        byte[] soliditySHA3 = SolidityPackEncoder.soliditySHA3(buildSchema(typedDataArr));
        byte[] soliditySHA32 = SolidityPackEncoder.soliditySHA3(buildTypes(typedDataArr));
        return SolidityPackEncoder.soliditySHA3(new Bytes32[]{new Bytes32(soliditySHA3), new Bytes32(soliditySHA32)});
    }
}
