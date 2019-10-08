package trust.blockchain.blockchain.zilliqa.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaSentTransactionResult {
    @SerializedName("TranID")
    private final String hash;

    public ZilliqaSentTransactionResult(String str) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        this.hash = str;
    }

    public static /* synthetic */ ZilliqaSentTransactionResult copy$default(ZilliqaSentTransactionResult zilliqaSentTransactionResult, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = zilliqaSentTransactionResult.hash;
        }
        return zilliqaSentTransactionResult.copy(str);
    }

    public final String component1() {
        return this.hash;
    }

    public final ZilliqaSentTransactionResult copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        return new ZilliqaSentTransactionResult(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaSentTransactionResult) {
                if (Intrinsics.areEqual(this.hash, ((ZilliqaSentTransactionResult) obj).hash)) {
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
        stringBuilder.append("ZilliqaSentTransactionResult(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
