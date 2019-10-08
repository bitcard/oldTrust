package com.wallet.crypto.trustapp.ui;

import android.view.MenuItem;

public abstract class BaseActivity extends ToolbarActivity {
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
