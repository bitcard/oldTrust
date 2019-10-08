package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosError {
    private final String error;
    private final String kind;

    public TezosError(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "kind");
        Intrinsics.checkParameterIsNotNull(str2, "error");
        this.kind = str;
        this.error = str2;
    }

    public static /* synthetic */ TezosError copy$default(TezosError tezosError, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosError.kind;
        }
        if ((i & 2) != 0) {
            str2 = tezosError.error;
        }
        return tezosError.copy(str, str2);
    }

    public final String component1() {
        return this.kind;
    }

    public final String component2() {
        return this.error;
    }

    public final TezosError copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "kind");
        Intrinsics.checkParameterIsNotNull(str2, "error");
        return new TezosError(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosError) {
                TezosError tezosError = (TezosError) obj;
                if (Intrinsics.areEqual(this.kind, tezosError.kind) && Intrinsics.areEqual(this.error, tezosError.error)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getError() {
        return this.error;
    }

    public final String getKind() {
        return this.kind;
    }

    public int hashCode() {
        String str = this.kind;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.error;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosError(kind=");
        stringBuilder.append(this.kind);
        stringBuilder.append(", error=");
        stringBuilder.append(this.error);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
