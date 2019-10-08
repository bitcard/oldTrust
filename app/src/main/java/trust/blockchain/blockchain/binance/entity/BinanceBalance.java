package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceBalance {
    private final String free;
    private final String frozen;
    private final String locked;
    private final String symbol;

    public BinanceBalance(String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "symbol");
        Intrinsics.checkParameterIsNotNull(str2, "free");
        Intrinsics.checkParameterIsNotNull(str3, "locked");
        Intrinsics.checkParameterIsNotNull(str4, "frozen");
        this.symbol = str;
        this.free = str2;
        this.locked = str3;
        this.frozen = str4;
    }

    public static /* synthetic */ BinanceBalance copy$default(BinanceBalance binanceBalance, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = binanceBalance.symbol;
        }
        if ((i & 2) != 0) {
            str2 = binanceBalance.free;
        }
        if ((i & 4) != 0) {
            str3 = binanceBalance.locked;
        }
        if ((i & 8) != 0) {
            str4 = binanceBalance.frozen;
        }
        return binanceBalance.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.symbol;
    }

    public final String component2() {
        return this.free;
    }

    public final String component3() {
        return this.locked;
    }

    public final String component4() {
        return this.frozen;
    }

    public final BinanceBalance copy(String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "symbol");
        Intrinsics.checkParameterIsNotNull(str2, "free");
        Intrinsics.checkParameterIsNotNull(str3, "locked");
        Intrinsics.checkParameterIsNotNull(str4, "frozen");
        return new BinanceBalance(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceBalance) {
                BinanceBalance binanceBalance = (BinanceBalance) obj;
                if (Intrinsics.areEqual(this.symbol, binanceBalance.symbol) && Intrinsics.areEqual(this.free, binanceBalance.free) && Intrinsics.areEqual(this.locked, binanceBalance.locked) && Intrinsics.areEqual(this.frozen, binanceBalance.frozen)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getFree() {
        return this.free;
    }

    public final String getFrozen() {
        return this.frozen;
    }

    public final String getLocked() {
        return this.locked;
    }

    public final String getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        String str = this.symbol;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.free;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.locked;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.frozen;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceBalance(symbol=");
        stringBuilder.append(this.symbol);
        stringBuilder.append(", free=");
        stringBuilder.append(this.free);
        stringBuilder.append(", locked=");
        stringBuilder.append(this.locked);
        stringBuilder.append(", frozen=");
        stringBuilder.append(this.frozen);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
