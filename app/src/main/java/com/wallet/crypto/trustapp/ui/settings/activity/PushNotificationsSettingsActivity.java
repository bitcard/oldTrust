package com.wallet.crypto.trustapp.ui.settings.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.interact.RegisterPushNotificationsInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.settings.entity.NotificationState;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class PushNotificationsSettingsActivity extends BaseActivity {
    @Inject
    /* renamed from: a */
    PreferenceRepositoryType f23251a;
    @Inject
    /* renamed from: b */
    RegisterPushNotificationsInteract f23252b;
    /* renamed from: c */
    View f23253c;
    /* renamed from: d */
    SwitchCompat f23254d;

    /* renamed from: a */
    public static /* synthetic */ void m368a(PushNotificationsSettingsActivity pushNotificationsSettingsActivity, View view) {
        pushNotificationsSettingsActivity.toggleSwitch();
        pushNotificationsSettingsActivity.updateSwitch();
    }

    private void toggleSwitch() {
        NotificationState toggle = NotificationState.getState(this.f23251a.getEnableNotifications()).toggle();
        this.f23251a.setEnableNotifications(toggle.logicState());
        if (toggle == NotificationState.ENABLE) {
            this.f23252b.register().subscribe(() -> {}, throwable -> {});
            this.f23253c.setVisibility(View.VISIBLE);
            return;
        }
        this.f23252b.unregister().subscribe(() -> {}, throwable -> {});
        this.f23253c.setVisibility(View.GONE);
    }

    private void updateSwitch() {
        if (this.f23251a.getEnableNotifications()) {
            this.f23254d.setChecked(true);
            this.f23253c.setVisibility(View.VISIBLE);
            return;
        }
        this.f23254d.setChecked(false);
        this.f23253c.setVisibility(View.GONE);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AndroidInjection.inject(this);
        setContentView((int) R.layout.activity_push_notifications_settings);
        toolbar();
        this.f23254d = (SwitchCompat) findViewById(R.id.push_switch);
        View findViewById = findViewById(R.id.push_preference);
        this.f23253c = findViewById(R.id.send_receive_container);
        findViewById.setOnClickListener(new C1561k(this));
        this.f23254d.setOnClickListener(view -> toggleSwitch());
    }

    protected void onResume() {
        super.onResume();
        updateSwitch();
    }
}
