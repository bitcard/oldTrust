package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.transfer.activity.ConfirmationActivity;
import javax.inject.Inject;

import trust.blockchain.entity.TransactionUnsigned;

public class ConfirmationRouter {

    @Inject
    public ConfirmationRouter() {

    }

    public void open(Context context, TransactionUnsigned transactionUnsigned) {
        Intent intent = new Intent(context, ConfirmationActivity.class);
        intent.putExtra("TRANSACTION_UNSIGNED", transactionUnsigned);
        context.startActivity(intent);
    }
}
