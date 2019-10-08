package trust.blockchain.blockchain.tezos.entity;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosOperation {
    private final List<TezosContent> contents;
    private final String hash;

    public TezosOperation(String str, List<TezosContent> list) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(list, "contents");
        this.hash = str;
        this.contents = list;
    }

    public static /* synthetic */ TezosOperation copy$default(TezosOperation tezosOperation, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosOperation.hash;
        }
        if ((i & 2) != 0) {
            list = tezosOperation.contents;
        }
        return tezosOperation.copy(str, list);
    }

    public final String component1() {
        return this.hash;
    }

    public final List<TezosContent> component2() {
        return this.contents;
    }

    public final TezosOperation copy(String str, List<TezosContent> list) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(list, "contents");
        return new TezosOperation(str, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosOperation) {
                TezosOperation tezosOperation = (TezosOperation) obj;
                if (Intrinsics.areEqual(this.hash, tezosOperation.hash) && Intrinsics.areEqual(this.contents, tezosOperation.contents)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final List<TezosContent> getContents() {
        return this.contents;
    }

    public final String getHash() {
        return this.hash;
    }

    public int hashCode() {
        String str = this.hash;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List list = this.contents;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosOperation(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(", contents=");
        stringBuilder.append(this.contents);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
