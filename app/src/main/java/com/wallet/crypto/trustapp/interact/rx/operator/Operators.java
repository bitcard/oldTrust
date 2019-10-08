package com.wallet.crypto.trustapp.interact.rx.operator;

import android.util.Pair;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.service.ServiceException;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import trust.blockchain.Slip;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.SwapPayload;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;

public class Operators {
    public static Map<String, List<Transaction>> fetchPendingTransactions(Session session, TransactionsRepository transactionsRepository) {
        return indexingTransactions(transactionsRepository.fetchPendingTransactions(session));
    }

    private static Map<String, List<Transaction>> indexingTransactions(Transaction[] transactionArr) {
        HashMap hashMap = new HashMap();
        for (Transaction transaction : transactionArr) {
            Object obj;
            if (transaction.type == Type.TOKEN_TRANSFER) {
                obj = transaction.tokenId;
            } else {
                obj = String.valueOf(transaction.coin.coinType().value());
            }
            List list = (List) hashMap.get(obj);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(obj, list);
            }
            list.add(transaction);
        }
        return hashMap;
    }

    public static Single<Pair<String, Transaction>> updatePending(Single<Transaction> single, Transaction transaction) {
        return single.map(transaction2 -> {
                    return new Pair<>(transaction.id,
                                        new Transaction(
                                                transaction.id,
                                                transaction2.error != null ? transaction2.error : transaction.error,
                                                transaction2.blockNumber,
                                                transaction.timeStamp,
                                                transaction.nonce,
                                                transaction2.from != null ? transaction2.from : transaction.from ,
                                                transaction2.from != null ? transaction2.from : transaction.from,
                                                transaction2.to != null ? transaction2.to : transaction.to,
                                                transaction2.value != null ? transaction2.value : transaction.value,
                                                transaction2.fee != null ? transaction2.fee : transaction.fee,
                                                transaction2.input,
                                                transaction.tokenName,
                                                transaction.tokenId,
                                                transaction.memo,
                                                transaction.coin,
                                                transaction.type,
                                                transaction2.status,
                                                transaction2.direction,
                                                transaction.title,
                                                transaction.swapPayload));
                })
                .onErrorResumeNext(throwable -> Single.fromCallable(() -> {
                    if ((System.currentTimeMillis() / 1000) - transaction.timeStamp > 150 && (throwable instanceof ServiceException) && ((ServiceException) throwable).getHttpCode() == 13) {
                        return new Pair<>(transaction.id, null);
                    }
                    return new Pair<>(transaction.id, transaction);
                }));
    }
}
