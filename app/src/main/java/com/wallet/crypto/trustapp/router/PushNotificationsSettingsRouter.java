package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import com.wallet.crypto.trustapp.ui.settings.activity.PushNotificationsSettingsActivity;

public class PushNotificationsSettingsRouter {
    @Inject
    public PushNotificationsSettingsRouter() {

    }

    public void open(Context context) {
        context.startActivity(new Intent(context, PushNotificationsSettingsActivity.class));
    }
}
