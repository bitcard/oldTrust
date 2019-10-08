package trust.blockchain.blockchain.zilliqa.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaBalanceResult {
    private final String balance;
    private final long nonce;

    public ZilliqaBalanceResult(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        this.balance = str;
        this.nonce = j;
    }

    public static /* synthetic */ ZilliqaBalanceResult copy$default(ZilliqaBalanceResult zilliqaBalanceResult, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = zilliqaBalanceResult.balance;
        }
        if ((i & 2) != 0) {
            j = zilliqaBalanceResult.nonce;
        }
        return zilliqaBalanceResult.copy(str, j);
    }

    public final String component1() {
        return this.balance;
    }

    public final long component2() {
        return this.nonce;
    }

    public final ZilliqaBalanceResult copy(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        return new ZilliqaBalanceResult(str, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaBalanceResult) {
                ZilliqaBalanceResult zilliqaBalanceResult = (ZilliqaBalanceResult) obj;
                if (Intrinsics.areEqual(this.balance, zilliqaBalanceResult.balance)) {
                    if (this.nonce == zilliqaBalanceResult.nonce) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getBalance() {
        return this.balance;
    }

    public final long getNonce() {
        return this.nonce;
    }

    public int hashCode() {
        String str = this.balance;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.nonce;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZilliqaBalanceResult(balance=");
        stringBuilder.append(this.balance);
        stringBuilder.append(", nonce=");
        stringBuilder.append(this.nonce);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
