package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceOrder {
    private final String status;

    public BinanceOrder(String str) {
        Intrinsics.checkParameterIsNotNull(str, "status");
        this.status = str;
    }

    public static /* synthetic */ BinanceOrder copy$default(BinanceOrder binanceOrder, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = binanceOrder.status;
        }
        return binanceOrder.copy(str);
    }

    public final String component1() {
        return this.status;
    }

    public final BinanceOrder copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "status");
        return new BinanceOrder(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceOrder) {
                if (Intrinsics.areEqual(this.status, ((BinanceOrder) obj).status)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceOrder(status=");
        stringBuilder.append(this.status);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
