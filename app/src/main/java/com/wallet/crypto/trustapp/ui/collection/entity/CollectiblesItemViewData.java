package com.wallet.crypto.trustapp.ui.collection.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesItemViewData.kt */
public final class CollectiblesItemViewData {
    /* renamed from: a */
    private final String f16797a;
    /* renamed from: b */
    private final String f16798b;
    /* renamed from: c */
    private final String f16799c;
    /* renamed from: d */
    private final String f16800d;
    /* renamed from: e */
    private final String f16801e;
    /* renamed from: f */
    private final String f16802f;

    public CollectiblesItemViewData(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        Intrinsics.checkParameterIsNotNull(str4, "externalLink");
        Intrinsics.checkParameterIsNotNull(str5, "openSeaLink");
        Intrinsics.checkParameterIsNotNull(str6, "coverLink");
        this.f16797a = str;
        this.f16798b = str2;
        this.f16799c = str3;
        this.f16800d = str4;
        this.f16801e = str5;
        this.f16802f = str6;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CollectiblesItemViewData) {
                CollectiblesItemViewData collectiblesItemViewData = (CollectiblesItemViewData) obj;
                if (Intrinsics.areEqual(this.f16797a, collectiblesItemViewData.f16797a) && Intrinsics.areEqual(this.f16798b, collectiblesItemViewData.f16798b) && Intrinsics.areEqual(this.f16799c, collectiblesItemViewData.f16799c) && Intrinsics.areEqual(this.f16800d, collectiblesItemViewData.f16800d) && Intrinsics.areEqual(this.f16801e, collectiblesItemViewData.f16801e) && Intrinsics.areEqual(this.f16802f, collectiblesItemViewData.f16802f)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getCoverLink() {
        return this.f16802f;
    }

    public final String getDescription() {
        return this.f16798b;
    }

    public final String getExternalLink() {
        return this.f16800d;
    }

    public final String getExternalLinkTitle() {
        return this.f16799c;
    }

    public final boolean getHasDescription() {
        CharSequence charSequence = this.f16798b;
        if (charSequence != null) {
            if (charSequence.length() != 0) {
                return true;
            }
        }
        return false;
    }

    public final String getOpenSeaLink() {
        return this.f16801e;
    }

    public final String getTitle() {
        return this.f16797a;
    }

    public int hashCode() {
        String str = this.f16797a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f16798b;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.f16799c;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.f16800d;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.f16801e;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.f16802f;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CollectiblesItemViewData(title=");
        stringBuilder.append(this.f16797a);
        stringBuilder.append(", description=");
        stringBuilder.append(this.f16798b);
        stringBuilder.append(", externalLinkTitle=");
        stringBuilder.append(this.f16799c);
        stringBuilder.append(", externalLink=");
        stringBuilder.append(this.f16800d);
        stringBuilder.append(", openSeaLink=");
        stringBuilder.append(this.f16801e);
        stringBuilder.append(", coverLink=");
        stringBuilder.append(this.f16802f);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
