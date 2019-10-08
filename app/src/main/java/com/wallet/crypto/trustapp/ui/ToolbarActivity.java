package com.wallet.crypto.trustapp.ui;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.wallet.crypto.trustapp.R;

public class ToolbarActivity extends LockedActivity {
    private Toolbar toolbar;

    private void disableAutoFill() {
        getWindow().getDecorView().setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
    }

    public void disableDisplayHomeAsUp() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDisplayShowHomeEnabled(false);
        }
    }

    public void enableDisplayHomeAsUp() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void hideToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 26) {
            disableAutoFill();
        }
    }

    public void setSubtitle(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setSubtitle(str);
        }
    }

    public void setTitle(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(str);
        }
    }

    public void showToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.show();
        }
    }

    public Toolbar toolbar() {
        if (this.toolbar == null) {
            this.toolbar = (Toolbar) findViewById(R.id.toolbar);
            Toolbar toolbar = this.toolbar;
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                this.toolbar.setTitle(getTitle());
                enableDisplayHomeAsUp();
            }
        }
        return this.toolbar;
    }
}
