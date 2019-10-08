package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceTransaction {
    private final BinanceTransactionValue value;

    public BinanceTransaction(BinanceTransactionValue binanceTransactionValue) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValue, "value");
        this.value = binanceTransactionValue;
    }

    public static /* synthetic */ BinanceTransaction copy$default(BinanceTransaction binanceTransaction, BinanceTransactionValue binanceTransactionValue, int i, Object obj) {
        if ((i & 1) != 0) {
            binanceTransactionValue = binanceTransaction.value;
        }
        return binanceTransaction.copy(binanceTransactionValue);
    }

    public final BinanceTransactionValue component1() {
        return this.value;
    }

    public final BinanceTransaction copy(BinanceTransactionValue binanceTransactionValue) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValue, "value");
        return new BinanceTransaction(binanceTransactionValue);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceTransaction) {
                if (Intrinsics.areEqual(this.value, ((BinanceTransaction) obj).value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BinanceTransactionValue getValue() {
        return this.value;
    }

    public int hashCode() {
        BinanceTransactionValue binanceTransactionValue = this.value;
        return binanceTransactionValue != null ? binanceTransactionValue.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceTransaction(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
