package com.wallet.crypto.trustapp.repository.network;

import android.os.Handler;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.service.TransactionsServiceAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Asset;

/* compiled from: BlockchainRepositoryType.kt */
public class BlockchainRepositoryType extends NodeRepositoryType {
    public BlockchainRepositoryType(Handler handler, SessionRepository sessionRepository, TransactionsRepository transactionsRepository, TransactionsServiceAdapter transactionsServiceAdapter, RpcService... rpcServiceArr) {
//        Intrinsics.checkParameterIsNotNull(rpcServiceArr, "services");
        super(handler, sessionRepository, transactionsRepository, transactionsServiceAdapter, (RpcService[]) Arrays.copyOf(rpcServiceArr, rpcServiceArr.length));
    }

    public Asset[] loadBalances(Slip slip, Asset[] assetArr) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        Intrinsics.checkParameterIsNotNull(assetArr, "assets");
        RpcService service = getService(slip);
        Collection arrayList = new ArrayList();
        for (Asset asset : assetArr) {
            if ((asset.coin() == slip ? 1 : 0) != 0) {
                arrayList.add(asset);
            }
        }
        Object[] toArray = ((List) arrayList).toArray(new Asset[0]);
        if (toArray != null) {
            Asset[] loadBalances = service.loadBalances(slip, (Asset[]) toArray);
            Intrinsics.checkExpressionValueIsNotNull(loadBalances, "getService(coin).loadBal…== coin }.toTypedArray())");
            return loadBalances;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
