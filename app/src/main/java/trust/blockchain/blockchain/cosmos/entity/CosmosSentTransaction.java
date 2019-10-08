package trust.blockchain.blockchain.cosmos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: CosmosModels.kt */
public final class CosmosSentTransaction {
    private final String error;
    private final String txhash;

    public CosmosSentTransaction(String str, String str2) {
        this.txhash = str;
        this.error = str2;
    }

    public static /* synthetic */ CosmosSentTransaction copy$default(CosmosSentTransaction cosmosSentTransaction, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cosmosSentTransaction.txhash;
        }
        if ((i & 2) != 0) {
            str2 = cosmosSentTransaction.error;
        }
        return cosmosSentTransaction.copy(str, str2);
    }

    public final String component1() {
        return this.txhash;
    }

    public final String component2() {
        return this.error;
    }

    public final CosmosSentTransaction copy(String str, String str2) {
        return new CosmosSentTransaction(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosSentTransaction) {
                CosmosSentTransaction cosmosSentTransaction = (CosmosSentTransaction) obj;
                if (Intrinsics.areEqual(this.txhash, cosmosSentTransaction.txhash) && Intrinsics.areEqual(this.error, cosmosSentTransaction.error)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getError() {
        return this.error;
    }

    public final String getTxhash() {
        return this.txhash;
    }

    public int hashCode() {
        String str = this.txhash;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.error;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosSentTransaction(txhash=");
        stringBuilder.append(this.txhash);
        stringBuilder.append(", error=");
        stringBuilder.append(this.error);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
