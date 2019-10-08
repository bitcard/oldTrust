package trust.blockchain.blockchain.nimiq.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: NimiqModels.kt */
public final class NimiqError {
    private final String message;

    public NimiqError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.message = str;
    }

    public static /* synthetic */ NimiqError copy$default(NimiqError nimiqError, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = nimiqError.message;
        }
        return nimiqError.copy(str);
    }

    public final String component1() {
        return this.message;
    }

    public final NimiqError copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        return new NimiqError(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NimiqError) {
                if (Intrinsics.areEqual(this.message, ((NimiqError) obj).message)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String str = this.message;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NimiqError(message=");
        stringBuilder.append(this.message);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
