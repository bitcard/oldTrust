package trust.blockchain.blockchain.theta.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThetaModels.kt */
public final class ThetaTransaction {
    @SerializedName("block_height")
    private final long blockHeight;
    private final String status;

    public ThetaTransaction(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "status");
        this.status = str;
        this.blockHeight = j;
    }

    public static /* synthetic */ ThetaTransaction copy$default(ThetaTransaction thetaTransaction, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = thetaTransaction.status;
        }
        if ((i & 2) != 0) {
            j = thetaTransaction.blockHeight;
        }
        return thetaTransaction.copy(str, j);
    }

    public final String component1() {
        return this.status;
    }

    public final long component2() {
        return this.blockHeight;
    }

    public final ThetaTransaction copy(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "status");
        return new ThetaTransaction(str, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ThetaTransaction) {
                ThetaTransaction thetaTransaction = (ThetaTransaction) obj;
                if (Intrinsics.areEqual(this.status, thetaTransaction.status)) {
                    if (this.blockHeight == thetaTransaction.blockHeight) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final long getBlockHeight() {
        return this.blockHeight;
    }

    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.blockHeight;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ThetaTransaction(status=");
        stringBuilder.append(this.status);
        stringBuilder.append(", blockHeight=");
        stringBuilder.append(this.blockHeight);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
