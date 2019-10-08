package com.wallet.crypto.trustapp.repository.network;

import android.os.Handler;
import android.util.Pair;

import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.interact.rx.operator.Operators;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.service.TransactionsService;
import com.wallet.crypto.trustapp.service.TransactionsServiceAdapter;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import trust.blockchain.CoinService;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Bip32Path;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public abstract class NodeRepositoryType implements BlockchainRepository {
    /* renamed from: a */
    private final Handler f19233a;
    /* renamed from: b */
    private final SessionRepository f19234b;
    /* renamed from: c */
    private final TransactionsRepository f19235c;
    /* renamed from: d */
    private final TransactionsServiceAdapter f19236d;
    /* renamed from: e */
    private final RpcService[] f19237e;
    /* renamed from: f */
    private Runnable f19238f = new C14471();

    /* renamed from: com.wallet.crypto.trustapp.repository.network.NodeRepositoryType$1 */
    class C14471 implements Runnable {

        /* renamed from: com.wallet.crypto.trustapp.repository.network.NodeRepositoryType$1$1 */
        class C14481 implements CompletableObserver {
            C14481() {
            }

            public void onComplete() {
                NodeRepositoryType.this.start();
            }

            public void onError(Throwable th) {
                NodeRepositoryType.this.start();
            }

            public void onSubscribe(Disposable disposable) {
            }
        }

        C14471() {
        }

        public void run() {
            NodeRepositoryType.this.f19234b.getSessionOperator()
                    .flatMapCompletable(session -> NodeRepositoryType.this.updatePending(session))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new C14481());
        }
    }

    public NodeRepositoryType(Handler handler, SessionRepository sessionRepository, TransactionsRepository transactionsRepository, TransactionsServiceAdapter transactionsServiceAdapter, RpcService... rpcServiceArr) {
        this.f19233a = handler;
        this.f19234b = sessionRepository;
        this.f19235c = transactionsRepository;
        this.f19236d = transactionsServiceAdapter;
        this.f19237e = rpcServiceArr;
        start();
    }

    private String getTransactionHash(Transaction transaction) {
        String str = transaction.id;
        if (!CoinConfig.f16616a.needToPassBlockLevel(transaction.coin)) {
            return str;
        }
        return String.format("%s-%s", new Object[]{str, transaction.blockNumber});
    }

    private void start() {
        Handler handler = this.f19233a;
        if (handler != null) {
            handler.removeCallbacks(this.f19238f);
            this.f19233a.postDelayed(this.f19238f, 5000);
        }
    }

    private Completable updatePending(Session session) {
        return Single.fromCallable(()->f19235c.fetchPendingTransactions(session))
                .flatMap(transactions -> Observable.fromArray(transactions).flatMapSingle(transaction -> NodeRepositoryType.m65a(this, session, transaction)).toList())
                .flatMapCompletable(o -> Observable.fromIterable((List) o).flatMapCompletable(o1 -> NodeRepositoryType.m63a(this, session, (Pair) o1)));
    }

    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return getService(transactionUnsigned.from().coin).encodeTransaction(transactionUnsigned, bArr);
    }

    public Single<Fee> estimateFee(TransactionUnsigned transactionUnsigned) {
        return getService(transactionUnsigned.from().coin).estimateFee(transactionUnsigned);
    }

    public Single<Long> estimateNonce(Account account) {
        return getService(account.coin).estimateNonce(account);
    }

    public Single<Transaction> findTransaction(Account account, String str) {
        return getService(account.coin).findTransaction(account, str);
    }

    public Single<BlockInfo> getBlockNumber(Slip slip) throws IOException {
        return getService(slip).getBlockNumber(slip);
    }

    protected RpcService getService(Slip slip) {
        for (RpcService coinService : this.f19237e) {
            if (slip.available(coinService.getMaintainedCoins())) {
                return coinService;
            }
        }
        throw new IllegalArgumentException("Doesn't supported coin");
    }

    public Single<String> sendTransaction(Account account, byte[] bArr) {
        return getService(account.coin).sendTransaction(account, bArr);
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return getService(transactionUnsigned.from().coin).transactionId(transactionUnsigned, bArr);
    }

    /* renamed from: a */
    public static /* synthetic */ Transaction m67a(NodeRepositoryType nodeRepositoryType, Account account, Transaction transaction, String str) throws Exception {
        HashSet hashSet = new HashSet();
        for (Bip32Path address : account.getAllIndices()) {
            hashSet.add(address.getAddress());
        }
        return ((TransactionsService) nodeRepositoryType.f19236d.getService(transaction.coin)).getTransaction(account.coin, hashSet, str);
    }

    public static SingleSource m65a(NodeRepositoryType r3, Session r4, Transaction r5) throws java.lang.Exception {
        Account account = r4.wallet.account(r5.coin);
        String r0 = r3.getTransactionHash(r5);
        return Operators.updatePending(
                CoinConfig.f16616a.hasCustomTransactionFinder(r5.coin) ?
                        io.reactivex.Single.fromCallable(()->NodeRepositoryType.m67a(r3, account, r5, r0)) :
                        r3.findTransaction(account, r0), r5);
    }

    public static CompletableSource m63a(NodeRepositoryType r1, Session r2, Pair r3) throws Exception {
        if (r3.second == null) {
            return r1.f19235c.removeByHash(r2, (String)r3.first);
        } else {
            return r1.f19235c.put(r2, (trust.blockchain.entity.Transaction)r3.second);
        }
    }

}
