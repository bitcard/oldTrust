package trust.blockchain.blockchain.zilliqa.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaRpcError {
    private final long code;
    private final String message;

    public ZilliqaRpcError(long j, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.code = j;
        this.message = str;
    }

    public static /* synthetic */ ZilliqaRpcError copy$default(ZilliqaRpcError zilliqaRpcError, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = zilliqaRpcError.code;
        }
        if ((i & 2) != 0) {
            str = zilliqaRpcError.message;
        }
        return zilliqaRpcError.copy(j, str);
    }

    public final long component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final ZilliqaRpcError copy(long j, String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        return new ZilliqaRpcError(j, str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaRpcError) {
                ZilliqaRpcError zilliqaRpcError = (ZilliqaRpcError) obj;
                if ((this.code == zilliqaRpcError.code) && Intrinsics.areEqual(this.message, zilliqaRpcError.message)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        long j = this.code;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZilliqaRpcError(code=");
        stringBuilder.append(this.code);
        stringBuilder.append(", message=");
        stringBuilder.append(this.message);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
