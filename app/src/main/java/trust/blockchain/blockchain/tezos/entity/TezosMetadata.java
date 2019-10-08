package trust.blockchain.blockchain.tezos.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosMetadata {
    @SerializedName("operation_result")
    private final TezosOperationResult operationResult;

    public TezosMetadata(TezosOperationResult tezosOperationResult) {
        Intrinsics.checkParameterIsNotNull(tezosOperationResult, "operationResult");
        this.operationResult = tezosOperationResult;
    }

    public static /* synthetic */ TezosMetadata copy$default(TezosMetadata tezosMetadata, TezosOperationResult tezosOperationResult, int i, Object obj) {
        if ((i & 1) != 0) {
            tezosOperationResult = tezosMetadata.operationResult;
        }
        return tezosMetadata.copy(tezosOperationResult);
    }

    public final TezosOperationResult component1() {
        return this.operationResult;
    }

    public final TezosMetadata copy(TezosOperationResult tezosOperationResult) {
        Intrinsics.checkParameterIsNotNull(tezosOperationResult, "operationResult");
        return new TezosMetadata(tezosOperationResult);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosMetadata) {
                if (Intrinsics.areEqual(this.operationResult, ((TezosMetadata) obj).operationResult)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final TezosOperationResult getOperationResult() {
        return this.operationResult;
    }

    public int hashCode() {
        TezosOperationResult tezosOperationResult = this.operationResult;
        return tezosOperationResult != null ? tezosOperationResult.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosMetadata(operationResult=");
        stringBuilder.append(this.operationResult);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
