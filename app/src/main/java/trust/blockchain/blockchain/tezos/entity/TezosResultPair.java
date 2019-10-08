package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosResultPair {
    private final boolean confirmed;
    private final String message;

    public TezosResultPair(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.confirmed = z;
        this.message = str;
    }

    public static /* synthetic */ TezosResultPair copy$default(TezosResultPair tezosResultPair, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = tezosResultPair.confirmed;
        }
        if ((i & 2) != 0) {
            str = tezosResultPair.message;
        }
        return tezosResultPair.copy(z, str);
    }

    public final boolean component1() {
        return this.confirmed;
    }

    public final String component2() {
        return this.message;
    }

    public final TezosResultPair copy(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        return new TezosResultPair(z, str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosResultPair) {
                TezosResultPair tezosResultPair = (TezosResultPair) obj;
                if ((this.confirmed == tezosResultPair.confirmed) && Intrinsics.areEqual(this.message, tezosResultPair.message)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final boolean getConfirmed() {
        return this.confirmed;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int i = this.confirmed ? 1 : 0;
        i *= 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosResultPair(confirmed=");
        stringBuilder.append(this.confirmed);
        stringBuilder.append(", message=");
        stringBuilder.append(this.message);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
