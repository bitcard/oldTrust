package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.wallet.crypto.trustapp.ui.assets.activity.AddAssetActivity;
import javax.inject.Inject;

import trust.blockchain.Slip;

public class AddAssetRouter {
    @Inject
    public AddAssetRouter() {

    }

    public void open(Activity activity) {
        activity.startActivityForResult(new Intent(activity, AddAssetActivity.class), 5004);
    }

    public void open(Activity activity, String str, Slip slip) {
        Intent intent = new Intent(activity, AddAssetActivity.class);
        intent.putExtra("contract_address", str);
        intent.putExtra("coin_type", slip.coinType().value());
        activity.startActivityForResult(intent, 5004);
    }
}
