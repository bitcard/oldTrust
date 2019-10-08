package com.wallet.crypto.trustapp.service.rpc.waves.entity;

/* compiled from: WavesModels.kt */
public final class WavesBalance {
    private final long balance;

    public WavesBalance(long j) {
        this.balance = j;
    }

    public static /* synthetic */ WavesBalance copy$default(WavesBalance wavesBalance, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = wavesBalance.balance;
        }
        return wavesBalance.copy(j);
    }

    public final long component1() {
        return this.balance;
    }

    public final WavesBalance copy(long j) {
        return new WavesBalance(j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof WavesBalance) {
                if (this.balance == ((WavesBalance) obj).balance) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getBalance() {
        return this.balance;
    }

    public int hashCode() {
        long j = this.balance;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WavesBalance(balance=");
        stringBuilder.append(this.balance);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
