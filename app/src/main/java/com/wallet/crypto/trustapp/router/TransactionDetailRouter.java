package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.transfer.activity.TransactionDetailActivity;
import trust.blockchain.entity.Asset;

public class TransactionDetailRouter {
    public void open(Context context, Asset asset, String str) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra("transaction_hash", str);
        intent.putExtra("asset", asset);
        context.startActivity(intent);
    }
}
