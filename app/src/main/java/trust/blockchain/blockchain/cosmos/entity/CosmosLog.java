package trust.blockchain.blockchain.cosmos.entity;

/* compiled from: CosmosModels.kt */
public final class CosmosLog {
    private final boolean success;

    public CosmosLog(boolean z) {
        this.success = z;
    }

    public static /* synthetic */ CosmosLog copy$default(CosmosLog cosmosLog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = cosmosLog.success;
        }
        return cosmosLog.copy(z);
    }

    public final boolean component1() {
        return this.success;
    }

    public final CosmosLog copy(boolean z) {
        return new CosmosLog(z);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosLog) {
                if (this.success == ((CosmosLog) obj).success) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public int hashCode() {
        boolean z = this.success;
        return z ? 1 : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosLog(success=");
        stringBuilder.append(this.success);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
