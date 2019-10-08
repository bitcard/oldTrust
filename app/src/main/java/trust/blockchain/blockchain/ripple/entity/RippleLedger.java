package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class RippleLedger {
    @SerializedName("error_message")
    private final String errorMessage;
    @SerializedName("ledger_index")
    private final long ledgerIndex;

    public RippleLedger(String str, long j) {
        this.errorMessage = str;
        this.ledgerIndex = j;
    }

    public static /* synthetic */ RippleLedger copy$default(RippleLedger rippleLedger, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rippleLedger.errorMessage;
        }
        if ((i & 2) != 0) {
            j = rippleLedger.ledgerIndex;
        }
        return rippleLedger.copy(str, j);
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final long component2() {
        return this.ledgerIndex;
    }

    public final RippleLedger copy(String str, long j) {
        return new RippleLedger(str, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RippleLedger) {
                RippleLedger rippleLedger = (RippleLedger) obj;
                if (Intrinsics.areEqual(this.errorMessage, rippleLedger.errorMessage)) {
                    if (this.ledgerIndex == rippleLedger.ledgerIndex) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final long getLedgerIndex() {
        return this.ledgerIndex;
    }

    public int hashCode() {
        String str = this.errorMessage;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.ledgerIndex;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RippleLedger(errorMessage=");
        stringBuilder.append(this.errorMessage);
        stringBuilder.append(", ledgerIndex=");
        stringBuilder.append(this.ledgerIndex);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
