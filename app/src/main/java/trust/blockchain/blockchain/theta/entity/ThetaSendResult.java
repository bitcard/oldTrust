package trust.blockchain.blockchain.theta.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThetaModels.kt */
public final class ThetaSendResult {
    private final String hash;

    public ThetaSendResult(String str) {
        this.hash = str;
    }

    public static /* synthetic */ ThetaSendResult copy$default(ThetaSendResult thetaSendResult, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = thetaSendResult.hash;
        }
        return thetaSendResult.copy(str);
    }

    public final String component1() {
        return this.hash;
    }

    public final ThetaSendResult copy(String str) {
        return new ThetaSendResult(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ThetaSendResult) {
                if (Intrinsics.areEqual(this.hash, ((ThetaSendResult) obj).hash)) {
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
        stringBuilder.append("ThetaSendResult(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
