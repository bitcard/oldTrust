package com.wallet.crypto.trustapp.service;

import java.io.IOException;
import java.util.Set;
import trust.blockchain.CoinService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionSign;

/* compiled from: TransactionsService.kt */
public interface TransactionsService extends CoinService {

    /* compiled from: TransactionsService.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Transaction[] getTransactionList$default(TransactionsService transactionsService, Asset asset, Transaction[] transactionArr, int i, Object obj) throws IOException {
            if (obj == null) {
                if ((i & 2) != 0) {
                    transactionArr = new Transaction[0];
                }
                return transactionsService.getTransactionList(asset, transactionArr);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTransactionList");
        }
    }

    Transaction checkPending(Account account, Transaction transaction) throws IOException;

    Transaction getTransaction(Slip slip, Set<String> set, String str) throws IOException;

    Transaction[] getTransactionList(Asset asset, Transaction[] transactionArr) throws IOException;

    Transaction sendTransaction(TransactionSign transactionSign) throws IOException;
}
