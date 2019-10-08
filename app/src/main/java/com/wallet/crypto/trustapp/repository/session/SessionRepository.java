package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.Completable;
import io.reactivex.Single;
import trust.blockchain.entity.Wallet;

public interface SessionRepository {
    void addOnSessionChangeListener(OnSessionChangeListener onSessionChangeListener);

    Session getSession();

    Single<Session> getSessionOperator();

    Completable notifySessionChanged(Session session);

    Completable setCurrencyCode(String str);

    Completable setWallet(Wallet wallet);
}
