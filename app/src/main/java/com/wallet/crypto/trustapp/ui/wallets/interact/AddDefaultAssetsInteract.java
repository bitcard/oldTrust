package com.wallet.crypto.trustapp.ui.wallets.interact;

import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.inject.Inject;

import io.reactivex.functions.Action;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Wallet;

public class AddDefaultAssetsInteract {
    /* renamed from: a */
    private final AssetsController f17051a;

    @Inject
    public AddDefaultAssetsInteract(AssetsController assetsController) {
        this.f17051a = assetsController;
    }

    /* renamed from: b */
    public static /* synthetic */ void m45b(AddDefaultAssetsInteract addDefaultAssetsInteract, Wallet wallet, Account account) throws Exception {
        boolean z = true;
        Asset[] assetArr;
        if (wallet.accounts.length != 1) {
            if (!CoinConfig.f16616a.isEnableByDefault(account.coin)) {
                z = false;
            }
        }

        Asset energyAsset = account.coin.feeCalculator().energyAsset(account);  // r3
        Asset asset = account.coin.coinAsset(account, z);   // r7
        assetArr = (energyAsset != null || energyAsset.contract.address.equals(asset.contract.address)) ? new Asset[]{account.coin.coinAsset(account, z)} : new Asset[]{energyAsset, account.coin.coinAsset(account, z)};
        addDefaultAssetsInteract.f17051a.addAsset(new Session(wallet), assetArr);
    }

    public Single<Wallet> add(Wallet wallet) {
        return Observable
                .fromArray(wallet.accounts)
                .flatMapCompletable(account -> Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        AddDefaultAssetsInteract.m45b(AddDefaultAssetsInteract.this, wallet, account);
                    }
                }))
                .andThen(Single.just(wallet));
    }
}
