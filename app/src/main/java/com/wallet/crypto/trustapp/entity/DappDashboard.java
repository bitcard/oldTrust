package com.wallet.crypto.trustapp.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappDashboard.kt */
public final class DappDashboard {
    private final DappCategory[] categories;
    private final DappLink[] favorites;
    private final DappLink[] history;

    public DappDashboard(DappLink[] dappLinkArr, DappLink[] dappLinkArr2, DappCategory[] dappCategoryArr) {
        Intrinsics.checkParameterIsNotNull(dappLinkArr, "favorites");
        Intrinsics.checkParameterIsNotNull(dappLinkArr2, "history");
        Intrinsics.checkParameterIsNotNull(dappCategoryArr, "categories");
        this.favorites = dappLinkArr;
        this.history = dappLinkArr2;
        this.categories = dappCategoryArr;
    }

    public static /* synthetic */ DappDashboard copy$default(DappDashboard dappDashboard, DappLink[] dappLinkArr, DappLink[] dappLinkArr2, DappCategory[] dappCategoryArr, int i, Object obj) {
        if ((i & 1) != 0) {
            dappLinkArr = dappDashboard.favorites;
        }
        if ((i & 2) != 0) {
            dappLinkArr2 = dappDashboard.history;
        }
        if ((i & 4) != 0) {
            dappCategoryArr = dappDashboard.categories;
        }
        return dappDashboard.copy(dappLinkArr, dappLinkArr2, dappCategoryArr);
    }

    public final DappLink[] component1() {
        return this.favorites;
    }

    public final DappLink[] component2() {
        return this.history;
    }

    public final DappCategory[] component3() {
        return this.categories;
    }

    public final DappDashboard copy(DappLink[] dappLinkArr, DappLink[] dappLinkArr2, DappCategory[] dappCategoryArr) {
        Intrinsics.checkParameterIsNotNull(dappLinkArr, "favorites");
        Intrinsics.checkParameterIsNotNull(dappLinkArr2, "history");
        Intrinsics.checkParameterIsNotNull(dappCategoryArr, "categories");
        return new DappDashboard(dappLinkArr, dappLinkArr2, dappCategoryArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DappDashboard) {
                DappDashboard dappDashboard = (DappDashboard) obj;
                if (Intrinsics.areEqual(this.favorites, dappDashboard.favorites) && Intrinsics.areEqual(this.history, dappDashboard.history) && Intrinsics.areEqual(this.categories, dappDashboard.categories)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final DappCategory[] getCategories() {
        return this.categories;
    }

    public final DappLink[] getFavorites() {
        return this.favorites;
    }

    public final DappLink[] getHistory() {
        return this.history;
    }

    public int hashCode() {
        DappLink[] dappLinkArr = this.favorites;
        int i = 0;
        int hashCode = (dappLinkArr != null ? Arrays.hashCode(dappLinkArr) : 0) * 31;
        DappLink[] dappLinkArr2 = this.history;
        hashCode = (hashCode + (dappLinkArr2 != null ? Arrays.hashCode(dappLinkArr2) : 0)) * 31;
        DappCategory[] dappCategoryArr = this.categories;
        if (dappCategoryArr != null) {
            i = Arrays.hashCode(dappCategoryArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DappDashboard(favorites=");
        stringBuilder.append(Arrays.toString(this.favorites));
        stringBuilder.append(", history=");
        stringBuilder.append(Arrays.toString(this.history));
        stringBuilder.append(", categories=");
        stringBuilder.append(Arrays.toString(this.categories));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
