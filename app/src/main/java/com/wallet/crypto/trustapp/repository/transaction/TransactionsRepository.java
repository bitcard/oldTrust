package com.wallet.crypto.trustapp.repository.transaction;

import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public interface TransactionsRepository {
    void addPendingTransaction(Session session, String str, TransactionUnsigned transactionUnsigned);

    Observable<Transaction[]> fetch(Session session, Asset asset, boolean z);

    Transaction[] fetchPendingTransactions(Session session);

    Single<Transaction> findTransaction(Session session, String str);

    Completable put(Session session, Transaction transaction);

    Completable removeByHash(Session session, String str);
}
