package com.wallet.crypto.trustapp.repository.assets;

import com.wallet.crypto.trustapp.entity.AssetStatus;
import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.Observable;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Wallet;

/* compiled from: AssetsController.kt */
public interface AssetsController {
    void addAsset(Session session, Asset[] assetArr);

    void addOnCollectionChangeListener(OnCollectionChangeListener onCollectionChangeListener);

    void delete(Session session, Asset asset);

    void enableNoneEmpty(Wallet wallet);

    Ticker[] findTickers(Session session, Asset[] assetArr);

    Asset[] getActive(Session session);

    Asset[] getAll(Session session);

    Asset getAssetById(Session session, String str);

    Observable<AssetStatus> getAssetStatus(Session session, Asset asset);

    void loadAssets(Session session);

    void loadTickers(Session session, boolean z, Asset[] assetArr);

    void markPopupAsShowed(Session session, Asset asset);

    void setEnable(Session session, Asset asset, boolean z);

    Asset[] updateBalances(Session session, Asset[] assetArr, boolean z);
}
