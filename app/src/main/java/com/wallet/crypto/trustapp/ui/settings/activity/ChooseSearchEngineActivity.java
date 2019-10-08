package com.wallet.crypto.trustapp.ui.settings.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class ChooseSearchEngineActivity extends BaseActivity {
    @Inject
    /* renamed from: a */
    PreferenceRepositoryType f23250a;

    private void select(String str) {
        this.f23250a.setSearchEngine(str);
        setResult(-1);
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AndroidInjection.inject(this);
        setContentView((int) R.layout.activity_choose_search_engine);
        toolbar();
        RadioButton radioButton = (RadioButton) findViewById(R.id.google_radiobutton);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.duckduckgo_radiobutton);
        String searchEngine = this.f23250a.getSearchEngine();
        if (searchEngine.equals("Google")) {
            radioButton.setChecked(true);
        } else if (searchEngine.equals("DuckDuckGo")) {
            radioButton2.setChecked(true);
        }
        radioButton2.setOnCheckedChangeListener(new C1557f(this));
        radioButton.setOnCheckedChangeListener(new C1560i(this));
        findViewById(R.id.google_framelayout).setOnClickListener(new C1559h(this, radioButton));
        findViewById(R.id.duckduckgo_framelayout).setOnClickListener(new C1558g(this, radioButton2));
    }

    public static void m366b(ChooseSearchEngineActivity r0, CompoundButton r1, boolean r2) {
        if (r2) {
            r0.select("Google");
        }
    }

    public static void m365a(ChooseSearchEngineActivity r0, RadioButton r1, View r2) {
        r1.setChecked(true);
        r0.select("Google");
    }

    public static void m364a(ChooseSearchEngineActivity r0, CompoundButton r1, boolean r2) {
        if (r2 ) {
            r0.select("DuckDuckGo");
        }
    }

    public static void m367b(ChooseSearchEngineActivity r0, RadioButton r1, View r2) {
        r1.setChecked(true);
        r0.select("DuckDuckGo");
    }
}
