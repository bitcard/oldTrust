package com.wallet.crypto.trustapp;

import java.math.MathContext;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: CoinConfig.kt */
public final class CoinConfig {
    /* renamed from: a */
    public static final CoinConfig f16616a = new CoinConfig();

    private static final Slip[] dexCoins = new Slip[]{Slip.BINANCE, Slip.ETH};  // f16758a

    public final static  /* synthetic */ class WhenMappings {
        /* renamed from: a */
        public static final /* synthetic */ int[] f16604a = new int[Slip.values().length];
        /* renamed from: b */
        public static final /* synthetic */ int[] f16605b = new int[Slip.values().length];
        /* renamed from: c */
        public static final /* synthetic */ int[] f16606c = new int[Slip.values().length];
        /* renamed from: d */
        public static final /* synthetic */ int[] f16607d = new int[Slip.values().length];
        /* renamed from: e */
        public static final /* synthetic */ int[] f16608e = new int[Slip.values().length];
        /* renamed from: f */
        public static final /* synthetic */ int[] f16609f = new int[Slip.values().length];
        /* renamed from: g */
        public static final /* synthetic */ int[] f16610g = new int[Slip.values().length];
        /* renamed from: h */
        public static final /* synthetic */ int[] f16611h = new int[Slip.values().length];
        /* renamed from: i */
        public static final /* synthetic */ int[] f16612i = new int[Slip.values().length];
        /* renamed from: j */
        public static final /* synthetic */ int[] f16613j = new int[Slip.values().length];
        /* renamed from: k */
        public static final /* synthetic */ int[] f16614k = new int[Slip.values().length];
        /* renamed from: l */
        public static final /* synthetic */ int[] f16615l = new int[Slip.values().length];

        static {
            f16604a[Slip.ETH.ordinal()] = 1;
            f16604a[Slip.BTC.ordinal()] = 2;
            f16604a[Slip.BINANCE.ordinal()] = 3;
            f16604a[Slip.CLO.ordinal()] = 4;
            f16604a[Slip.GO.ordinal()] = 5;
            f16604a[Slip.ETC.ordinal()] = 6;
            f16604a[Slip.POA.ordinal()] = 7;
            f16604a[Slip.VET.ordinal()] = 8;
            f16604a[Slip.WAN.ordinal()] = 9;
            f16604a[Slip.TRX.ordinal()] = 10;
            f16604a[Slip.ICX.ordinal()] = 11;
            f16604a[Slip.TOMO.ordinal()] = 12;
            f16604a[Slip.LTC.ordinal()] = 13;
            f16604a[Slip.BCH.ordinal()] = 14;
            f16604a[Slip.DASH.ordinal()] = 15;
            f16604a[Slip.ZCOIN.ordinal()] = 16;
            f16604a[Slip.ZCASH.ordinal()] = 17;
            f16604a[Slip.RIPPLE.ordinal()] = 18;
            f16604a[Slip.TEZOS.ordinal()] = 19;
            f16604a[Slip.STELLAR.ordinal()] = 20;
            f16604a[Slip.KIN.ordinal()] = 21;
            f16604a[Slip.AION.ordinal()] = 22;
            f16604a[Slip.NIMIQ.ordinal()] = 23;
            f16604a[Slip.THUNDERTOKEN.ordinal()] = 24;
            f16604a[Slip.DOGECOIN.ordinal()] = 25;
            f16604a[Slip.COSMOS.ordinal()] = 26;
            f16604a[Slip.ONTOLOGY.ordinal()] = 27;
            f16604a[Slip.GROESTL.ordinal()] = 28;
            f16604a[Slip.QTUM.ordinal()] = 29;
            f16604a[Slip.VIACOIN.ordinal()] = 30;
            f16604a[Slip.ZELCASH.ordinal()] = 31;
            f16604a[Slip.ZILLIQA.ordinal()] = 32;
            f16604a[Slip.IOTEX.ordinal()] = 33;
            f16604a[Slip.THETA.ordinal()] = 34;
            f16604a[Slip.WAVES.ordinal()] = 35;
            f16604a[Slip.RAVEN.ordinal()] = 36;
            f16605b[Slip.BTC.ordinal()] = 1;
            f16605b[Slip.LTC.ordinal()] = 2;
            f16605b[Slip.BCH.ordinal()] = 3;
            f16605b[Slip.DASH.ordinal()] = 4;
            f16605b[Slip.ZCOIN.ordinal()] = 5;
            f16605b[Slip.ZCASH.ordinal()] = 6;
            f16605b[Slip.GROESTL.ordinal()] = 7;
            f16605b[Slip.QTUM.ordinal()] = 8;
            f16605b[Slip.VIACOIN.ordinal()] = 9;
            f16605b[Slip.ZELCASH.ordinal()] = 10;
            f16605b[Slip.DOGECOIN.ordinal()] = 11;
            f16605b[Slip.RAVEN.ordinal()] = 12;
            f16605b[Slip.ETH.ordinal()] = 13;
            f16605b[Slip.CLO.ordinal()] = 14;
            f16605b[Slip.GO.ordinal()] = 15;
            f16605b[Slip.ETC.ordinal()] = 16;
            f16605b[Slip.POA.ordinal()] = 17;
            f16605b[Slip.VET.ordinal()] = 18;
            f16605b[Slip.WAN.ordinal()] = 19;
            f16605b[Slip.TRX.ordinal()] = 20;
            f16605b[Slip.ICX.ordinal()] = 21;
            f16605b[Slip.TOMO.ordinal()] = 22;
            f16605b[Slip.BINANCE.ordinal()] = 23;
            f16605b[Slip.RIPPLE.ordinal()] = 24;
            f16605b[Slip.TEZOS.ordinal()] = 25;
            f16605b[Slip.STELLAR.ordinal()] = 26;
            f16605b[Slip.KIN.ordinal()] = 27;
            f16605b[Slip.AION.ordinal()] = 28;
            f16605b[Slip.NIMIQ.ordinal()] = 29;
            f16605b[Slip.THUNDERTOKEN.ordinal()] = 30;
            f16605b[Slip.THETA.ordinal()] = 31;
            f16605b[Slip.ONTOLOGY.ordinal()] = 32;
            f16605b[Slip.ZILLIQA.ordinal()] = 33;
            f16605b[Slip.IOTEX.ordinal()] = 34;
            f16605b[Slip.WAVES.ordinal()] = 35;
            f16605b[Slip.COSMOS.ordinal()] = 36;
            f16606c[Slip.BTC.ordinal()] = 1;
            f16606c[Slip.LTC.ordinal()] = 2;
            f16606c[Slip.BCH.ordinal()] = 3;
            f16606c[Slip.DASH.ordinal()] = 4;
            f16606c[Slip.ZCOIN.ordinal()] = 5;
            f16606c[Slip.ZCASH.ordinal()] = 6;
            f16606c[Slip.GROESTL.ordinal()] = 7;
            f16606c[Slip.QTUM.ordinal()] = 8;
            f16606c[Slip.VIACOIN.ordinal()] = 9;
            f16606c[Slip.ZELCASH.ordinal()] = 10;
            f16606c[Slip.DOGECOIN.ordinal()] = 11;
            f16606c[Slip.RAVEN.ordinal()] = 12;
            f16606c[Slip.ETH.ordinal()] = 13;
            f16606c[Slip.CLO.ordinal()] = 14;
            f16606c[Slip.GO.ordinal()] = 15;
            f16606c[Slip.ETC.ordinal()] = 16;
            f16606c[Slip.POA.ordinal()] = 17;
            f16606c[Slip.VET.ordinal()] = 18;
            f16606c[Slip.WAN.ordinal()] = 19;
            f16606c[Slip.TRX.ordinal()] = 20;
            f16606c[Slip.ICX.ordinal()] = 21;
            f16606c[Slip.TOMO.ordinal()] = 22;
            f16606c[Slip.BINANCE.ordinal()] = 23;
            f16606c[Slip.RIPPLE.ordinal()] = 24;
            f16606c[Slip.TEZOS.ordinal()] = 25;
            f16606c[Slip.STELLAR.ordinal()] = 26;
            f16606c[Slip.KIN.ordinal()] = 27;
            f16606c[Slip.AION.ordinal()] = 28;
            f16606c[Slip.NIMIQ.ordinal()] = 29;
            f16606c[Slip.THUNDERTOKEN.ordinal()] = 30;
            f16606c[Slip.ONTOLOGY.ordinal()] = 31;
            f16606c[Slip.COSMOS.ordinal()] = 32;
            f16606c[Slip.ZILLIQA.ordinal()] = 33;
            f16606c[Slip.IOTEX.ordinal()] = 34;
            f16606c[Slip.WAVES.ordinal()] = 35;
            f16606c[Slip.THETA.ordinal()] = 36;
            f16607d[Slip.BTC.ordinal()] = 1;
            f16607d[Slip.LTC.ordinal()] = 2;
            f16607d[Slip.BCH.ordinal()] = 3;
            f16607d[Slip.DASH.ordinal()] = 4;
            f16607d[Slip.ZCOIN.ordinal()] = 5;
            f16607d[Slip.ZCASH.ordinal()] = 6;
            f16607d[Slip.BINANCE.ordinal()] = 7;
            f16607d[Slip.RIPPLE.ordinal()] = 8;
            f16607d[Slip.AION.ordinal()] = 9;
            f16607d[Slip.KIN.ordinal()] = 10;
            f16607d[Slip.STELLAR.ordinal()] = 11;
            f16607d[Slip.NIMIQ.ordinal()] = 12;
            f16607d[Slip.TEZOS.ordinal()] = 13;
            f16607d[Slip.THETA.ordinal()] = 14;
            f16607d[Slip.TRX.ordinal()] = 15;
            f16607d[Slip.COSMOS.ordinal()] = 16;
            f16607d[Slip.ONTOLOGY.ordinal()] = 17;
            f16607d[Slip.GROESTL.ordinal()] = 18;
            f16607d[Slip.QTUM.ordinal()] = 19;
            f16607d[Slip.VIACOIN.ordinal()] = 20;
            f16607d[Slip.ZELCASH.ordinal()] = 21;
            f16607d[Slip.ZILLIQA.ordinal()] = 22;
            f16607d[Slip.IOTEX.ordinal()] = 23;
            f16607d[Slip.DOGECOIN.ordinal()] = 24;
            f16607d[Slip.WAVES.ordinal()] = 25;
            f16607d[Slip.RAVEN.ordinal()] = 26;
            f16607d[Slip.ETH.ordinal()] = 27;
            f16607d[Slip.CLO.ordinal()] = 28;
            f16607d[Slip.GO.ordinal()] = 29;
            f16607d[Slip.ETC.ordinal()] = 30;
            f16607d[Slip.POA.ordinal()] = 31;
            f16607d[Slip.VET.ordinal()] = 32;
            f16607d[Slip.WAN.ordinal()] = 33;
            f16607d[Slip.ICX.ordinal()] = 34;
            f16607d[Slip.TOMO.ordinal()] = 35;
            f16607d[Slip.THUNDERTOKEN.ordinal()] = 36;
            f16608e[Slip.BINANCE.ordinal()] = 1;
            f16608e[Slip.RIPPLE.ordinal()] = 2;
            f16608e[Slip.STELLAR.ordinal()] = 3;
            f16608e[Slip.COSMOS.ordinal()] = 4;
            f16608e[Slip.KIN.ordinal()] = 5;
            f16608e[Slip.BTC.ordinal()] = 6;
            f16608e[Slip.LTC.ordinal()] = 7;
            f16608e[Slip.BCH.ordinal()] = 8;
            f16608e[Slip.DASH.ordinal()] = 9;
            f16608e[Slip.ZCOIN.ordinal()] = 10;
            f16608e[Slip.ZCASH.ordinal()] = 11;
            f16608e[Slip.AION.ordinal()] = 12;
            f16608e[Slip.NIMIQ.ordinal()] = 13;
            f16608e[Slip.TEZOS.ordinal()] = 14;
            f16608e[Slip.THETA.ordinal()] = 15;
            f16608e[Slip.TRX.ordinal()] = 16;
            f16608e[Slip.DOGECOIN.ordinal()] = 17;
            f16608e[Slip.ETH.ordinal()] = 18;
            f16608e[Slip.CLO.ordinal()] = 19;
            f16608e[Slip.GO.ordinal()] = 20;
            f16608e[Slip.ETC.ordinal()] = 21;
            f16608e[Slip.POA.ordinal()] = 22;
            f16608e[Slip.VET.ordinal()] = 23;
            f16608e[Slip.WAN.ordinal()] = 24;
            f16608e[Slip.ICX.ordinal()] = 25;
            f16608e[Slip.TOMO.ordinal()] = 26;
            f16608e[Slip.ONTOLOGY.ordinal()] = 27;
            f16608e[Slip.GROESTL.ordinal()] = 28;
            f16608e[Slip.QTUM.ordinal()] = 29;
            f16608e[Slip.VIACOIN.ordinal()] = 30;
            f16608e[Slip.ZELCASH.ordinal()] = 31;
            f16608e[Slip.ZILLIQA.ordinal()] = 32;
            f16608e[Slip.IOTEX.ordinal()] = 33;
            f16608e[Slip.THUNDERTOKEN.ordinal()] = 34;
            f16608e[Slip.WAVES.ordinal()] = 35;
            f16608e[Slip.RAVEN.ordinal()] = 36;
            f16609f[Slip.RIPPLE.ordinal()] = 1;
            f16610g[Slip.BINANCE.ordinal()] = 1;
            f16610g[Slip.STELLAR.ordinal()] = 2;
            f16610g[Slip.COSMOS.ordinal()] = 3;
            f16610g[Slip.KIN.ordinal()] = 4;
            f16611h[Slip.VET.ordinal()] = 1;
            f16611h[Slip.THETA.ordinal()] = 2;
            f16612i[Slip.RIPPLE.ordinal()] = 1;
            f16612i[Slip.STELLAR.ordinal()] = 2;
            f16612i[Slip.KIN.ordinal()] = 3;
            f16612i[Slip.BINANCE.ordinal()] = 4;
            f16612i[Slip.COSMOS.ordinal()] = 5;
            f16613j[Slip.TRX.ordinal()] = 1;
            f16613j[Slip.COSMOS.ordinal()] = 2;
            f16613j[Slip.ZILLIQA.ordinal()] = 3;
            f16614k[Slip.TEZOS.ordinal()] = 1;
            f16615l[Slip.ETH.ordinal()] = 1;
            f16615l[Slip.ETC.ordinal()] = 2;
            f16615l[Slip.GO.ordinal()] = 3;
            f16615l[Slip.CLO.ordinal()] = 4;
            f16615l[Slip.POA.ordinal()] = 5;
            f16615l[Slip.VET.ordinal()] = 6;
            f16615l[Slip.WAN.ordinal()] = 7;
            f16615l[Slip.TOMO.ordinal()] = 8;
            f16615l[Slip.THUNDERTOKEN.ordinal()] = 9;
            f16615l[Slip.IOTEX.ordinal()] = 10;
        }
    }

    private CoinConfig() {
    }

    public static final Slip[] getDexCoins() {
        return dexCoins;
    }

    public static final boolean isUTXOBased(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16605b[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return true;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean supportEnergyAsset(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16611h[slip.ordinal()]) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public static final boolean supportKeyStoreImport(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16615l[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    public final MathContext getMathContext(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        MathContext mathContext;
        if (slip.unit().decimals <= 8) {
            mathContext = MathContext.DECIMAL32;
            Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.DECIMAL32");
            return mathContext;
        }
        mathContext = MathContext.DECIMAL64;
        Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.DECIMAL64");
        return mathContext;
    }

    public final int getTagOrMemoText(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16612i[slip.ordinal()]) {
            case 1:
                return R.string.Destination_Tag;
            case 2:
            case 3:
            case 4:
            case 5:
                return R.string.Memo;
            default:
                throw new IllegalStateException("Not supported");
        }
    }

    public final boolean hasCustomTransactionFinder(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16606c[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return true;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean hasCustomTransactionSigner(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16607d[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return true;
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean hasMemo(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16610g[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }

    public final boolean hasSimplePayloadType(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16613j[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return false;
            default:
                return true;
        }
    }

    public final boolean hasTag(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        return WhenMappings.f16609f[slip.ordinal()] == 1;
    }

    public final boolean hasTagOrMemo(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16608e[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean isEnableByDefault(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        switch (WhenMappings.f16604a[slip.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return true;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean needToPassBlockLevel(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        return WhenMappings.f16614k[slip.ordinal()] == 1;
    }
}
