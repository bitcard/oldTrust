package com.wallet.crypto.trustapp.interact;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

public class HandleTransactionInteract {
    /* renamed from: a */
    private final BlockchainRepository f16622a;
    /* renamed from: b */
    private final WalletsRepository f16623b;
    /* renamed from: c */
    private final TransactionsRepository f16624c;

    @Inject
    HandleTransactionInteract(BlockchainRepository blockchainRepository, WalletsRepository walletsRepository, TransactionsRepository transactionsRepository) {
        this.f16622a = blockchainRepository;
        this.f16623b = walletsRepository;
        this.f16624c = transactionsRepository;
    }

    private Single<byte[]> createTransaction(Session session, TransactionUnsigned transactionUnsigned) {
        return getNonce(transactionUnsigned, transactionUnsigned.nonce())
                .flatMap(aLong -> signTransaction(session, transactionUnsigned.nonce(aLong.longValue())));
    }

    private Single<Long> getNonce(TransactionUnsigned transactionUnsigned, long j) {
        if (j >= 0) {
            return Single.just(Long.valueOf(j));
        }
        return this.f16622a.estimateNonce(transactionUnsigned.from());
    }

    private byte[] getSignPayload(TransactionSign transactionSign) {
        if (CoinConfig.f16616a.hasSimplePayloadType(transactionSign.getUnsignedTx().account().coin)) {
            return transactionSign.getSign();
        }
        return transactionSign.getEncodedRequest();
    }

    private Single<byte[]> signTransaction(Session session, TransactionUnsigned transactionUnsigned) {
        return this.f16622a.encodeTransaction(transactionUnsigned, null)
                .flatMap(bytes -> f16623b.signMessage(session.wallet, transactionUnsigned.from(), bytes, true))
                .flatMap(bytes -> f16622a.encodeTransaction(transactionUnsigned, bytes))
                .subscribeOn(Schedulers.io());
    }

    private String updateTransactionId(TransactionSign transactionSign, String str) {
        return (TextUtils.isEmpty(transactionSign.getTxId()) || !TextUtils.isEmpty(str)) ? str : transactionSign.getTxId();
    }

    public Single<TransactionSigned> approve(Session session, TransactionUnsigned transactionUnsigned) {
        return send(session, transactionUnsigned);
    }

    public Single<TransactionSigned> interact(Session session, TransactionUnsigned transactionUnsigned) {
        switch (transactionUnsigned.type()) {
            case 3:
                return approve(session, transactionUnsigned);
            case 4:
                return sign(session, transactionUnsigned);
            default:
                return send(session, transactionUnsigned);
        }
    }

    public Single<TransactionSigned> send(Session session, TransactionUnsigned transactionUnsigned) {
        if (CoinConfig.f16616a.hasCustomTransactionSigner(transactionUnsigned.account().coin)) {
            return this.f16623b.signTransaction(session.wallet, transactionUnsigned)
                    .flatMap(transactionSign -> f16622a.sendTransaction(transactionUnsigned.from(), getSignPayload(transactionSign))
                            .doOnSuccess(s -> f16624c.addPendingTransaction(session, updateTransactionId(transactionSign, s), transactionUnsigned))
                            .map(s -> new TransactionSigned(transactionUnsigned, transactionSign.getSign(), s)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return createTransaction(session, transactionUnsigned)
                .flatMap(bytes -> f16622a.sendTransaction(transactionUnsigned.from(), bytes)
                        .doOnSuccess(o -> f16624c.addPendingTransaction(session, f16622a.transactionId(transactionUnsigned, bytes).blockingGet(), transactionUnsigned))
                        .map(new C2222n(transactionUnsigned, bytes)));
    }

    public Single<TransactionSigned> sign(Session session, TransactionUnsigned transactionUnsigned) {
        return createTransaction(session, transactionUnsigned).map(new C2208k(transactionUnsigned));
    }
}
