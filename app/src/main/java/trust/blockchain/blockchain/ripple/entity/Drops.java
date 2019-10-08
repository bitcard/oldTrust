package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class Drops {
    @SerializedName("error_message")
    private final String errorMessage;
    @SerializedName("median_fee")
    private final String medianFee;

    public Drops(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str2, "medianFee");
        this.errorMessage = str;
        this.medianFee = str2;
    }

    public static /* synthetic */ Drops copy$default(Drops drops, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = drops.errorMessage;
        }
        if ((i & 2) != 0) {
            str2 = drops.medianFee;
        }
        return drops.copy(str, str2);
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final String component2() {
        return this.medianFee;
    }

    public final Drops copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str2, "medianFee");
        return new Drops(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Drops) {
                Drops drops = (Drops) obj;
                if (Intrinsics.areEqual(this.errorMessage, drops.errorMessage) && Intrinsics.areEqual(this.medianFee, drops.medianFee)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final String getMedianFee() {
        return this.medianFee;
    }

    public int hashCode() {
        String str = this.errorMessage;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.medianFee;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Drops(errorMessage=");
        stringBuilder.append(this.errorMessage);
        stringBuilder.append(", medianFee=");
        stringBuilder.append(this.medianFee);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
