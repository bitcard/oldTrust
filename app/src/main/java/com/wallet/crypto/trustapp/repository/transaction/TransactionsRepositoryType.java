package com.wallet.crypto.trustapp.repository.transaction;

import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.service.TransactionsService;
import com.wallet.crypto.trustapp.service.TransactionsServiceAdapter;

import org.web3j.utils.Numeric;

import java.math.BigInteger;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Transaction;

public class TransactionsRepositoryType implements TransactionsRepository {
    /* renamed from: a */
    private final SessionRepository f19264a;
    /* renamed from: b */
    private final TransactionLocalSource f19265b;
    /* renamed from: c */
    private final TransactionsServiceAdapter f19266c;
    /* renamed from: d */
    private final ApiService f19267d;

    public TransactionsRepositoryType(SessionRepository sessionRepository, TransactionLocalSource transactionLocalSource, TransactionsServiceAdapter transactionsServiceAdapter, ApiService apiService) {
        this.f19264a = sessionRepository;
        this.f19267d = apiService;
        this.f19266c = transactionsServiceAdapter;
        this.f19265b = transactionLocalSource;
    }

    private Single<Transaction[]> fetchFromCache(Session session, Asset asset) {
        return this.f19265b.fetchByAsset(session, asset)
                .flatMapObservable(C2255a.f19268a)
                .toList()
                .map(C2262l.f19280a);
    }

    private Completable updateFromNetwork(Session session, Asset asset) {
        Single fromCallable;
        if (CoinConfig.isUTXOBased(asset.coin())) {
            fromCallable = Single.fromCallable(()->this.f19266c.getService(asset.coin()).getTransactionList(asset, this.f19265b.fetchByAsset(session, asset).blockingGet()));
        } else {
            fromCallable = Single.fromCallable(()->this.f19267d.fetchLastTransactions(asset));
        }
        return fromCallable.onErrorResumeNext(C2260j.f19277a)
                .flatMapCompletable(o -> f19265b.put(session, (Transaction[]) o));
    }

    public void addPendingTransaction(Session r24, String r25, trust.blockchain.entity.TransactionUnsigned r26) {
        SubunitValue r10 = new SubunitValue(r26.weiAmount(), r26.unit());
        String r15 = r26.getMemoOrTag();
        Transaction.Type r17;
        String r14;
        String r13;
        if (r26.type() == 2) {
            r17 = Transaction.Type.TOKEN_TRANSFER;
            r14 = r26.asset().contract.tokenId;
            r13 = r26.asset().contract.name;
        } else if (r26.type() == 5) {
            r17 = Transaction.Type.SWAP;
            r14 = r26.asset().contract.tokenId;
            r13 = r26.asset().contract.name;
        } else {
            r17 = Transaction.Type.TRANSFER;
            r14 = "";
            r13 = null;
        }

        String r11 = "0";
        try {
            r11 = r26.fee().calculateNetworkFee().toString();
        } catch (Exception e){
        }
        this.f19265b.put(r24, new Transaction(r25,
                null,
                String.valueOf(r26.blockInfo() != null && r26.blockInfo().number != null ? r26.blockInfo().number: BigInteger.ZERO),
                System.currentTimeMillis() / 1000,
                (int) r26.nonce(),
                r26.from().address(),
                r26.from().address(),
                r26.recipientAddress(),
                r10,
                r11,
                Numeric.toHexString(r26.data()),
                r13,
                r14,
                r15,
                r26.asset().contract.coin,
                r17,
                Transaction.Status.hasPending(r26) ? Transaction.Status.PENDING : Transaction.Status.COMPLETED,
                Transaction.Direction.OUT,
                null,
                r26.swapPayload()
                ))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Observable<Transaction[]> fetch(Session session, Asset asset, boolean z) {
        if (z) {
            return Single.merge(fetchFromCache(session, asset), updateFromNetwork(session, asset).andThen(fetchFromCache(session, asset)))
                    .toObservable();
        }
        return fetchFromCache(session, asset).toObservable();
    }

    public Transaction[] fetchPendingTransactions(Session session) {
        return this.f19265b.fetchPendingTransactions(session);
    }

    public Single<Transaction> findTransaction(Session session, String str) {
        return this.f19265b.findByHash(session, str);
    }

    public Completable put(Session session, Transaction transaction) {
        return this.f19265b.put(session, transaction);
    }

    public Completable removeByHash(Session session, String str) {
        return this.f19265b.removeByHash(session, str);
    }
}
