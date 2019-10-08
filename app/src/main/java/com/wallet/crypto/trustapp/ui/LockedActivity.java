package com.wallet.crypto.trustapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.ui.passcode.fragment.PasscodeFragment;

public abstract class LockedActivity extends AppCompatActivity {
    public static final String DIALOG_LOCK_TAG = "LockDialog";

    protected void onResume() {
        super.onResume();
        if (((App) getApplication()).isScreenLocked()) {
            new PasscodeFragment().show(getSupportFragmentManager().beginTransaction().setCustomAnimations(0, 0), DIALOG_LOCK_TAG);
        }
    }
}
