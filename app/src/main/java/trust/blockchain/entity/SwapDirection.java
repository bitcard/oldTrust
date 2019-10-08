package trust.blockchain.entity;

/* compiled from: SwapPayload.kt */
public enum SwapDirection {
    NONE(0),
    BUY(1),
    SELL(2);
    
    private final long value;

    private SwapDirection(long j) {
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }

    public final long value() {
        return this.value;
    }
}
