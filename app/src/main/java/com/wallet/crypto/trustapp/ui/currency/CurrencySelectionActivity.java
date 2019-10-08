package com.wallet.crypto.trustapp.ui.currency;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class CurrencySelectionActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    /* renamed from: a */
    DispatchingAndroidInjector<Fragment> f23212a;

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_currency_selection);
        toolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurrencySelectionFragment()).commit();
    }

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.f23212a;
    }
}
