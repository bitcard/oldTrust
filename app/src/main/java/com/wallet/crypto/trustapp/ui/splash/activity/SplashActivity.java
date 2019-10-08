package com.wallet.crypto.trustapp.ui.splash.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.router.AddWalletRouter;
import com.wallet.crypto.trustapp.router.MainScreenRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.splash.fabric.SplashViewModelFactory;
import com.wallet.crypto.trustapp.ui.splash.viewmodel.SplashViewModel;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Wallet;

public class SplashActivity extends BaseActivity {
    @Inject
    /* renamed from: a */
    SplashViewModelFactory f23255a;
    /* renamed from: b */
    SplashViewModel f23256b;

    private void onWallets(Wallet[] walletArr) {
        if (walletArr.length == 0) {
            new AddWalletRouter().open(this);
        } else {
            this.f23256b.checkWallets();
            if (this.f23256b.hasPasscode()) {
                new PasscodeRouter().openToLogin(this);
            } else {
                new MainScreenRouter().open(this, true);
            }
        }
        overridePendingTransition(0, 0);
        finish();
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        this.f23256b = ViewModelProviders.of(this, this.f23255a).get(SplashViewModel.class);
        this.f23256b.wallets().observe(this, wallets -> onWallets(wallets));
        ((App) getApplication()).setDeepLink(getIntent().getData());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    protected void onStart() {
        super.onStart();
        this.f23256b.init();
    }
}
