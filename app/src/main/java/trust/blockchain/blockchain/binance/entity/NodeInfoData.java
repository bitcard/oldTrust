package trust.blockchain.blockchain.binance.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class NodeInfoData {
    @SerializedName("network")
    private final String chainId;

    public NodeInfoData(String str) {
        Intrinsics.checkParameterIsNotNull(str, "chainId");
        this.chainId = str;
    }

    public static /* synthetic */ NodeInfoData copy$default(NodeInfoData nodeInfoData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = nodeInfoData.chainId;
        }
        return nodeInfoData.copy(str);
    }

    public final String component1() {
        return this.chainId;
    }

    public final NodeInfoData copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "chainId");
        return new NodeInfoData(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NodeInfoData) {
                if (Intrinsics.areEqual(this.chainId, ((NodeInfoData) obj).chainId)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getChainId() {
        return this.chainId;
    }

    public int hashCode() {
        String str = this.chainId;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NodeInfoData(chainId=");
        stringBuilder.append(this.chainId);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
