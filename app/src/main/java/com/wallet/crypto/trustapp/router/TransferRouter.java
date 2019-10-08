package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.ui.transfer.activity.SendActivity;
import javax.inject.Inject;

import trust.blockchain.entity.Asset;
import trust.blockchain.entity.TransactionUnsigned;

public class TransferRouter {
    @Inject
    TransferRouter(){

    }

    public void open(Context context, Session session, Asset asset) {
        open(context, new TransactionUnsigned(new Asset(asset.type, asset.contract, session.wallet.account(asset.coin()), asset.balance, asset.ticker, asset.isEnabled, asset.isAddedManually, asset.updateBalanceTime)));
    }

    public void open(Context context, TransactionUnsigned transactionUnsigned) {
        Intent intent = new Intent(context, SendActivity.class);
        intent.putExtra("TRANSACTION_UNSIGNED", transactionUnsigned);
        context.startActivity(intent);
    }
}
