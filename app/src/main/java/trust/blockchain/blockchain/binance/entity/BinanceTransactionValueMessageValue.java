package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceTransactionValueMessageValue {
    /* renamed from: id */
    private final String id;
    private final int type;

    public BinanceTransactionValueMessageValue(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        this.id = str;
        this.type = i;
    }

    public static /* synthetic */ BinanceTransactionValueMessageValue copy$default(BinanceTransactionValueMessageValue binanceTransactionValueMessageValue, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = binanceTransactionValueMessageValue.id;
        }
        if ((i2 & 2) != 0) {
            i = binanceTransactionValueMessageValue.type;
        }
        return binanceTransactionValueMessageValue.copy(str, i);
    }

    public final String component1() {
        return this.id;
    }

    public final int component2() {
        return this.type;
    }

    public final BinanceTransactionValueMessageValue copy(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        return new BinanceTransactionValueMessageValue(str, i);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceTransactionValueMessageValue) {
                BinanceTransactionValueMessageValue binanceTransactionValueMessageValue = (BinanceTransactionValueMessageValue) obj;
                if (Intrinsics.areEqual(this.id, binanceTransactionValueMessageValue.id)) {
                    if (this.type == binanceTransactionValueMessageValue.type) {
                        return true;
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

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.id;
        return ((str != null ? str.hashCode() : 0) * 31) + this.type;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceTransactionValueMessageValue(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", type=");
        stringBuilder.append(this.type);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
