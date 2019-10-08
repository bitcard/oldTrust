package com.wallet.crypto.trustapp.service;

import com.wallet.crypto.trustapp.repository.entity.RealmAccountIndex;
import com.wallet.crypto.trustapp.service.RealmManager.WalletsOperation;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Bip32Path;

/* compiled from: RealmAccountIndices.kt */
final class RealmAccountIndices$get$1<T> implements WalletsOperation<Unit> {
    final /* synthetic */ String[] $inValues;
    final /* synthetic */ HashMap $pathList;

    RealmAccountIndices$get$1(String[] strArr, HashMap hashMap) {
        this.$inValues = strArr;
        this.$pathList = hashMap;
    }

    @Override
    public Unit execute(Realm realm) {
        RealmResults<RealmAccountIndex> findAll = realm.where(RealmAccountIndex.class).in("publicKey", this.$inValues).findAll();
        Intrinsics.checkExpressionValueIsNotNull(findAll, "result");
        for (RealmAccountIndex realmAccountIndex : findAll) {
            HashMap hashMap = this.$pathList;
            Intrinsics.checkExpressionValueIsNotNull(realmAccountIndex, "accountIndex");
            ArrayList arrayList = (ArrayList) hashMap.get(realmAccountIndex.getPublicKey());
            if (arrayList != null) {
                String address = realmAccountIndex.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address, "accountIndex.address");
                String path = realmAccountIndex.getPath();
                Intrinsics.checkExpressionValueIsNotNull(path, "accountIndex.path");
                arrayList.add(new Bip32Path(address, 0, path));
            }
        }

        return Unit.INSTANCE;
    }
}
