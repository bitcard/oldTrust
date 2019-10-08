package com.wallet.crypto.trustapp.ui.receive.view;

import android.net.Uri;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiveViewData.kt */
public final class ReceiveViewData {
    /* renamed from: a */
    private String title;
    /* renamed from: b */
    private String coinName;
    /* renamed from: c */
    private String symbol;
    /* renamed from: d */
    private String address;
    /* renamed from: e */
    private boolean isWatch;
    /* renamed from: f */
    private Uri qrUri;
    /* renamed from: g */
    private double amount;
    /* renamed from: h */
    private String qrData;

    public ReceiveViewData(String str, String str2, String str3, String str4, boolean z, Uri uri, double d, String str5) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        Intrinsics.checkParameterIsNotNull(str2, "coinName");
        Intrinsics.checkParameterIsNotNull(str3, "symbol");
        Intrinsics.checkParameterIsNotNull(str4, "address");
        Intrinsics.checkParameterIsNotNull(str5, "qrData");
        this.title = str;
        this.coinName = str2;
        this.symbol = str3;
        this.address = str4;
        this.isWatch = z;
        this.qrUri = uri;
        this.amount = d;
        this.qrData = str5;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ReceiveViewData) {
                ReceiveViewData receiveViewData = (ReceiveViewData) obj;
                if (Intrinsics.areEqual(this.title, receiveViewData.title) && Intrinsics.areEqual(this.coinName, receiveViewData.coinName) && Intrinsics.areEqual(this.symbol, receiveViewData.symbol) && Intrinsics.areEqual(this.address, receiveViewData.address)) {
                    if ((this.isWatch == receiveViewData.isWatch) && Intrinsics.areEqual(this.qrUri, receiveViewData.qrUri) && Double.compare(this.amount, receiveViewData.amount) == 0 && Intrinsics.areEqual(this.qrData, receiveViewData.qrData)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getAddress() {
        return this.address;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final String getQrData() {
        return this.qrData;
    }

    public final Uri getQrUri() {
        return this.qrUri;
    }

    public final String getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        String str = this.title;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.coinName;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.symbol;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.address;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        int i2 = this.isWatch ? 1 : 0;
        hashCode = (hashCode + i2) * 31;
        Uri uri = this.qrUri;
        hashCode = (hashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.amount);
        hashCode = (hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        str2 = this.qrData;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public final boolean isWatch() {
        return this.isWatch;
    }

    public final void setQrUri(Uri uri) {
        this.qrUri = uri;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ReceiveViewData(title=");
        stringBuilder.append(this.title);
        stringBuilder.append(", coinName=");
        stringBuilder.append(this.coinName);
        stringBuilder.append(", symbol=");
        stringBuilder.append(this.symbol);
        stringBuilder.append(", address=");
        stringBuilder.append(this.address);
        stringBuilder.append(", isWatch=");
        stringBuilder.append(this.isWatch);
        stringBuilder.append(", qrUri=");
        stringBuilder.append(this.qrUri);
        stringBuilder.append(", amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(", qrData=");
        stringBuilder.append(this.qrData);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
