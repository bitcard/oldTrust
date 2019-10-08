package trust.blockchain.blockchain.tezos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TezosModels.kt */
public final class TezosAccount {
    private final String balance;
    private final long counter;

    public TezosAccount(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        this.balance = str;
        this.counter = j;
    }

    public static /* synthetic */ TezosAccount copy$default(TezosAccount tezosAccount, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tezosAccount.balance;
        }
        if ((i & 2) != 0) {
            j = tezosAccount.counter;
        }
        return tezosAccount.copy(str, j);
    }

    public final String component1() {
        return this.balance;
    }

    public final long component2() {
        return this.counter;
    }

    public final TezosAccount copy(String str, long j) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        return new TezosAccount(str, j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TezosAccount) {
                TezosAccount tezosAccount = (TezosAccount) obj;
                if (Intrinsics.areEqual(this.balance, tezosAccount.balance)) {
                    if (this.counter == tezosAccount.counter) {
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

    public final long getCounter() {
        return this.counter;
    }

    public int hashCode() {
        String str = this.balance;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.counter;
        return hashCode + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TezosAccount(balance=");
        stringBuilder.append(this.balance);
        stringBuilder.append(", counter=");
        stringBuilder.append(this.counter);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
