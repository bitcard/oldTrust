package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class RippleTransaction {
    private final String Account;
    private final String Destination;
    private final String Fee;
    private final boolean Validated;
    @SerializedName("error_message")
    private final String errorMessage;
    @SerializedName("ledger_index")
    private final long ledgerIndex;
    private final String status;

    public RippleTransaction(String str, String str2, String str3, String str4, String str5, boolean z, long j) {
        Intrinsics.checkParameterIsNotNull(str2, "Account");
        Intrinsics.checkParameterIsNotNull(str3, "Destination");
        Intrinsics.checkParameterIsNotNull(str4, "Fee");
        Intrinsics.checkParameterIsNotNull(str5, "status");
        this.errorMessage = str;
        this.Account = str2;
        this.Destination = str3;
        this.Fee = str4;
        this.status = str5;
        this.Validated = z;
        this.ledgerIndex = j;
    }

    public static /* synthetic */ RippleTransaction copy$default(RippleTransaction rippleTransaction, String str, String str2, String str3, String str4, String str5, boolean z, long j, int i, Object obj) {
        RippleTransaction rippleTransaction2 = rippleTransaction;
        return rippleTransaction.copy((i & 1) != 0 ? rippleTransaction2.errorMessage : str, (i & 2) != 0 ? rippleTransaction2.Account : str2, (i & 4) != 0 ? rippleTransaction2.Destination : str3, (i & 8) != 0 ? rippleTransaction2.Fee : str4, (i & 16) != 0 ? rippleTransaction2.status : str5, (i & 32) != 0 ? rippleTransaction2.Validated : z, (i & 64) != 0 ? rippleTransaction2.ledgerIndex : j);
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final String component2() {
        return this.Account;
    }

    public final String component3() {
        return this.Destination;
    }

    public final String component4() {
        return this.Fee;
    }

    public final String component5() {
        return this.status;
    }

    public final boolean component6() {
        return this.Validated;
    }

    public final long component7() {
        return this.ledgerIndex;
    }

    public final RippleTransaction copy(String str, String str2, String str3, String str4, String str5, boolean z, long j) {
        String str6 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "Account");
        String str7 = str3;
        Intrinsics.checkParameterIsNotNull(str3, "Destination");
        String str8 = str4;
        Intrinsics.checkParameterIsNotNull(str4, "Fee");
        String str9 = str5;
        Intrinsics.checkParameterIsNotNull(str5, "status");
        return new RippleTransaction(str, str6, str7, str8, str9, z, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RippleTransaction) {
                RippleTransaction rippleTransaction = (RippleTransaction) obj;
                if (Intrinsics.areEqual(this.errorMessage, rippleTransaction.errorMessage) && Intrinsics.areEqual(this.Account, rippleTransaction.Account) && Intrinsics.areEqual(this.Destination, rippleTransaction.Destination) && Intrinsics.areEqual(this.Fee, rippleTransaction.Fee) && Intrinsics.areEqual(this.status, rippleTransaction.status)) {
                    if (this.Validated == rippleTransaction.Validated) {
                        if (this.ledgerIndex == rippleTransaction.ledgerIndex) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getAccount() {
        return this.Account;
    }

    public final String getDestination() {
        return this.Destination;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final String getFee() {
        return this.Fee;
    }

    public final long getLedgerIndex() {
        return this.ledgerIndex;
    }

    public final String getStatus() {
        return this.status;
    }

    public final boolean getValidated() {
        return this.Validated;
    }

    public int hashCode() {
        String str = this.errorMessage;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.Account;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.Destination;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.Fee;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.status;
        if (str2 != null) {
            i = str2.hashCode();
        }
        hashCode = (hashCode + i) * 31;

        if (this.Validated) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (hashCode + i) * 31;
        long j = this.ledgerIndex;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RippleTransaction(errorMessage=");
        stringBuilder.append(this.errorMessage);
        stringBuilder.append(", Account=");
        stringBuilder.append(this.Account);
        stringBuilder.append(", Destination=");
        stringBuilder.append(this.Destination);
        stringBuilder.append(", Fee=");
        stringBuilder.append(this.Fee);
        stringBuilder.append(", status=");
        stringBuilder.append(this.status);
        stringBuilder.append(", Validated=");
        stringBuilder.append(this.Validated);
        stringBuilder.append(", ledgerIndex=");
        stringBuilder.append(this.ledgerIndex);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
