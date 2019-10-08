package com.wallet.crypto.trustapp.repository.wallet;

import io.reactivex.Completable;
import io.reactivex.Single;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Wallet;

public interface WalletsRepository {
    Single<Wallet> addWallet(int i, String str, String str2, String str3, Slip[] slipArr);

    Completable deleteWallet(Wallet wallet);

    Single<String> exportKeyStore(Wallet wallet, String str, Account account);

    Single<String> exportPhrase(Wallet wallet);

    Single<String> exportPrivateKey(Wallet wallet, Account account);

    Single<Wallet[]> fetch();

    Single<Wallet> findMainWallet();

    Single<Wallet> findWalletById(String str);

    Wallet[] getAllWallet();

    int getDefaultType();

    Single<Integer> getNextWalletNumber(int i);

    Wallet getWalletById(String str);

    Single<Boolean> isSkipBackup(Wallet wallet);

    Single<Wallet> newWallet(String str);

    Single<Wallet> reimportWallet(Wallet wallet);

    Completable setIsSkipBackup(Wallet wallet, boolean z);

    Completable setName(Wallet wallet, String str);

    Single<byte[]> signMessage(Wallet wallet, Account account, byte[] bArr, boolean z);

    Single<TransactionSign> signTransaction(Wallet wallet, TransactionUnsigned transactionUnsigned);
}
