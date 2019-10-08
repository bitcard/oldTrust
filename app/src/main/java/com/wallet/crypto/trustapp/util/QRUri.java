package com.wallet.crypto.trustapp.util;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import trust.blockchain.Slip;
import trust.blockchain.entity.Address;

public class QRUri implements Parcelable {
    public static final Creator<QRUri> CREATOR = new C16221();
    /* renamed from: a */
    private final String f17082a;
    /* renamed from: b */
    private final Slip f17083b;
    /* renamed from: c */
    private final String f17084c;
    /* renamed from: d */
    private final Address f17085d;
    /* renamed from: e */
    private final Map<String, String> f17086e;
    /* renamed from: f */
    private Double f17087f;

    /* renamed from: com.wallet.crypto.trustapp.util.QRUri$1 */
    static class C16221 implements Creator<QRUri> {
        C16221() {
        }

        public QRUri createFromParcel(Parcel parcel) {
            return new QRUri(parcel);
        }

        public QRUri[] newArray(int i) {
            return new QRUri[i];
        }
    }

    private QRUri(Slip slip, String str, String str2, Address address, Map<String, String> map) {
        this.f17083b = slip;
        this.f17082a = str;
        this.f17084c = str2;
        this.f17085d = address;
        this.f17086e = Collections.unmodifiableMap(map);
    }

    public static QRUri parse(String str, Slip slip) {
        Address address;
        String str2;
        Throwable e;
        if (slip == null) {
            return new QRUri(null, str, null, null, Collections.emptyMap());
        }
        HashMap hashMap = new HashMap();
        Address address2 = null;
        String scheme;
        try {
            Uri parse = Uri.parse(str);
            scheme = parse.getScheme();
            try {
                Uri parse2 = Uri.parse(parse.getSchemeSpecificPart());
                String[] r4 = new String[4];
                int i = 0;
                r4[0] = parse2.getPath();
                r4[1] = parse.getHost();
                r4[2] = parse.getLastPathSegment();
                String queryParameter = parse.isHierarchical() ? parse.getQueryParameter(org.web3j.abi.datatypes.Address.TYPE_NAME) : parse2.isHierarchical() ? parse2.getQueryParameter(org.web3j.abi.datatypes.Address.TYPE_NAME) : null;
                r4[3] = queryParameter;
                while (i < r4.length && address2 == null) {
                    String charSequence = r4[i];
                    if (!TextUtils.isEmpty(charSequence) && slip.isValid(charSequence)) {
                        address2 = slip.toAddress(charSequence);
                    }
                    i++;
                }
                Set<String> queryParameterNames = parse.isHierarchical() ? parse.getQueryParameterNames() : parse2.isHierarchical() ? parse2.getQueryParameterNames() : Collections.emptySet();
                for (String str3 : queryParameterNames) {
                    if (!TextUtils.isEmpty(str3)) {
                        hashMap.put(str3, parse.isHierarchical() ? parse.getQueryParameter(str3) : parse2.getQueryParameter(str3));
                    }
                }
                address = address2;
                str2 = scheme;
            } catch (Exception e2) {
                e = e2;
                Log.d("TAG", "", e);
                address = null;
                str2 = scheme;
                return new QRUri(slip, str, str2, address, hashMap);
            }
        } catch (Exception e3) {
            e = e3;
            scheme = null;
            Log.d("TAG", "", e);
            address = null;
            str2 = scheme;
            return new QRUri(slip, str, str2, address, hashMap);
        }
        return new QRUri(slip, str, str2, address, hashMap);
    }

    public int describeContents() {
        return 0;
    }

    public Address getAddress() {
        return this.f17085d;
    }

    public Double getAmount() {
        if (f17087f != null) {
            return f17087f;
        }

        String value = null;
        for (String key : f17086e.keySet()) {
            if ("amount".equals(key) || "value".equals(key) || "a".equals(key) || "v".equals(key)) {
                value = f17086e.get(key);
            }
        }

        if (TextUtils.isEmpty(value)) {
            return f17087f;
        }

        String[] r0 = value.replace(",", ".").replace(" ", "").split("\\.");
        String r1 = new String();
        for (int i = 0; i < r0.length;) {
            r1 += r0[i];
            i++;
            if (i == r0.length - 1) {
                r1 += ".";
            }
        }

        try {
            f17087f = Double.valueOf(r1);
        } catch (Exception e) {
            try {
                f17087f = Double.valueOf(NumberFormat.getInstance().parse(r1).doubleValue());
            } catch (Exception e1) {

            }
        }
        return f17087f;
    }

    public String getParameter(String str) {
        return (String) this.f17086e.get(str.toLowerCase());
    }

    public String getUri() {
        return this.f17082a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Slip slip = this.f17083b;
        parcel.writeString(slip == null ? null : slip.name());
        parcel.writeString(this.f17082a);
        parcel.writeString(this.f17084c);
        Address address = this.f17085d;
        parcel.writeString(address == null ? "" : address.data());
        parcel.writeMap(new HashMap(this.f17086e));
    }

    protected QRUri(Parcel parcel) {
        Slip slip;
        String readString = parcel.readString();
        if (readString == null) {
            slip = null;
        } else {
            slip = Slip.valueOf(readString);
        }
        this.f17083b = slip;
        this.f17082a = parcel.readString();
        this.f17084c = parcel.readString();
        readString = parcel.readString();
        if (TextUtils.isEmpty(readString)) {
            this.f17085d = null;
        } else {
            this.f17085d = this.f17083b.toAddress(readString);
        }
        this.f17086e = Collections.unmodifiableMap(parcel.readHashMap(String.class.getClassLoader()));
    }
}
