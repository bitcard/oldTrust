package com.wallet.crypto.trustapp.repository.wallet;

import io.reactivex.Completable;
import io.reactivex.Single;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Wallet;
import trust.blockchain.entity.WalletDescriptor;

public interface WalletStore {
    Single<Wallet> add(int i, String str, byte[] bArr, Account... accountArr);

    Completable delete(Wallet wallet);

    Single<Wallet[]> fetch();

    Single<Wallet> findMainWallet();

    Single<Wallet> findWalletById(String str);

    Wallet[] getAllWallets();

    Wallet getWalletById(String str);

    Single<byte[]> getWalletData(Wallet wallet);

    Single<Boolean> isSkipBackup(Wallet wallet);

    Completable setIsSkipBackup(Wallet wallet, boolean z);

    Completable setName(Wallet wallet, String str);

    Single<Wallet> update(Wallet wallet, WalletDescriptor walletDescriptor);
}
