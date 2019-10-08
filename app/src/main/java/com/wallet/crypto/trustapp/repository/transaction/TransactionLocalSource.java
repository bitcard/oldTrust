package com.wallet.crypto.trustapp.repository.transaction;

import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.Completable;
import io.reactivex.Single;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Transaction;

public interface TransactionLocalSource {
    Single<Transaction[]> fetchByAsset(Session session, Asset asset);

    Transaction[] fetchPendingTransactions(Session session);

    Single<Transaction> findByHash(Session session, String str);

    Completable put(Session session, Transaction... transactionArr);

    Completable removeByHash(Session session, String str);
}
