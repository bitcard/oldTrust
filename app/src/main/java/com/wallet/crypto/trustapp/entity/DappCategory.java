package com.wallet.crypto.trustapp.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappCategory.kt */
public final class DappCategory {
    private final String id;
    private final Dapp[] items;
    private final int limit;
    private final String name;
    private final int order;
    private final String slug;

    public DappCategory(String str, String str2, int i, int i2, String str3, Dapp[] dappArr) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(str3, "slug");
        Intrinsics.checkParameterIsNotNull(dappArr, "items");
        this.id = str;
        this.name = str2;
        this.order = i;
        this.limit = i2;
        this.slug = str3;
        this.items = dappArr;
    }

    public static /* synthetic */ DappCategory copy$default(DappCategory dappCategory, String str, String str2, int i, int i2, String str3, Dapp[] dappArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = dappCategory.id;
        }
        if ((i3 & 2) != 0) {
            str2 = dappCategory.name;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            i = dappCategory.order;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = dappCategory.limit;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            str3 = dappCategory.slug;
        }
        String str5 = str3;
        if ((i3 & 32) != 0) {
            dappArr = dappCategory.items;
        }
        return dappCategory.copy(str, str4, i4, i5, str5, dappArr);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final int component3() {
        return this.order;
    }

    public final int component4() {
        return this.limit;
    }

    public final String component5() {
        return this.slug;
    }

    public final Dapp[] component6() {
        return this.items;
    }

    public final DappCategory copy(String str, String str2, int i, int i2, String str3, Dapp[] dappArr) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Intrinsics.checkParameterIsNotNull(str3, "slug");
        Intrinsics.checkParameterIsNotNull(dappArr, "items");
        return new DappCategory(str, str2, i, i2, str3, dappArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DappCategory) {
                DappCategory dappCategory = (DappCategory) obj;
                if (Intrinsics.areEqual(this.id, dappCategory.id) && Intrinsics.areEqual(this.name, dappCategory.name)) {
                    if (this.order == dappCategory.order) {
                        if ((this.limit == dappCategory.limit) && Intrinsics.areEqual(this.slug, dappCategory.slug) && Intrinsics.areEqual(this.items, dappCategory.items)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getId() {
        return this.id;
    }

    public final Dapp[] getItems() {
        return this.items;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final String getName() {
        return this.name;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getSlug() {
        return this.slug;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        hashCode = (((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.order) * 31) + this.limit) * 31;
        str2 = this.slug;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Dapp[] dappArr = this.items;
        if (dappArr != null) {
            i = Arrays.hashCode(dappArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DappCategory(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", order=");
        stringBuilder.append(this.order);
        stringBuilder.append(", limit=");
        stringBuilder.append(this.limit);
        stringBuilder.append(", slug=");
        stringBuilder.append(this.slug);
        stringBuilder.append(", items=");
        stringBuilder.append(Arrays.toString(this.items));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
