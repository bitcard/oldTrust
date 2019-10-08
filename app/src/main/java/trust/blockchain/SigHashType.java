package trust.blockchain;

/* compiled from: SigHashType.kt */
public enum SigHashType {
    ALL(1),
    NONE(2),
    FORK(64);
    
    private final int value;

    private SigHashType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
