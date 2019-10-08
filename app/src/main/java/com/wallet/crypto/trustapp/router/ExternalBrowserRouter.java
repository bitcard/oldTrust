package com.wallet.crypto.trustapp.router;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class ExternalBrowserRouter {

    @javax.inject.Inject
    public ExternalBrowserRouter(){

    }

    public void open(Context context, Uri uri) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (ActivityNotFoundException e) {
            Log.d(ExternalBrowserRouter.class.getSimpleName(), "Open activity err", e);
        }
    }
}
