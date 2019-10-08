package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosOperationError {
    private final String kind;

    public TezosOperationError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "kind");
        this.kind = str;
    }

    public static /* synthetic */ TezosOperationError copy$default(TezosOperationError tezosOperationError, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosOperationError.kind;
        }
        return tezosOperationError.copy(str);
    }

    public final String component1() {
        return this.kind;
    }

    public final TezosOperationError copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "kind");
        return new TezosOperationError(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosOperationError) {
                if (Intrinsics.areEqual(this.kind, ((TezosOperationError) obj).kind)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getKind() {
        return this.kind;
    }

    public int hashCode() {
        String str = this.kind;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosOperationError(kind=");
        stringBuilder.append(this.kind);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
