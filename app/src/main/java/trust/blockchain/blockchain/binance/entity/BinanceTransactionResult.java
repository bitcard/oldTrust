package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceTransactionResult {
    /* renamed from: tx */
    private final BinanceTransaction tx;

    public BinanceTransactionResult(BinanceTransaction tx) {
        Intrinsics.checkParameterIsNotNull(tx, "tx");
        this.tx = tx;
    }

    public static /* synthetic */ BinanceTransactionResult copy$default(BinanceTransactionResult binanceTransactionResult, BinanceTransaction binanceTransaction, int i, Object obj) {
        if ((i & 1) != 0) {
            binanceTransaction = binanceTransactionResult.tx;
        }
        return binanceTransactionResult.copy(binanceTransaction);
    }

    public final BinanceTransaction component1() {
        return this.tx;
    }

    public final BinanceTransactionResult copy(BinanceTransaction tx) {
        Intrinsics.checkParameterIsNotNull(tx, "tx");
        return new BinanceTransactionResult(tx);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceTransactionResult) {
                if (Intrinsics.areEqual(this.tx, ((BinanceTransactionResult) obj).tx)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BinanceTransaction getTx() {
        return this.tx;
    }

    public int hashCode() {
        BinanceTransaction binanceTransaction = this.tx;
        return binanceTransaction != null ? binanceTransaction.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceTransactionResult(tx=");
        stringBuilder.append(this.tx);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
