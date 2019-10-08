package trust.blockchain.blockchain.binance.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class BroadcastResult {
    private final String hash;

    public BroadcastResult(String str) {
        this.hash = str;
    }

    public static /* synthetic */ BroadcastResult copy$default(BroadcastResult broadcastResult, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = broadcastResult.hash;
        }
        return broadcastResult.copy(str);
    }

    public final String component1() {
        return this.hash;
    }

    public final BroadcastResult copy(String str) {
        return new BroadcastResult(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BroadcastResult) {
                if (Intrinsics.areEqual(this.hash, ((BroadcastResult) obj).hash)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getHash() {
        return this.hash;
    }

    public int hashCode() {
        String str = this.hash;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BroadcastResult(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
