package trust.blockchain.blockchain.cosmos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: CosmosModels.kt */
public final class CosmosAccount {
    private final CosmosAccountValue value;

    public CosmosAccount(CosmosAccountValue cosmosAccountValue) {
        Intrinsics.checkParameterIsNotNull(cosmosAccountValue, "value");
        this.value = cosmosAccountValue;
    }

    public static /* synthetic */ CosmosAccount copy$default(CosmosAccount cosmosAccount, CosmosAccountValue cosmosAccountValue, int i, Object obj) {
        if ((i & 1) != 0) {
            cosmosAccountValue = cosmosAccount.value;
        }
        return cosmosAccount.copy(cosmosAccountValue);
    }

    public final CosmosAccountValue component1() {
        return this.value;
    }

    public final CosmosAccount copy(CosmosAccountValue cosmosAccountValue) {
        Intrinsics.checkParameterIsNotNull(cosmosAccountValue, "value");
        return new CosmosAccount(cosmosAccountValue);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosAccount) {
                if (Intrinsics.areEqual(this.value, ((CosmosAccount) obj).value)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final CosmosAccountValue getValue() {
        return this.value;
    }

    public int hashCode() {
        CosmosAccountValue cosmosAccountValue = this.value;
        return cosmosAccountValue != null ? cosmosAccountValue.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosAccount(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
