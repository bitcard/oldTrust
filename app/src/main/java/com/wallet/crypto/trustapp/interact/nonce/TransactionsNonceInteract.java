package com.wallet.crypto.trustapp.interact.nonce;

import io.reactivex.Single;
import trust.blockchain.entity.Account;

public interface TransactionsNonceInteract {
    Single<Long> estimate(Account account);
}
