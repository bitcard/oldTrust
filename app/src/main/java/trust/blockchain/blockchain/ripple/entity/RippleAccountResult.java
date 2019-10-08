package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class RippleAccountResult {
    @SerializedName("account_data")
    private final RippleAccountData AccountData;
    @SerializedName("error_message")
    private final String errorMessage;
    private final long ledger_current_index;
    private final boolean validated;

    public RippleAccountResult(String str, RippleAccountData rippleAccountData, boolean z, long j) {
        Intrinsics.checkParameterIsNotNull(rippleAccountData, "AccountData");
        this.errorMessage = str;
        this.AccountData = rippleAccountData;
        this.validated = z;
        this.ledger_current_index = j;
    }

    public final RippleAccountData getAccountData() {
        return this.AccountData;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final long getLedger_current_index() {
        return this.ledger_current_index;
    }

    public final boolean getValidated() {
        return this.validated;
    }
}
