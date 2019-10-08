package trust.blockchain.blockchain.binance.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BinanceTransactionValue {
    private final BinanceTransactionValueMessage[] msg;

    public BinanceTransactionValue(BinanceTransactionValueMessage[] binanceTransactionValueMessageArr) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValueMessageArr, "msg");
        this.msg = binanceTransactionValueMessageArr;
    }

    public static /* synthetic */ BinanceTransactionValue copy$default(BinanceTransactionValue binanceTransactionValue, BinanceTransactionValueMessage[] binanceTransactionValueMessageArr, int i, Object obj) {
        if ((i & 1) != 0) {
            binanceTransactionValueMessageArr = binanceTransactionValue.msg;
        }
        return binanceTransactionValue.copy(binanceTransactionValueMessageArr);
    }

    public final BinanceTransactionValueMessage[] component1() {
        return this.msg;
    }

    public final BinanceTransactionValue copy(BinanceTransactionValueMessage[] binanceTransactionValueMessageArr) {
        Intrinsics.checkParameterIsNotNull(binanceTransactionValueMessageArr, "msg");
        return new BinanceTransactionValue(binanceTransactionValueMessageArr);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceTransactionValue) {
                if (Intrinsics.areEqual(this.msg, ((BinanceTransactionValue) obj).msg)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BinanceTransactionValueMessage[] getMsg() {
        return this.msg;
    }

    public int hashCode() {
        BinanceTransactionValueMessage[] binanceTransactionValueMessageArr = this.msg;
        return binanceTransactionValueMessageArr != null ? Arrays.hashCode(binanceTransactionValueMessageArr) : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceTransactionValue(msg=");
        stringBuilder.append(Arrays.toString(this.msg));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
