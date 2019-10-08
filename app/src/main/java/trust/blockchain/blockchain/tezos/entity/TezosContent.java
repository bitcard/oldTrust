package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosContent {
    private final TezosMetadata metadata;

    public TezosContent(TezosMetadata tezosMetadata) {
        Intrinsics.checkParameterIsNotNull(tezosMetadata, "metadata");
        this.metadata = tezosMetadata;
    }

    public static /* synthetic */ TezosContent copy$default(TezosContent tezosContent, TezosMetadata tezosMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            tezosMetadata = tezosContent.metadata;
        }
        return tezosContent.copy(tezosMetadata);
    }

    public final TezosMetadata component1() {
        return this.metadata;
    }

    public final TezosContent copy(TezosMetadata tezosMetadata) {
        Intrinsics.checkParameterIsNotNull(tezosMetadata, "metadata");
        return new TezosContent(tezosMetadata);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosContent) {
                if (Intrinsics.areEqual(this.metadata, ((TezosContent) obj).metadata)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final TezosMetadata getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        TezosMetadata tezosMetadata = this.metadata;
        return tezosMetadata != null ? tezosMetadata.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosContent(metadata=");
        stringBuilder.append(this.metadata);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
