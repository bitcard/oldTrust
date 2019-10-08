package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;

import javax.inject.Inject;

import trust.blockchain.entity.Asset;

public class MainScreenRouter {
    @Inject
    public MainScreenRouter(){

    }

    public void open(Context context, boolean z) {
        Intent intent = new Intent(context, MainActivity.class);
        if (z) {
            intent.setFlags(268468224);
        }
        context.startActivity(intent);
    }

    public void open(Context context, boolean z, Asset asset) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("asset", asset);
        if (z) {
            intent.setFlags(268468224);
        }
        context.startActivity(intent);
    }
}
