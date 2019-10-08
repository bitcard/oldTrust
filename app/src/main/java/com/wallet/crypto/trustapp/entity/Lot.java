package com.wallet.crypto.trustapp.entity;

import java.math.BigInteger;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.PriceAddress;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Unit;

/* compiled from: Lot.kt */
public final class Lot {
    private final Asset asset;
    private final LotInfo lotInfo;

    public Lot(Asset asset, LotInfo lotInfo) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Intrinsics.checkParameterIsNotNull(lotInfo, "lotInfo");
        this.asset = asset;
        this.lotInfo = lotInfo;
    }

    public static /* synthetic */ Lot copy$default(Lot lot, Asset asset, LotInfo lotInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            asset = lot.asset;
        }
        if ((i & 2) != 0) {
            lotInfo = lot.lotInfo;
        }
        return lot.copy(asset, lotInfo);
    }

    public final Lot base(Lot this_) {
        Intrinsics.checkParameterIsNotNull(this_, "opposite");
        if (Intrinsics.areEqual(this.lotInfo.getQuoteAssetContract(), contract())) {
            return (Intrinsics.areEqual(this.lotInfo.getBaseAssetContract(), this_.lotInfo.getQuoteAssetContract()) || Intrinsics.areEqual(this.lotInfo.getQuoteAssetContract(), this_.lotInfo.getQuoteAssetContract())) ? this : this_;
        } else {
            return this_;
        }
    }

    public final Asset component1() {
        return this.asset;
    }

    public final LotInfo component2() {
        return this.lotInfo;
    }

    public final String contract() {
        String data = PriceAddress.byAsset(this.asset).data();
        Intrinsics.checkExpressionValueIsNotNull(data, "PriceAddress.byAsset(asset).data()");
        return data;
    }

    public final Lot copy(Asset asset, LotInfo lotInfo) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Intrinsics.checkParameterIsNotNull(lotInfo, "lotInfo");
        return new Lot(asset, lotInfo);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Lot) {
                Lot lot = (Lot) obj;
                if (Intrinsics.areEqual(this.asset, lot.asset) && Intrinsics.areEqual(this.lotInfo, lot.lotInfo)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final Asset getAsset() {
        return this.asset;
    }

    public final LotInfo getLotInfo() {
        return this.lotInfo;
    }

    public int hashCode() {
        Asset asset = this.asset;
        int i = 0;
        int hashCode = (asset != null ? asset.hashCode() : 0) * 31;
        LotInfo lotInfo = this.lotInfo;
        if (lotInfo != null) {
            i = lotInfo.hashCode();
        }
        return hashCode + i;
    }

    public final boolean isCoin() {
        return this.asset.isCoin();
    }

    public final BigInteger lotSize(Lot lot) {
        Intrinsics.checkParameterIsNotNull(lot, "someLot");
        if (Intrinsics.areEqual(this.lotInfo.getQuoteAssetContract(), lot.contract())) {
            BigInteger bigInteger = BigInteger.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(bigInteger, "BigInteger.ZERO");
            return bigInteger;
        } else if (Intrinsics.areEqual(this.lotInfo.getBaseAssetContract(), lot.contract())) {
            return this.lotInfo.getLotSize();
        } else {
            return lot.lotInfo.getLotSize();
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lot(asset=");
        stringBuilder.append(this.asset);
        stringBuilder.append(", lotInfo=");
        stringBuilder.append(this.lotInfo);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final Unit unit() {
        Unit unit = this.asset.unit();
        Intrinsics.checkExpressionValueIsNotNull(unit, "asset.unit()");
        return unit;
    }
}
