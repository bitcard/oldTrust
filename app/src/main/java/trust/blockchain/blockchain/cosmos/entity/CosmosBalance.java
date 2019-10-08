package trust.blockchain.blockchain.cosmos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: CosmosModels.kt */
public final class CosmosBalance {
    private final String amount;
    private final String denom;

    public CosmosBalance(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "denom");
        Intrinsics.checkParameterIsNotNull(str2, "amount");
        this.denom = str;
        this.amount = str2;
    }

    public static /* synthetic */ CosmosBalance copy$default(CosmosBalance cosmosBalance, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cosmosBalance.denom;
        }
        if ((i & 2) != 0) {
            str2 = cosmosBalance.amount;
        }
        return cosmosBalance.copy(str, str2);
    }

    public final String component1() {
        return this.denom;
    }

    public final String component2() {
        return this.amount;
    }

    public final CosmosBalance copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "denom");
        Intrinsics.checkParameterIsNotNull(str2, "amount");
        return new CosmosBalance(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosBalance) {
                CosmosBalance cosmosBalance = (CosmosBalance) obj;
                if (Intrinsics.areEqual(this.denom, cosmosBalance.denom) && Intrinsics.areEqual(this.amount, cosmosBalance.amount)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getDenom() {
        return this.denom;
    }

    public int hashCode() {
        String str = this.denom;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.amount;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosBalance(denom=");
        stringBuilder.append(this.denom);
        stringBuilder.append(", amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
