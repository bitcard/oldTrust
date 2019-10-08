package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import com.wallet.crypto.trustapp.ui.wallets.activity.WalletsActivity;

public class ManageWalletsRouter {
    @Inject
    public ManageWalletsRouter() {

    }

    public void open(Context context, boolean z) {
        Intent intent = new Intent(context, WalletsActivity.class);
        if (z) {
            intent.setFlags(268468224);
        }
        context.startActivity(intent);
    }
}
