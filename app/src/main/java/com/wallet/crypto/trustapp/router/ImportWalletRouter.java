package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.importwallet.activity.ImportWalletActivity;
import javax.inject.Inject;

import trust.blockchain.Slip;
import trust.blockchain.entity.ServiceErrorException;

public class ImportWalletRouter {
    @Inject
    public ImportWalletRouter() {

    }

    public void openForResult(Activity activity, Slip slip) {
        Intent intent = new Intent(activity, ImportWalletActivity.class);
        if (slip != null) {
            intent.putExtra("coin", slip.name());
        }
        activity.startActivityForResult(intent, ServiceErrorException.KEY_STORE_ERROR);
    }
}
