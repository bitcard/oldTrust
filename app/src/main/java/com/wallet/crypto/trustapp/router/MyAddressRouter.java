package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.receive.activity.ReceiveActivity;
import trust.blockchain.entity.Asset;

public class MyAddressRouter {
    public static Asset getAsset(Intent intent) {
        return (Asset) intent.getParcelableExtra("asset");
    }

    public void open(Context context, Asset asset) {
        Intent intent = new Intent(context, ReceiveActivity.class);
        intent.putExtra("asset", asset);
        context.startActivity(intent);
    }
}
