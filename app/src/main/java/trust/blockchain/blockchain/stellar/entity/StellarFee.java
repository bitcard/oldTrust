package trust.blockchain.blockchain.stellar.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StellarModels.kt */
public final class StellarFee {
    @SerializedName("mode_accepted_fee")
    private final String acceptedFee;

    public StellarFee(String str) {
        Intrinsics.checkParameterIsNotNull(str, "acceptedFee");
        this.acceptedFee = str;
    }

    public static /* synthetic */ StellarFee copy$default(StellarFee stellarFee, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stellarFee.acceptedFee;
        }
        return stellarFee.copy(str);
    }

    public final String component1() {
        return this.acceptedFee;
    }

    public final StellarFee copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "acceptedFee");
        return new StellarFee(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof StellarFee) {
                if (Intrinsics.areEqual(this.acceptedFee, ((StellarFee) obj).acceptedFee)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getAcceptedFee() {
        return this.acceptedFee;
    }

    public int hashCode() {
        String str = this.acceptedFee;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StellarFee(acceptedFee=");
        stringBuilder.append(this.acceptedFee);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
