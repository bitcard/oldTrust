package trust.blockchain.blockchain.cosmos.entity;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CosmosModels.kt */
public final class CosmosTransaction {
    private final String height;
    private final List<CosmosLog> logs;

    public CosmosTransaction(String str, List<CosmosLog> list) {
        Intrinsics.checkParameterIsNotNull(str, "height");
        Intrinsics.checkParameterIsNotNull(list, "logs");
        this.height = str;
        this.logs = list;
    }

    public static /* synthetic */ CosmosTransaction copy$default(CosmosTransaction cosmosTransaction, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cosmosTransaction.height;
        }
        if ((i & 2) != 0) {
            list = cosmosTransaction.logs;
        }
        return cosmosTransaction.copy(str, list);
    }

    public final String component1() {
        return this.height;
    }

    public final List<CosmosLog> component2() {
        return this.logs;
    }

    public final CosmosTransaction copy(String str, List<CosmosLog> list) {
        Intrinsics.checkParameterIsNotNull(str, "height");
        Intrinsics.checkParameterIsNotNull(list, "logs");
        return new CosmosTransaction(str, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosTransaction) {
                CosmosTransaction cosmosTransaction = (CosmosTransaction) obj;
                if (Intrinsics.areEqual(this.height, cosmosTransaction.height) && Intrinsics.areEqual(this.logs, cosmosTransaction.logs)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getHeight() {
        return this.height;
    }

    public final List<CosmosLog> getLogs() {
        return this.logs;
    }

    public int hashCode() {
        String str = this.height;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List list = this.logs;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosTransaction(height=");
        stringBuilder.append(this.height);
        stringBuilder.append(", logs=");
        stringBuilder.append(this.logs);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
