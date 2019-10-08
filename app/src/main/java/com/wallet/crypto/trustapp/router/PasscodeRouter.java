package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.passcode.activity.PasscodeActivity;

import javax.inject.Inject;

public class PasscodeRouter {
    @Inject
    public PasscodeRouter(){

    }

    public int action(Intent intent) {
        return intent.getIntExtra("action", 3);
    }

    public Intent deleteIntent(Context context) {
        Intent intent = new Intent(context, PasscodeActivity.class);
        intent.putExtra("action", 2);
        return intent;
    }

    public String getPinCode(Intent intent) {
        return intent.getStringExtra("pin_code");
    }

    public void openToCreate(Context context) {
        Intent intent = new Intent(context, PasscodeActivity.class);
        intent.putExtra("action", 1);
        context.startActivity(intent);
    }

    public void openToDelete(Context context) {
        context.startActivity(deleteIntent(context));
    }

    public void openToLogin(Context context) {
        Intent intent = new Intent(context, PasscodeActivity.class);
        intent.putExtra("action", 3);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }

    public void openToRepeat(Context context, String str) {
        Intent intent = new Intent(context, PasscodeActivity.class);
        intent.putExtra("action", 4);
        intent.putExtra("pin_code", str);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    public void openToSendConfirm(Activity activity, String str) {
        Intent intent = new Intent(activity, PasscodeActivity.class);
        intent.putExtra("action", 5);
        intent.putExtra("tag", str);
        activity.startActivityForResult(intent, 1023);
    }

    public String tag(Intent intent) {
        return intent.getStringExtra("tag");
    }
}
