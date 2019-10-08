package com.wallet.crypto.trustapp.ui.transfer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.router.MainScreenRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment;
import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment.Listener;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

public class ConfirmationActivity extends BaseActivity implements Listener, HasSupportFragmentInjector {
    @Inject
    /* renamed from: a */
    DispatchingAndroidInjector<Fragment> f23257a;

    private ConfirmTransactionFragment getConfirmationFragment() {
        return (ConfirmTransactionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    private void showConfirmFragment(TransactionUnsigned transactionUnsigned) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ConfirmTransactionFragment.create(transactionUnsigned, this)).commit();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        getConfirmationFragment().onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById == null || !findFragmentById.isVisible()) {
            super.onBackPressed();
        } else if (findFragmentById instanceof ConfirmTransactionFragment) {
            ConfirmTransactionFragment confirmTransactionFragment = (ConfirmTransactionFragment) findFragmentById;
            if (confirmTransactionFragment.onBackPress()) {
                onCancel(confirmTransactionFragment.getTransactionUnsigned());
            }
        }
    }

    public void onCancel(TransactionUnsigned transactionUnsigned) {
        finish();
    }

    public void onComplete(TransactionSigned transactionSigned) {
        new MainScreenRouter().open(this, true, transactionSigned.asset);
        finish();
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_confirm);
        toolbar();
        if (getIntent().hasExtra("TRANSACTION_UNSIGNED")) {
            showConfirmFragment((TransactionUnsigned) getIntent().getParcelableExtra("TRANSACTION_UNSIGNED"));
        } else {
            setTitle(R.string.Sign);
        }
    }

    public void onError(TransactionUnsigned transactionUnsigned, String str) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.f23257a;
    }
}
