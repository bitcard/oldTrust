package com.wallet.crypto.trustapp.service.rpc.waves.entity;

/* compiled from: WavesModels.kt */
public final class WavesTransactionInfo {
    private final long height;

    public WavesTransactionInfo(long j) {
        this.height = j;
    }

    public static /* synthetic */ WavesTransactionInfo copy$default(WavesTransactionInfo wavesTransactionInfo, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = wavesTransactionInfo.height;
        }
        return wavesTransactionInfo.copy(j);
    }

    public final long component1() {
        return this.height;
    }

    public final WavesTransactionInfo copy(long j) {
        return new WavesTransactionInfo(j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof WavesTransactionInfo) {
                if (this.height == ((WavesTransactionInfo) obj).height) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getHeight() {
        return this.height;
    }

    public int hashCode() {
        long j = this.height;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WavesTransactionInfo(height=");
        stringBuilder.append(this.height);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
