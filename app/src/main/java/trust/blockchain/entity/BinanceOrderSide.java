package trust.blockchain.entity;

/* compiled from: BinanceOrderPayload.kt */
public enum BinanceOrderSide {
    BUY(1),
    SELL(2);
    
    private final long value;

    private BinanceOrderSide(long j) {
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }

    public final long value() {
        return this.value;
    }
}
