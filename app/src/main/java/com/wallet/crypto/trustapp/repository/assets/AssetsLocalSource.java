package com.wallet.crypto.trustapp.repository.assets;

import com.wallet.crypto.trustapp.entity.AssetStatus;
import com.wallet.crypto.trustapp.entity.Session;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Ticker;

public interface AssetsLocalSource {
    void delete(Session session, Asset asset);

    Ticker[] findTickers(Session session, Asset[] assetArr);

    Asset[] getActive(Session session);

    Asset[] getAll(Session session);

    Asset getAssetById(Session session, String str);

    AssetStatus getCoinStatus(Session session, Asset asset);

    void markPopupAsShowed(Session session, Asset asset);

    void saveAssets(Session session, Asset[] assetArr);

    void saveCoinStatus(Session session, Asset asset, AssetStatus assetStatus);

    void saveTickers(Session session, Ticker[] tickerArr);

    void setEnable(Session session, Asset asset, boolean z);

    void update(Session session, Asset[] assetArr);

    void updateAssetBalance(Session session, Asset[] assetArr);
}
