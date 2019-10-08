package com.wallet.crypto.trustapp.ui.wallets.entity;

import com.wallet.crypto.trustapp.entity.ViewData;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Account;

/* compiled from: ExtendedKeyViewData.kt */
public final class ExtendedKeyViewData implements ViewData {
//    /* renamed from: a */
//    public static final Companion f20111a = new Companion();
    /* renamed from: b */
    private final String f20112b;
    /* renamed from: c */
    private final String f20113c;
//
//    /* compiled from: ExtendedKeyViewData.kt */
//    public static final class Companion {
//        private Companion() {
//        }
//
//        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
//            this();
//        }
//    }

    public ExtendedKeyViewData(Account account) {
        Intrinsics.checkParameterIsNotNull(account, "accountKey");
        String coinName = account.coin.coinName();
        Intrinsics.checkExpressionValueIsNotNull(coinName, "accountKey.coin.coinName()");
        if (coinName != null) {
            coinName = coinName.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(coinName, "(this as java.lang.String).toUpperCase()");
            this.f20112b = coinName;
            String str = account.extendedPublicKey;
            Intrinsics.checkExpressionValueIsNotNull(str, "accountKey.extendedPublicKey");
            this.f20113c = str;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public boolean areContentsTheSame(ViewData viewData) {
        if (viewData != null) {
            ExtendedKeyViewData extendedKeyViewData = (ExtendedKeyViewData) viewData;
            return Intrinsics.areEqual(this.f20112b, extendedKeyViewData.f20112b) && Intrinsics.areEqual(this.f20113c, extendedKeyViewData.f20113c);
        } else {
            throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData");
        }
    }

    public boolean areItemsTheSame(ViewData viewData) {
        if (viewData != null) {
            return areItemsTheSame((ExtendedKeyViewData) viewData);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData");
    }

    public int compare(ViewData viewData) {
        String str = this.f20112b;
        if (viewData != null) {
            return str.compareTo(((ExtendedKeyViewData) viewData).f20112b);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData");
    }

    public final String getCoinName() {
        return this.f20112b;
    }

    public final String getExtendedKey() {
        return this.f20113c;
    }

    public int getViewType() {
        return 2005;
    }

    public int getWeight() {
        return 0;
    }
}
