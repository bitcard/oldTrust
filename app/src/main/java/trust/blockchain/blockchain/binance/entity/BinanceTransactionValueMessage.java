package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceTransactionValueMessage {
    private final BinanceTransactionValueMessageValue value;

    public BinanceTransactionValueMessage(BinanceTransactionValueMessageValue binanceTransactionValueMessageValue) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValueMessageValue, "value");
        this.value = binanceTransactionValueMessageValue;
    }

    public static /* synthetic */ BinanceTransactionValueMessage copy$default(BinanceTransactionValueMessage binanceTransactionValueMessage, BinanceTransactionValueMessageValue binanceTransactionValueMessageValue, int i, Object obj) {
        if ((i & 1) != 0) {
            binanceTransactionValueMessageValue = binanceTransactionValueMessage.value;
        }
        return binanceTransactionValueMessage.copy(binanceTransactionValueMessageValue);
    }

    public final BinanceTransactionValueMessageValue component1() {
        return this.value;
    }

    public final BinanceTransactionValueMessage copy(BinanceTransactionValueMessageValue binanceTransactionValueMessageValue) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValueMessageValue, "value");
        return new BinanceTransactionValueMessage(binanceTransactionValueMessageValue);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceTransactionValueMessage) {
                if (Intrinsics.areEqual(this.value, ((BinanceTransactionValueMessage) obj).value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BinanceTransactionValueMessageValue getValue() {
        return this.value;
    }

    public int hashCode() {
        BinanceTransactionValueMessageValue binanceTransactionValueMessageValue = this.value;
        return binanceTransactionValueMessageValue != null ? binanceTransactionValueMessageValue.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceTransactionValueMessage(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
