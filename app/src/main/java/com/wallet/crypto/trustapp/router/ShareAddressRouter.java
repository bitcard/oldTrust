package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.wallet.crypto.trustapp.R;

import javax.inject.Inject;

public class ShareAddressRouter {

    @Inject
    public ShareAddressRouter() {

    }

    public void open(Context context, Uri uri, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.ShareVia)));
    }
}
