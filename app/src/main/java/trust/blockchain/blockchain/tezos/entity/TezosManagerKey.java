package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosManagerKey {
    private final String key;

    public TezosManagerKey(String str) {
        this.key = str;
    }

    public static /* synthetic */ TezosManagerKey copy$default(TezosManagerKey tezosManagerKey, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosManagerKey.key;
        }
        return tezosManagerKey.copy(str);
    }

    public final String component1() {
        return this.key;
    }

    public final TezosManagerKey copy(String str) {
        return new TezosManagerKey(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosManagerKey) {
                if (Intrinsics.areEqual(this.key, ((TezosManagerKey) obj).key)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getKey() {
        return this.key;
    }

    public int hashCode() {
        String str = this.key;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosManagerKey(key=");
        stringBuilder.append(this.key);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
