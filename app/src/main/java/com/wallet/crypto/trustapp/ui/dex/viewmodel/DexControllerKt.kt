package com.wallet.crypto.trustapp.ui.dex.viewmodel

import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.TradeAsset
import java.math.BigDecimal
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Value
import java.math.BigInteger

/* compiled from: DexController.kt */
object DexControllerKt {

    fun findAssets(r11: Asset?, r12: Array<Lot>, r13: String?, r14: Boolean): Array<Asset> {
//        /*
//        r0 = "asset";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0);
//        r0 = "lots";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0);
        val contract = r11?.contract();
        var r0 = r12.filter {
//        r11 = r11.contract();
//        r0 = new java.util.ArrayList;
//        r0.<init>();
//        r0 = (java.util.Collection) r0;
//        r1 = r12.length;
//        r2 = 0;
//        r3 = r2;
//    L_0x0018:
//        r4 = 1;
//        if (r3 >= r1) goto L_0x0045;
//    L_0x001b:
//        r5 = r12[r3];
//        if (r14 != 0) goto L_0x003d;
//    L_0x001f:
//        r6 = r5.getLotInfo();
//        r6 = r6.getBaseAssetContract();
//        r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r11);
//        if (r6 != 0) goto L_0x003d;
//    L_0x002d:
//        r6 = r5.getLotInfo();
//        r6 = r6.getQuoteAssetContract();
//        r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r11);
//        if (r6 == 0) goto L_0x003c;
//    L_0x003b:
//        goto L_0x003d;
//    L_0x003c:
//        r4 = r2;
//    L_0x003d:
//        if (r4 == 0) goto L_0x0042;
//    L_0x003f:
//        r0.add(r5);
//    L_0x0042:
//        r3 = r3 + 1;
//        goto L_0x0018;
            r14 || it.lotInfo.baseAssetContract == contract || it.lotInfo.quoteAssetContract == contract
        }

        if (!r14) {
//    L_0x0045:
//        r0 = (java.util.List) r0;
//        r1 = 10;
//        if (r14 == 0) goto L_0x004d;
//    L_0x004b:
//        goto L_0x00d9;
//    L_0x004d:
//        r0 = (java.lang.Iterable) r0;
//        r14 = new java.util.ArrayList;
//        r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, r1);
//        r14.<init>(r3);
//        r14 = (java.util.Collection) r14;
//        r0 = r0.iterator();
            r0 = r0.map { lot ->
//    L_0x005e:
//        r3 = r0.hasNext();
//        if (r3 == 0) goto L_0x00ad;
//    L_0x0064:
//        r3 = r0.next();
//        r3 = (com.wallet.crypto.trustapp.entity.Lot) r3;
//        r5 = new java.util.ArrayList;
//        r5.<init>();
//        r5 = (java.util.Collection) r5;
//        r6 = r12.length;
//        r7 = r2;
                r12.filter {
//    L_0x0073:
//        if (r7 >= r6) goto L_0x00a7;
//    L_0x0075:
//        r8 = r12[r7];
//        r9 = r3.getLotInfo();
//        r9 = r9.getBaseAssetContract();
//        r10 = r8.contract();
//        r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10);
//        if (r9 != 0) goto L_0x009e;
//    L_0x0089:
//        r9 = r3.getLotInfo();
//        r9 = r9.getQuoteAssetContract();
//        r10 = r8.contract();
//        r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10);
//        if (r9 == 0) goto L_0x009c;
//    L_0x009b:
//        goto L_0x009e;
//    L_0x009c:
//        r9 = r2;
//        goto L_0x009f;
//    L_0x009e:
//        r9 = r4;
//    L_0x009f:
//        if (r9 == 0) goto L_0x00a4;
//    L_0x00a1:
//        r5.add(r8);
//    L_0x00a4:
//        r7 = r7 + 1;
//        goto L_0x0073;
                    lot.lotInfo.baseAssetContract == it.contract()  || lot.lotInfo.quoteAssetContract == it.contract()
                }
//    L_0x00a7:
//        r5 = (java.util.List) r5;
//        r14.add(r5);
//        goto L_0x005e;
            }.reduce{a, b -> a + b}
//    L_0x00ad:
//        r14 = (java.util.List) r14;
//        r14 = (java.lang.Iterable) r14;
//        r12 = r14.iterator();
//        r14 = r12.hasNext();
//        if (r14 == 0) goto L_0x01ea;
//    L_0x00bb:
//        r14 = r12.next();
//    L_0x00bf:
//        r0 = r12.hasNext();
//        if (r0 == 0) goto L_0x00d6;
//    L_0x00c5:
//        r0 = r12.next();
//        r0 = (java.util.List) r0;
//        r14 = (java.util.List) r14;
//        r14 = (java.util.Collection) r14;
//        r0 = (java.lang.Iterable) r0;
//        r14 = kotlin.collections.CollectionsKt___CollectionsKt.plus(r14, r0);
//        goto L_0x00bf;
//    L_0x00d6:
//        r0 = r14;
//        r0 = (java.util.List) r0;
        }

        val temp = r0.map {
//    L_0x00d9:
//        r0 = (java.lang.Iterable) r0;
//        r12 = new java.util.ArrayList;
//        r14 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, r1);
//        r12.<init>(r14);
//        r12 = (java.util.Collection) r12;
//        r14 = r0.iterator();
//    L_0x00ea:
//        r0 = r14.hasNext();
//        if (r0 == 0) goto L_0x00fe;
//    L_0x00f0:
//        r0 = r14.next();
//        r0 = (com.wallet.crypto.trustapp.entity.Lot) r0;
//        r0 = r0.getAsset();
//        r12.add(r0);
//        goto L_0x00ea;
            it.asset
        }

        val buf = HashSet<String>()
        return temp.filter {
//    L_0x00fe:
//        r12 = (java.util.List) r12;
//        r12 = (java.lang.Iterable) r12;
//        r14 = new java.util.HashSet;
//        r14.<init>();
//        r0 = new java.util.ArrayList;
//        r0.<init>();
//        r12 = r12.iterator();
//    L_0x0110:
//        r1 = r12.hasNext();
//        if (r1 == 0) goto L_0x012b;
//    L_0x0116:
//        r1 = r12.next();
//        r3 = r1;
//        r3 = (trust.blockchain.entity.Asset) r3;
//        r3 = r3.id();
//        r3 = r14.add(r3);
//        if (r3 == 0) goto L_0x0110;
//    L_0x0127:
//        r0.add(r1);
//        goto L_0x0110;
            buf.add(it.id())
        }.filter {
//    L_0x012b:
//        r0 = (java.util.List) r0;
//        r0 = (java.lang.Iterable) r0;
//        r12 = new java.util.ArrayList;
//        r12.<init>();
//        r12 = (java.util.Collection) r12;
//        r14 = r0.iterator();
//    L_0x013a:
//        r0 = r14.hasNext();
//        if (r0 == 0) goto L_0x0156;
//    L_0x0140:
//        r0 = r14.next();
//        r1 = r0;
//        r1 = (trust.blockchain.entity.Asset) r1;
//        r1 = r1.contract();
//        r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r11);
//        r1 = r1 ^ r4;
//        if (r1 == 0) goto L_0x013a;
//    L_0x0152:
//        r12.add(r0);
//        goto L_0x013a;
            it.contract() != contract
        }.filter {
//    L_0x0156:
//        r12 = (java.util.List) r12;
//        r12 = (java.lang.Iterable) r12;
//        r11 = new java.util.ArrayList;
//        r11.<init>();
//        r11 = (java.util.Collection) r11;
//        r12 = r12.iterator();
//    L_0x0165:
//        r14 = r12.hasNext();
//        if (r14 == 0) goto L_0x01d3;
//    L_0x016b:
//        r14 = r12.next();
//        r0 = r14;
//        r0 = (trust.blockchain.entity.Asset) r0;
//        r1 = r13;
//        r1 = (java.lang.CharSequence) r1;
//        if (r1 == 0) goto L_0x0180;
//    L_0x0177:
//        r3 = r1.length();
//        if (r3 != 0) goto L_0x017e;
//    L_0x017d:
//        goto L_0x0180;
//    L_0x017e:
//        r3 = r2;
//        goto L_0x0181;
//    L_0x0180:
//        r3 = r4;
//    L_0x0181:
//        if (r3 != 0) goto L_0x01cc;
//    L_0x0183:
//        r3 = r0.contract;
//        r3 = r3.name;
//        r5 = "it.contract.name";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
//        r3 = (java.lang.CharSequence) r3;
//        r3 = kotlin.text.StringsKt__StringsKt.contains(r3, r1, r4);
//        if (r3 != 0) goto L_0x01cc;
//    L_0x0194:
//        r3 = r0.contract;
//        r3 = r3.unit;
//        r3 = r3.symbol;
//        r5 = "it.contract.unit.symbol";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
//        r3 = (java.lang.CharSequence) r3;
//        r3 = kotlin.text.StringsKt__StringsKt.contains(r3, r1, r4);
//        if (r3 != 0) goto L_0x01cc;
//    L_0x01a7:
//        r3 = r0.contract;
//        r3 = r3.contract;
//        r5 = "it.contract.contract";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
//        r3 = (java.lang.CharSequence) r3;
//        r3 = kotlin.text.StringsKt__StringsKt.contains(r3, r1, r4);
//        if (r3 != 0) goto L_0x01cc;
//    L_0x01b8:
//        r0 = r0.contract;
//        r0 = r0.tokenId;
//        r3 = "it.contract.tokenId";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3);
//        r0 = (java.lang.CharSequence) r0;
//        r0 = kotlin.text.StringsKt__StringsKt.contains(r0, r1, r4);
//        if (r0 == 0) goto L_0x01ca;
//    L_0x01c9:
//        goto L_0x01cc;
//    L_0x01ca:
//        r0 = r2;
//        goto L_0x01cd;
//    L_0x01cc:
//        r0 = r4;
//    L_0x01cd:
//        if (r0 == 0) goto L_0x0165;
//    L_0x01cf:
//        r11.add(r14);
//        goto L_0x0165;
            r13.isNullOrEmpty()
                    || it.contract.name.contains(r13, true)
                    || it.contract.unit.symbol.contains(r13, true)
                    || it.contract.contract.contains(r13, true)
                    || it.contract.tokenId.contains(r13, true)
        }.toTypedArray()
//    L_0x01d3:
//        r11 = (java.util.List) r11;
//        r11 = (java.util.Collection) r11;
//        r12 = new trust.blockchain.entity.Asset[r2];
//        r11 = r11.toArray(r12);
//        if (r11 == 0) goto L_0x01e2;
//    L_0x01df:
//        r11 = (trust.blockchain.entity.Asset[]) r11;
//        return r11;
//    L_0x01e2:
//        r11 = new kotlin.TypeCastException;
//        r12 = "null cannot be cast to non-null type kotlin.Array<T>";
//        r11.<init>(r12);
//        throw r11;
//    L_0x01ea:
//        r11 = new java.lang.UnsupportedOperationException;
//        r12 = "Empty collection can't be reduced.";
//        r11.<init>(r12);
//        r11 = (java.lang.Throwable) r11;
//        throw r11;
//        */
    }

    fun findLot(lots: Array<Lot>, from: Asset?, to: Asset): Lot? {
        val contractFrom = from?.contract()
        val contractTo = to.contract()
        val list = lots.filter {
            it.lotInfo.baseAssetContract == contractFrom && it.lotInfo.quoteAssetContract == contractTo
                    || it.lotInfo.baseAssetContract == contractTo && it.lotInfo.quoteAssetContract == contractFrom
        }
        return if (list.isEmpty()) null else list.first()
    }

    fun validateBalance(r2: TradeAsset?, r3: String): Int {
//        /*
//        if (r2 == 0) goto L_0x0007;
//    L_0x0002:
//        r2 = r2.getFromAsset();
//        goto L_0x0008;
//    L_0x0007:
//        r2 = 0;
//    L_0x0008:
        val fromAsset = r2?.getFromAsset()
//        if (r2 == 0) goto L_0x000f;
//    L_0x000a:
//        r0 = r2.balance;
//        if (r0 == 0) goto L_0x000f;
//    L_0x000e:
//        goto L_0x0016;
//    L_0x000f:
//        r0 = new trust.blockchain.entity.Value;
//        r1 = java.math.BigInteger.ZERO;
//        r0.<init>(r1);
//    L_0x0016:
//        r1 = "fromAsset?.balance ?: Value(BigInteger.ZERO)";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        val r0 = fromAsset?.balance ?: Value(BigInteger.ZERO)
        val unit = try {
//        if (r2 == 0) goto L_0x002a;
//    L_0x001d:
//        r2 = r2.unit();	 Catch:{ Exception -> 0x0032 }
//        if (r2 == 0) goto L_0x002a;
//    L_0x0023:
//        r2 = r2.toUnit(r3);	 Catch:{ Exception -> 0x0032 }
//        if (r2 == 0) goto L_0x002a;
//    L_0x0029:
//        goto L_0x0039;
//    L_0x002a:
//        r2 = new java.math.BigInteger;	 Catch:{ Exception -> 0x0032 }
//        r3 = "-1";
//        r2.<init>(r3);	 Catch:{ Exception -> 0x0032 }
//        goto L_0x0039;
            fromAsset?.unit()?.toUnit(r3)?: BigInteger("-1")
        } catch (e : Exception) {
//    L_0x0032:
//        r2 = new java.math.BigInteger;
//        r3 = "-1";
//        r2.<init>(r3);
            BigInteger("-1")
        }
//    L_0x0039:
//        r3 = r0.bigInteger();
//        r2 = r2.compareTo(r3);
//        r3 = 1;
//        if (r2 >= r3) goto L_0x0046;
//    L_0x0044:
//        r2 = 0;
//        goto L_0x0049;
//    L_0x0046:
//        r2 = 2131820957; // 0x7f11019d float:1.9274644E38 double:1.053259498E-314;
//    L_0x0049:
//        return r2;
//        */
        return if (unit.compareTo(r0.bigInteger()) >= 1) R.string.send_error_insufficientEther else 0
    }

    fun validateValue(tradeAsset: TradeAsset?, asset: Asset?, str: String?): Int {
        if (tradeAsset != null && asset != null && str != null) {
            if (tradeAsset.base()?.id() == asset.id()) {
                try {
                    val unitValue = asset.unit().toUnit(BigDecimal(str))
                    val divide = unitValue.divide(tradeAsset.lotSize())
                    val multiply = divide.multiply(tradeAsset.lotSize())
                    return if (unitValue != multiply) R.string.LotSizeMultiplesError else 0
                } catch (unused: Exception) {
                    return R.string.MustNumericValue
                }
            }
        }
        return 0
    }
}
