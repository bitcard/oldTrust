package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.QRScannerActivity;
import com.wallet.crypto.trustapp.util.QRUri;

import javax.inject.Inject;

import trust.blockchain.Slip;

public class QRScannerRouter {
    @Inject
    public QRScannerRouter() {

    }

    public QRUri onActivityResult(int i, int i2, Intent intent) {
        return (i == 4112 && i2 == -1 && intent != null) ? (QRUri) intent.getParcelableExtra("qr_uri") : null;
    }

    public void openForResult(Activity activity, Slip slip) {
        String str;
        Intent intent = new Intent(activity, QRScannerActivity.class);
        String str2 = "coin";
        if (slip == null) {
            str = null;
        } else {
            str = slip.name();
        }
        intent.putExtra(str2, str);
        activity.startActivityForResult(intent, 4112);
    }
}
