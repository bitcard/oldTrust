package com.wallet.crypto.trustapp.ui.settings.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.util.SystemUtils;

public class AboutActivity extends BaseActivity {
    /* renamed from: b */
    public static /* synthetic */ void m362b(AboutActivity aboutActivity, String str, View view) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:support@trustwallet.com"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Android support question (Android: ");
        stringBuilder.append(str);
        stringBuilder.append(")");
        intent.putExtra("android.intent.extra.SUBJECT", stringBuilder.toString());
        intent.putExtra("android.intent.extra.TEXT", "Dear Trust support,");
        aboutActivity.startActivity(Intent.createChooser(intent, "Select email application."));
    }

    private void findViewByIdAndSetOnClickListener(int i, String str) {
        findViewById(i).setOnClickListener(new C1553b(this, str));
    }

    private void rateThisApp() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("market://details?id=");
        stringBuilder.append(getPackageName());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        intent.addFlags(1207959552);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("http://play.google.com/store/apps/details?id=");
            stringBuilder2.append(getPackageName());
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2.toString())));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_about);
        toolbar();
        String versionName = SystemUtils.getVersionName(this);
        findViewByIdAndSetOnClickListener(R.id.terms_framelayout, "https://trustwallet.com/terms-of-services");
        findViewById(R.id.email_us_framelayout).setOnClickListener(new C1554c(this, versionName));
        findViewById(R.id.rate_us_framelayout).setOnClickListener(view -> rateThisApp());
        ((TextView) findViewById(R.id.version_subtitle)).setText(versionName);
    }
}
