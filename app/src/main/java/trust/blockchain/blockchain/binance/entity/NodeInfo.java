package trust.blockchain.blockchain.binance.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinanceModels.kt */
public final class NodeInfo {
    @SerializedName("node_info")
    private final NodeInfoData nodeInfo;
    @SerializedName("sync_info")
    private final SyncInfo syncInfo;

    public NodeInfo(SyncInfo syncInfo, NodeInfoData nodeInfoData) {
        Intrinsics.checkParameterIsNotNull(syncInfo, "syncInfo");
        Intrinsics.checkParameterIsNotNull(nodeInfoData, "nodeInfo");
        this.syncInfo = syncInfo;
        this.nodeInfo = nodeInfoData;
    }

    public static /* synthetic */ NodeInfo copy$default(NodeInfo nodeInfo, SyncInfo syncInfo, NodeInfoData nodeInfoData, int i, Object obj) {
        if ((i & 1) != 0) {
            syncInfo = nodeInfo.syncInfo;
        }
        if ((i & 2) != 0) {
            nodeInfoData = nodeInfo.nodeInfo;
        }
        return nodeInfo.copy(syncInfo, nodeInfoData);
    }

    public final SyncInfo component1() {
        return this.syncInfo;
    }

    public final NodeInfoData component2() {
        return this.nodeInfo;
    }

    public final NodeInfo copy(SyncInfo syncInfo, NodeInfoData nodeInfoData) {
        Intrinsics.checkParameterIsNotNull(syncInfo, "syncInfo");
        Intrinsics.checkParameterIsNotNull(nodeInfoData, "nodeInfo");
        return new NodeInfo(syncInfo, nodeInfoData);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NodeInfo) {
                NodeInfo nodeInfo = (NodeInfo) obj;
                if (Intrinsics.areEqual(this.syncInfo, nodeInfo.syncInfo) && Intrinsics.areEqual(this.nodeInfo, nodeInfo.nodeInfo)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final NodeInfoData getNodeInfo() {
        return this.nodeInfo;
    }

    public final SyncInfo getSyncInfo() {
        return this.syncInfo;
    }

    public int hashCode() {
        SyncInfo syncInfo = this.syncInfo;
        int i = 0;
        int hashCode = (syncInfo != null ? syncInfo.hashCode() : 0) * 31;
        NodeInfoData nodeInfoData = this.nodeInfo;
        if (nodeInfoData != null) {
            i = nodeInfoData.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NodeInfo(syncInfo=");
        stringBuilder.append(this.syncInfo);
        stringBuilder.append(", nodeInfo=");
        stringBuilder.append(this.nodeInfo);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
