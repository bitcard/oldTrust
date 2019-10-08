package trust.blockchain.blockchain.tezos.entity;

/* compiled from: TezosModels.kt */
public final class TezosBlockHeader {
    private final long level;

    public TezosBlockHeader(long j) {
        this.level = j;
    }

    public static /* synthetic */ TezosBlockHeader copy$default(TezosBlockHeader tezosBlockHeader, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = tezosBlockHeader.level;
        }
        return tezosBlockHeader.copy(j);
    }

    public final long component1() {
        return this.level;
    }

    public final TezosBlockHeader copy(long j) {
        return new TezosBlockHeader(j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosBlockHeader) {
                if (this.level == ((TezosBlockHeader) obj).level) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getLevel() {
        return this.level;
    }

    public int hashCode() {
        long j = this.level;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosBlockHeader(level=");
        stringBuilder.append(this.level);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
