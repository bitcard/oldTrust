package com.wallet.crypto.trustapp.ui.dapp.controller;

import trust.blockchain.entity.TransactionUnsigned;

public interface OnSignTransactionListener {
    void onSignTransaction(TransactionUnsigned transactionUnsigned);
}
