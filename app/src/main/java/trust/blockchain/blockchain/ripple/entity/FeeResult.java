package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class FeeResult {
    private final Drops drops;
    @SerializedName("error_message")
    private final String errorMessage;

    public FeeResult(String str, Drops drops) {
        Intrinsics.checkParameterIsNotNull(drops, "drops");
        this.errorMessage = str;
        this.drops = drops;
    }

    public static /* synthetic */ FeeResult copy$default(FeeResult feeResult, String str, Drops drops, int i, Object obj) {
        if ((i & 1) != 0) {
            str = feeResult.errorMessage;
        }
        if ((i & 2) != 0) {
            drops = feeResult.drops;
        }
        return feeResult.copy(str, drops);
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final Drops component2() {
        return this.drops;
    }

    public final FeeResult copy(String str, Drops drops) {
        Intrinsics.checkParameterIsNotNull(drops, "drops");
        return new FeeResult(str, drops);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof FeeResult) {
                FeeResult feeResult = (FeeResult) obj;
                if (Intrinsics.areEqual(this.errorMessage, feeResult.errorMessage) && Intrinsics.areEqual(this.drops, feeResult.drops)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final Drops getDrops() {
        return this.drops;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public int hashCode() {
        String str = this.errorMessage;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Drops drops = this.drops;
        if (drops != null) {
            i = drops.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FeeResult(errorMessage=");
        stringBuilder.append(this.errorMessage);
        stringBuilder.append(", drops=");
        stringBuilder.append(this.drops);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
