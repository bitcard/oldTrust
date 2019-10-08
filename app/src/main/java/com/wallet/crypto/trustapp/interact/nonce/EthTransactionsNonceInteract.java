package com.wallet.crypto.trustapp.interact.nonce;

import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;

import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Transaction;

public class EthTransactionsNonceInteract implements TransactionsNonceInteract {
    /* renamed from: a */
    private final SessionRepository f19165a;
    /* renamed from: b */
    private final TransactionsRepository f19166b;

    @Inject
    public EthTransactionsNonceInteract(SessionRepository sessionRepository, TransactionsRepository transactionsRepository) {
        this.f19165a = sessionRepository;
        this.f19166b = transactionsRepository;
    }

    public Single<Long> estimate(Account account) {
        return this.f19165a.getSessionOperator()
                .flatMap(session -> f19166b.fetch(session, account.coin.coinAsset(account, true), true).last(new Transaction[0]))
                .onErrorResumeNext(Single.just(new Transaction[0]))
                .flatMapObservable(transactions -> Observable.fromArray(transactions))
                .filter(transaction -> {
                    return transaction.owner.equals(account.address()) && transaction.from.equals(account.address()) && !"no_status".equalsIgnoreCase(transaction.error);
                })
                .toList()
                .map(transactions -> {
                    if (transactions.size() == 0) {
                        return Long.valueOf(0);
                    }
                    Collections.sort(transactions, C1444b.f16640a);
                    return Long.valueOf(((long) ((Transaction) transactions.get(0)).nonce) + 1);
                });
    }
}
