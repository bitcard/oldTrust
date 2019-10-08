package com.wallet.crypto.trustapp.util;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.abi.datatypes.BytesType;
import org.web3j.abi.datatypes.Int;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.abi.datatypes.generated.Int64;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import trust.blockchain.entity.TypedData;

public class EIP712TypedData {
    /* renamed from: a */
    public final Map<String, List<Type>> f17071a;
    /* renamed from: b */
    public final String f17072b;
    /* renamed from: c */
    public final JSONObject f17073c;
    /* renamed from: d */
    public final JSONObject f17074d;
    /* renamed from: e */
    private final TypedData[] f17075e;

    public static class Type {
        /* renamed from: a */
        public final String f17069a;
        /* renamed from: b */
        public final String f17070b;

        public Type(String str, String str2) {
            this.f17069a = str;
            this.f17070b = str2;
        }
    }

    public EIP712TypedData(Map<String, List<Type>> map, String str, JSONObject jSONObject, JSONObject jSONObject2) {
        this.f17071a = new HashMap<>();
        this.f17071a.putAll(map);
        this.f17072b = str;
        this.f17073c = jSONObject;
        this.f17074d = jSONObject2;
        this.f17075e = null;
    }

    private Set<String> findDependencies(String str, Set<String> set) {
        List<Type> list = (List) this.f17071a.get(str);
        if (!set.contains(str)) {
            if (list != null) {
                set.add(str);
                for (Type type : list) {
                    findDependencies(type.f17070b, set);
                }
                return set;
            }
        }
        return set;
    }

    private String generateLabel(JSONObject jSONObject, String str) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\n");
        for (Type type : this.f17071a.get(str)) {
            if (this.f17071a.containsKey(type.f17070b) && jSONObject.has(type.f17069a)) {
                String generateLabel = generateLabel(jSONObject.getJSONObject(type.f17069a), type.f17070b);
                stringBuilder.append("\t");
                stringBuilder.append(generateLabel);
                stringBuilder.append("\n");
            } else {
                stringBuilder.append("\t");
                stringBuilder.append(type.f17069a);
                stringBuilder.append(": ");
                stringBuilder.append(jSONObject.getString(type.f17069a));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static org.web3j.abi.datatypes.Type makeABIValue(Object r3, String r4) throws UnsupportedEncodingException {
//        /*
//        r0 = "bytes";
//        r0 = r4.contains(r0);
        if (r4.contains("bytes")) {
//        r1 = 0;
//        if (r0 == 0) goto L_0x0063;
//    L_0x0009:
//        r3 = (java.lang.String) r3;
//        r0 = android.text.TextUtils.isEmpty(r3);
//        if (r0 == 0) goto L_0x0014;
            byte[] bytes;
            String strR3 = (String) r3;
            if (TextUtils.isEmpty(strR3)) {
//    L_0x0011:
//        r3 = new byte[r1];
//        goto L_0x0033;
                bytes = new byte[0];
            } else if (Numeric.containsHexPrefix(strR3)) {
                bytes = Numeric.hexStringToByteArray(strR3);
//    L_0x0014:
//        r0 = org.web3j.utils.Numeric.containsHexPrefix(r3);
//        if (r0 == 0) goto L_0x001f;
//    L_0x001a:
//        r3 = org.web3j.utils.Numeric.hexStringToByteArray(r3);
//        goto L_0x0033;
            } else if (TextUtils.isDigitsOnly(strR3)) {
                bytes = new BigInteger(strR3).toByteArray();
//    L_0x001f:
//        r0 = android.text.TextUtils.isDigitsOnly(r3);
//        if (r0 == 0) goto L_0x002f;
//    L_0x0025:
//        r0 = new java.math.BigInteger;
//        r0.<init>(r3);
//        r3 = r0.toByteArray();
//        goto L_0x0033;
            } else {
//    L_0x002f:
//        r3 = r3.getBytes();
                bytes = strR3.getBytes();
            }
//    L_0x0033:
//        r0 = "bytes";
//        r2 = "";
//        r0 = r4.replace(r0, r2);
//        r2 = android.text.TextUtils.isEmpty(r0);
            int r0 = 0;
            String strR0 = r4.replace("bytes", "");
            if (!TextUtils.isEmpty(strR0)) {
//        if (r2 != 0) goto L_0x004a;
//    L_0x0041:
//        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x004a }
//        r0 = r0.intValue();	 Catch:{ Exception -> 0x004a }
//        goto L_0x004b;
//    L_0x004a:
//        r0 = r1;
                r0 = Integer.valueOf(strR0).intValue();
            }

            if (r0 == 0) {
//    L_0x004b:
//        if (r0 != 0) goto L_0x0057;
//    L_0x004d:
//        r3 = org.web3j.crypto.Hash.sha3(r3);
//        r4 = new org.web3j.abi.datatypes.generated.Bytes32;
//        r4.<init>(r3);
//        return r4;
                return new Bytes32(Hash.sha3(bytes));
            } else {
//    L_0x0057:
//        r0 = new byte[r0];
//        r2 = r3.length;
//        java.lang.System.arraycopy(r3, r1, r0, r1, r2);
//        r3 = new org.web3j.abi.datatypes.BytesType;
//        r3.<init>(r0, r4);
//        return r3;
                byte[] temp = new byte[r0];
                System.arraycopy(bytes, 0, temp, 0, bytes.length);
                return new BytesType(temp, r4);
            }
        }

        switch (r4) {
            case "int256":
//            case -1183813628: goto L_0x00e1;
//    L_0x00e1:
//        r2 = "int256";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00e9:
//        r4 = 10;
//        goto L_0x00ed;
//            case 10: goto L_0x0105;
//    L_0x0105:
//        r4 = new org.web3j.abi.datatypes.generated.Int256;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Int256(new BigInteger(String.valueOf(r3)));
            case "address":
//            case -1147692044: goto L_0x00d7;
//    L_0x00d7:
//        r2 = "address";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00df:
//        r4 = 5;
//        goto L_0x00ed;
//            case 5: goto L_0x0150;
//    L_0x0150:
//        r3 = (java.lang.String) r3;
//        r3 = org.web3j.utils.Numeric.toBigInt(r3);
//        r3 = r3.toByteArray();
//        r4 = r3.length;
//        r4 = r4 + 31;
//        r0 = 32;
//        r4 = r4 / r0;
//        r4 = r4 * r0;
//        r2 = r3.length;
//        r4 = r4 - r2;
//        r0 = new byte[r0];
//        java.util.Arrays.fill(r0, r1);
//        r2 = r3.length;
//        java.lang.System.arraycopy(r3, r1, r0, r4, r2);
//        r3 = new org.web3j.abi.datatypes.generated.Bytes32;
//        r3.<init>(r0);
//        return r3;
                byte[] input = Numeric.toBigInt((String)r3).toByteArray();
                int size = (input.length + 31) / 32 * 32 - input.length;
                byte[] temp = new byte[32];
                Arrays.fill(temp, (byte)0);
                System.arraycopy(input, 0, temp, size, input.length);
                return new Bytes32(temp);
            case "string":
//            case -891985903: goto L_0x00cd;
//    L_0x00cd:
//        r2 = "string";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00d5:
//        r4 = r1;
//        goto L_0x00ed;
//            case 0: goto L_0x01b2;
//    L_0x01b2:
//        r3 = (java.lang.String) r3;
//        r4 = "UTF-8";
//        r3 = r3.getBytes(r4);
//        r4 = new org.web3j.abi.datatypes.generated.Bytes32;
//        r3 = org.web3j.crypto.Hash.sha3(r3);
//        r4.<init>(r3);
//        return r4;
//        */
                return new Bytes32(Hash.sha3(((String)r3).getBytes("UTF-8")));
            case "uint32":
//            case -844996807: goto L_0x00c3;
//    L_0x00c3:
//        r2 = "uint32";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00cb:
//        r4 = 7;
//        goto L_0x00ed;
//            case 7: goto L_0x0132;
//    L_0x0132:
//        r4 = new org.web3j.abi.datatypes.generated.Uint32;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Uint32(new BigInteger(String.valueOf(r3)));
            case "uint64":
//            case -844996712: goto L_0x00b8;
//    L_0x00b8:
//        r2 = "uint64";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00c0:
//        r4 = 8;
//        goto L_0x00ed;
//            case 8: goto L_0x0123;
//    L_0x0123:
//        r4 = new org.web3j.abi.datatypes.generated.Uint64;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Uint64(new BigInteger(String.valueOf(r3)));
            case "uint256":
//            case -425098055: goto L_0x00ad;
//    L_0x00ad:
//        r2 = "uint256";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00b5:
//        r4 = 11;
//        goto L_0x00ed;
//            case 11: goto L_0x00f6;
//    L_0x00f6:
//        r4 = new org.web3j.abi.datatypes.generated.Uint256;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Uint256(new BigInteger(String.valueOf(r3)));
            case "int":
//            case 104431: goto L_0x00a3;
//    L_0x00a3:
//        r2 = "int";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00ab:
//        r4 = 2;
//        goto L_0x00ed;
//            case 2: goto L_0x0194;
//    L_0x0194:
//        r4 = new org.web3j.abi.datatypes.Int;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Int(new BigInteger(String.valueOf(r3)));
            case "bool":
//            case 3029738: goto L_0x0099;
//    L_0x0099:
//        r2 = "bool";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x00a1:
//        r4 = 3;
//        goto L_0x00ed;
//            case 3: goto L_0x0181;
//    L_0x0181:
//        r4 = new org.web3j.abi.datatypes.generated.Uint256;
//        r3 = (java.lang.Boolean) r3;
//        r3 = r3.booleanValue();
//        if (r3 == 0) goto L_0x018e;
//    L_0x018b:
//        r0 = 1;
//        goto L_0x0190;
//    L_0x018e:
//        r0 = 0;
//    L_0x0190:
//        r4.<init>(r0);
//        return r4;
                return new Uint256(((Boolean)r3).booleanValue() ? 1 : 0);
            case "uint":
//            case 3589978: goto L_0x008f;
//    L_0x008f:
//        r2 = "uint";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x0097:
//        r4 = 1;
//        goto L_0x00ed;
//            case 1: goto L_0x01a3;
//    L_0x01a3:
//        r4 = new org.web3j.abi.datatypes.Uint;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Uint(new BigInteger(String.valueOf(r3)));
            case "int32":
//            case 100359822: goto L_0x0084;
//    L_0x0084:
//        r2 = "int32";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x008c:
//        r4 = 6;
//        goto L_0x00ed;
//            case 6: goto L_0x0141;
//    L_0x0141:
//        r4 = new org.web3j.abi.datatypes.generated.Int32;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Int32(new BigInteger(String.valueOf(r3)));
            case "int64":
//            case 100359917: goto L_0x0078;
//    L_0x0078:
//        r2 = "int64";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x0080:
//        r4 = 9;
//        goto L_0x00ed;
//            case 9: goto L_0x0114;
//    L_0x0114:
//        r4 = new org.web3j.abi.datatypes.generated.Int64;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Int64(new BigInteger(String.valueOf(r3)));
            case "uint8":
//            case 111289374: goto L_0x006d;
//    L_0x006d:
//        r2 = "uint8";
//        r4 = r4.equals(r2);
//        if (r4 == 0) goto L_0x00ec;
//    L_0x0075:
//        r4 = 4;
//        goto L_0x00ed;
//            case 4: goto L_0x0172;
//    L_0x0172:
//        r4 = new org.web3j.abi.datatypes.generated.Uint8;
//        r0 = new java.math.BigInteger;
//        r3 = java.lang.String.valueOf(r3);
//        r0.<init>(r3);
//        r4.<init>(r0);
//        return r4;
                return new Uint8(new BigInteger(String.valueOf(r3)));
            default:
//    L_0x0063:
//        r0 = -1;
//        r2 = r4.hashCode();
//        switch(r2) {
//            default: goto L_0x006b;
//        };
//    L_0x006b:
//        goto L_0x00ec;
//    L_0x00ec:
//        r4 = r0;

//    L_0x00ed:
//        switch(r4) {
//            default: goto L_0x00f0;
//        };
//    L_0x00f0:
//        r3 = new java.lang.IllegalArgumentException;
//        r3.<init>();
//        throw r3;
                throw new IllegalArgumentException();
        }
    }

    public static EIP712TypedData parse(String str) throws JSONException {
        JSONArray jSONArray;
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            jSONArray = null;
            jSONObject = jSONObject2;
        } catch (JSONException unused) {
            jSONArray = new JSONArray(str);
        }
        if (jSONObject == null) {
            return parseTypedData(jSONArray);
        }
        return parseDomainSeparator(jSONObject);
    }

    private static EIP712TypedData parseDomainSeparator(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("primaryType");
        JSONObject jSONObject2 = jSONObject.getJSONObject("types");
        Iterator keys = jSONObject2.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONArray jSONArray = jSONObject2.getJSONArray(str);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                arrayList.add(new Type(jSONObject3.getString("name"), jSONObject3.getString("type")));
            }
            hashMap.put(str, arrayList);
        }
        return new EIP712TypedData(hashMap, string, jSONObject.getJSONObject("domain"), jSONObject.getJSONObject("message"));
    }

    private static EIP712TypedData parseTypedData(JSONArray jSONArray) throws JSONException {
        int length = jSONArray.length();
        TypedData[] typedDataArr = new TypedData[length];
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            typedDataArr[i] = new TypedData(jSONObject.getString("name"), jSONObject.getString("type"), jSONObject.get("value"));
        }
        return new EIP712TypedData(typedDataArr);
    }

    public byte[] encodeData(JSONObject jSONObject, String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Bytes32(Hash.sha3(encodeType(str))));
        for (Type type : this.f17071a.get(str)) {
            if (this.f17071a.containsKey(type.f17070b) && jSONObject.has(type.f17069a)) {
                arrayList.add(new Bytes32(Hash.sha3(encodeData(jSONObject.getJSONObject(type.f17069a), type.f17070b))));
            } else {
                arrayList.add(makeABIValue(jSONObject.get(type.f17069a), type.f17070b));
            }
        }
        return SolidityPackEncoder.solidityPack((org.web3j.abi.datatypes.Type[]) arrayList.toArray(new org.web3j.abi.datatypes.Type[0]));
    }

    public byte[] encodeType(String str) throws UnsupportedEncodingException {
        HashSet hashSet = new HashSet();
        findDependencies(str, hashSet);
        ArrayList<String> arrayList = new ArrayList(hashSet);
        arrayList.remove(str);
        Collections.sort(arrayList);
        arrayList.add(0, str);
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : arrayList) {
            ArrayList arrayList3 = new ArrayList();
            for (Type type : this.f17071a.get(str2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(type.f17070b);
                stringBuilder.append(" ");
                stringBuilder.append(type.f17069a);
                arrayList3.add(stringBuilder.toString());
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str2);
            stringBuilder2.append("(");
            stringBuilder2.append(TextUtils.join(",", arrayList3));
            stringBuilder2.append(")");
            arrayList2.add(stringBuilder2.toString());
        }
        return TextUtils.join("", arrayList2).getBytes("UTF-8");
    }

    public boolean hasDomainSeparator() {
        return !TextUtils.isEmpty(this.f17072b);
    }

    public byte[] signHash() throws Exception {
        if (!hasDomainSeparator()) {
            return TypedDataSignature.generateSignatureHash(this.f17075e);
        }
        byte[] sha3 = Hash.sha3(encodeData(this.f17073c, "EIP712Domain"));
        byte[] sha32 = Hash.sha3(encodeData(this.f17074d, this.f17072b));
        byte[] bArr = new byte[((sha3.length + sha32.length) + 2)];
        bArr[0] = (byte) 25;
        bArr[1] = (byte) 1;
        System.arraycopy(sha3, 0, bArr, 2, sha3.length);
        System.arraycopy(sha32, 0, bArr, sha3.length + 2, sha32.length);
        return Hash.sha3(bArr);
    }

    public String toString() {
        if (!hasDomainSeparator()) {
            return generateLabel(this.f17075e);
        }
        try {
            return generateLabel(this.f17074d, this.f17072b);
        } catch (JSONException unused) {
            return "";
        }
    }

    public EIP712TypedData(TypedData[] typedDataArr) {
        this.f17071a = new HashMap();
        this.f17072b = null;
        this.f17073c = null;
        this.f17074d = null;
        this.f17075e = typedDataArr;
    }

    private String generateLabel(TypedData[] typedDataArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TypedData typedData : typedDataArr) {
            stringBuilder.append("\n");
            stringBuilder.append(typedData.name);
            stringBuilder.append("=");
            stringBuilder.append(typedData.data);
        }
        return stringBuilder.toString();
    }
}
