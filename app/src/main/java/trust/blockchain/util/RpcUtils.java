package trust.blockchain.util;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: RpcUtils.kt */
public final class RpcUtils {
    public static final RpcUtils INSTANCE = new RpcUtils();

    private RpcUtils() {
    }

    public final Asset[] zipToBalance(Asset asset, Asset asset2) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Intrinsics.checkParameterIsNotNull(asset2, "energy");
        if (Intrinsics.areEqual(asset.id(), asset2.id())) {
            return new Asset[]{asset};
        }
        return new Asset[]{asset, asset2};
    }
}
