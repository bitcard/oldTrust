package com.wallet.crypto.trustapp.service.rpc.waves.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: WavesModels.kt */
public final class WavesSentTransaction {
    private final String id;

    public WavesSentTransaction(String str) {
        this.id = str;
    }

    public static /* synthetic */ WavesSentTransaction copy$default(WavesSentTransaction wavesSentTransaction, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wavesSentTransaction.id;
        }
        return wavesSentTransaction.copy(str);
    }

    public final String component1() {
        return this.id;
    }

    public final WavesSentTransaction copy(String str) {
        return new WavesSentTransaction(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof WavesSentTransaction) {
                if (Intrinsics.areEqual(this.id, ((WavesSentTransaction) obj).id)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WavesSentTransaction(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
