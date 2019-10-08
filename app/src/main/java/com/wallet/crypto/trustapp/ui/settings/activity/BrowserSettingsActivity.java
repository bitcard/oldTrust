package com.wallet.crypto.trustapp.ui.settings.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.SwitchCompat;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class BrowserSettingsActivity extends BaseActivity implements OnClickListener {
    @Inject
    /* renamed from: a */
    PreferenceRepositoryType f23248a;
    @Inject
    /* renamed from: b */
    SessionRepository f23249b;

    /* renamed from: a */
    public static /* synthetic */ void m363a(BrowserSettingsActivity browserSettingsActivity, DialogInterface dialogInterface, int i) {
        browserSettingsActivity.setResult(-1);
        browserSettingsActivity.finish();
    }

    static /* synthetic */ void lambda$onClick$1(DialogInterface dialogInterface, int i) {
    }

    private void setEnableDappPreferencesData() {
        boolean enableDapp = this.f23248a.getEnableDapp();
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.enable_dapp_switch);
        switchCompat.setOnClickListener(this);
        switchCompat.setChecked(enableDapp);
    }

    private void setSearchEnginePreferenceData() {
        ((TextView) findViewById(R.id.search_engine_subtitle)).setText(this.f23248a.getSearchEngine());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_browser_cache_preference /*2131361969*/:
                new Builder(this).setTitle((int) R.string.settings_browser_clearCache_alert_title)
                        .setMessage((int) R.string.settings_browser_clearCache_alert_message)
                        .setPositiveButton((int) R.string.OK, new C1555d(this))
                        .setNegativeButton((int) R.string.Cancel, C1556e.f16946a)
                        .create()
                        .show();
                return;
            case R.id.enable_dapp_preference /*2131362040*/:
            case R.id.enable_dapp_switch /*2131362041*/:
                SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.enable_dapp_switch);
                PreferenceRepositoryType preferenceRepositoryType = this.f23248a;
                preferenceRepositoryType.setEnableDapp(!preferenceRepositoryType.getEnableDapp());
                switchCompat.setChecked(this.f23248a.getEnableDapp());
                SessionRepository sessionRepository = this.f23249b;
                sessionRepository.notifySessionChanged((Session) sessionRepository.getSessionOperator().blockingGet()).blockingAwait();
                return;
            case R.id.search_engine_preference /*2131362281*/:
                startActivity(new Intent(this, ChooseSearchEngineActivity.class));
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AndroidInjection.inject(this);
        setContentView((int) R.layout.activity_browser_settings);
        toolbar();
        findViewById(R.id.enable_dapp_preference).setOnClickListener(this);
        setEnableDappPreferencesData();
        findViewById(R.id.search_engine_preference).setOnClickListener(this);
        setSearchEnginePreferenceData();
        findViewById(R.id.clear_browser_cache_preference).setOnClickListener(this);
    }

    public void onResume() {
        super.onResume();
        setSearchEnginePreferenceData();
    }
}
