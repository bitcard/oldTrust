package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosHead {
    private final String hash;
    private final TezosBlockHeader header;

    public TezosHead(String str, TezosBlockHeader tezosBlockHeader) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(tezosBlockHeader, "header");
        this.hash = str;
        this.header = tezosBlockHeader;
    }

    public static /* synthetic */ TezosHead copy$default(TezosHead tezosHead, String str, TezosBlockHeader tezosBlockHeader, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosHead.hash;
        }
        if ((i & 2) != 0) {
            tezosBlockHeader = tezosHead.header;
        }
        return tezosHead.copy(str, tezosBlockHeader);
    }

    public final String component1() {
        return this.hash;
    }

    public final TezosBlockHeader component2() {
        return this.header;
    }

    public final TezosHead copy(String str, TezosBlockHeader tezosBlockHeader) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(tezosBlockHeader, "header");
        return new TezosHead(str, tezosBlockHeader);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosHead) {
                TezosHead tezosHead = (TezosHead) obj;
                if (Intrinsics.areEqual(this.hash, tezosHead.hash) && Intrinsics.areEqual(this.header, tezosHead.header)) {
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

    public final TezosBlockHeader getHeader() {
        return this.header;
    }

    public int hashCode() {
        String str = this.hash;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        TezosBlockHeader tezosBlockHeader = this.header;
        if (tezosBlockHeader != null) {
            i = tezosBlockHeader.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosHead(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(", header=");
        stringBuilder.append(this.header);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
