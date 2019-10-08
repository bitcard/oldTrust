package trust.blockchain.blockchain.cosmos.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: CosmosModels.kt */
public final class CosmosNodeInfo {
    private final String network;

    public CosmosNodeInfo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "network");
        this.network = str;
    }

    public static /* synthetic */ CosmosNodeInfo copy$default(CosmosNodeInfo cosmosNodeInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cosmosNodeInfo.network;
        }
        return cosmosNodeInfo.copy(str);
    }

    public final String component1() {
        return this.network;
    }

    public final CosmosNodeInfo copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "network");
        return new CosmosNodeInfo(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CosmosNodeInfo) {
                if (Intrinsics.areEqual(this.network, ((CosmosNodeInfo) obj).network)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getNetwork() {
        return this.network;
    }

    public int hashCode() {
        String str = this.network;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CosmosNodeInfo(network=");
        stringBuilder.append(this.network);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
