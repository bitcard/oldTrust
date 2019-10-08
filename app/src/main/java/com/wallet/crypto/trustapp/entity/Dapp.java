package com.wallet.crypto.trustapp.entity;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: Dapp.kt */
public final class Dapp {
    private final Slip coin;
    private final String description;
    private final String id;
    private final String image;
    private final String name;
    private final String pageImage;
    private final String url;

    public Dapp(String str, String str2, String str3, String str4, Slip slip, String str5, String str6) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(str3, "url");
        Intrinsics.checkParameterIsNotNull(str4, "description");
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        Intrinsics.checkParameterIsNotNull(str5, "image");
        Intrinsics.checkParameterIsNotNull(str6, "pageImage");
        this.id = str;
        this.name = str2;
        this.url = str3;
        this.description = str4;
        this.coin = slip;
        this.image = str5;
        this.pageImage = str6;
    }

    public static /* synthetic */ Dapp copy$default(Dapp dapp, String str, String str2, String str3, String str4, Slip slip, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dapp.id;
        }
        if ((i & 2) != 0) {
            str2 = dapp.name;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = dapp.url;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = dapp.description;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            slip = dapp.coin;
        }
        Slip slip2 = slip;
        if ((i & 32) != 0) {
            str5 = dapp.image;
        }
        String str10 = str5;
        if ((i & 64) != 0) {
            str6 = dapp.pageImage;
        }
        return dapp.copy(str, str7, str8, str9, slip2, str10, str6);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.url;
    }

    public final String component4() {
        return this.description;
    }

    public final Slip component5() {
        return this.coin;
    }

    public final String component6() {
        return this.image;
    }

    public final String component7() {
        return this.pageImage;
    }

    public final Dapp copy(String str, String str2, String str3, String str4, Slip slip, String str5, String str6) {
        String str7 = str;
        Intrinsics.checkParameterIsNotNull(str, "id");
        String str8 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "name");
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "url");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "description");
        Slip slip2 = slip;
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "image");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, "pageImage");
        return new Dapp(str7, str8, str9, str10, slip2, str11, str12);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Dapp) {
                Dapp dapp = (Dapp) obj;
                if (Intrinsics.areEqual(this.id, dapp.id) && Intrinsics.areEqual(this.name, dapp.name) && Intrinsics.areEqual(this.url, dapp.url) && Intrinsics.areEqual(this.description, dapp.description) && Intrinsics.areEqual(this.coin, dapp.coin) && Intrinsics.areEqual(this.image, dapp.image) && Intrinsics.areEqual(this.pageImage, dapp.pageImage)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final Slip getCoin() {
        return this.coin;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPageImage() {
        return this.pageImage;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.url;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.description;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Slip slip = this.coin;
        hashCode = (hashCode + (slip != null ? slip.hashCode() : 0)) * 31;
        str2 = this.image;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.pageImage;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dapp(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", url=");
        stringBuilder.append(this.url);
        stringBuilder.append(", description=");
        stringBuilder.append(this.description);
        stringBuilder.append(", coin=");
        stringBuilder.append(this.coin);
        stringBuilder.append(", image=");
        stringBuilder.append(this.image);
        stringBuilder.append(", pageImage=");
        stringBuilder.append(this.pageImage);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
