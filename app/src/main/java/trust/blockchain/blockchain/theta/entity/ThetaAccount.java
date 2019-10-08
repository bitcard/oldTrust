package trust.blockchain.blockchain.theta.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThetaModels.kt */
public final class ThetaAccount {
    private final ThetaCoins coins;
    private final long sequence;

    public ThetaAccount(long j, ThetaCoins thetaCoins) {
        Intrinsics.checkParameterIsNotNull(thetaCoins, "coins");
        this.sequence = j;
        this.coins = thetaCoins;
    }

    public static /* synthetic */ ThetaAccount copy$default(ThetaAccount thetaAccount, long j, ThetaCoins thetaCoins, int i, Object obj) {
        if ((i & 1) != 0) {
            j = thetaAccount.sequence;
        }
        if ((i & 2) != 0) {
            thetaCoins = thetaAccount.coins;
        }
        return thetaAccount.copy(j, thetaCoins);
    }

    public final long component1() {
        return this.sequence;
    }

    public final ThetaCoins component2() {
        return this.coins;
    }

    public final ThetaAccount copy(long j, ThetaCoins thetaCoins) {
        Intrinsics.checkParameterIsNotNull(thetaCoins, "coins");
        return new ThetaAccount(j, thetaCoins);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ThetaAccount) {
                ThetaAccount thetaAccount = (ThetaAccount) obj;
                if ((this.sequence == thetaAccount.sequence) && Intrinsics.areEqual(this.coins, thetaAccount.coins)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final ThetaCoins getCoins() {
        return this.coins;
    }

    public final long getSequence() {
        return this.sequence;
    }

    public int hashCode() {
        long j = this.sequence;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        ThetaCoins thetaCoins = this.coins;
        return i + (thetaCoins != null ? thetaCoins.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ThetaAccount(sequence=");
        stringBuilder.append(this.sequence);
        stringBuilder.append(", coins=");
        stringBuilder.append(this.coins);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
