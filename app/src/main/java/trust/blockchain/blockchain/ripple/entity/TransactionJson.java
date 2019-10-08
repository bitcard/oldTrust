package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class TransactionJson {
    @SerializedName("error_message")
    private final String errorMessage;
    private final String hash;

    public TransactionJson(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str2, "hash");
        this.errorMessage = str;
        this.hash = str2;
    }

    public static /* synthetic */ TransactionJson copy$default(TransactionJson transactionJson, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = transactionJson.errorMessage;
        }
        if ((i & 2) != 0) {
            str2 = transactionJson.hash;
        }
        return transactionJson.copy(str, str2);
    }

    public final String component1() {
        return this.errorMessage;
    }

    public final String component2() {
        return this.hash;
    }

    public final TransactionJson copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str2, "hash");
        return new TransactionJson(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TransactionJson) {
                TransactionJson transactionJson = (TransactionJson) obj;
                if (Intrinsics.areEqual(this.errorMessage, transactionJson.errorMessage) && Intrinsics.areEqual(this.hash, transactionJson.hash)) {
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

    public final String getHash() {
        return this.hash;
    }

    public int hashCode() {
        String str = this.errorMessage;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.hash;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TransactionJson(errorMessage=");
        stringBuilder.append(this.errorMessage);
        stringBuilder.append(", hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
