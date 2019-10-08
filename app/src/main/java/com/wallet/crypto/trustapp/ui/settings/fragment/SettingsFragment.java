package com.wallet.crypto.trustapp.ui.settings.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType.LockAfterVariants;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.settings.factory.SettingsViewModelFactory;
import com.wallet.crypto.trustapp.ui.settings.viewmodel.SettingsViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.Objects;
import javax.inject.Inject;
import trust.blockchain.entity.ServiceErrorException;

public class SettingsFragment extends MenuFragment {
    @Inject
    /* renamed from: b */
    SettingsViewModelFactory f22216b;
    /* renamed from: c */
    SettingsViewModel f22217c;
    /* renamed from: d */
    private View f22218d;
    /* renamed from: e */
    private TextView f22219e;
    /* renamed from: f */
    private View f22220f;
    /* renamed from: g */
    private TextView f22221g;
    /* renamed from: h */
    private View f22222h;
    /* renamed from: i */
    private SwitchCompat f22223i;
    /* renamed from: j */
    private View f22224j;
    /* renamed from: k */
    private View f22225k;
    /* renamed from: l */
    private View f22226l;
    /* renamed from: m */
    private View f22227m;
    /* renamed from: n */
    private View f22228n;
    /* renamed from: o */
    private View f22229o;
    /* renamed from: p */
    private View f22230p;
    /* renamed from: q */
    private View f22231q;
    /* renamed from: r */
    private View f22232r;
    /* renamed from: s */
    private View f22233s;
    /* renamed from: t */
    private View f22234t;
    /* renamed from: u */
    private SwitchCompat f22235u;
    /* renamed from: v */
    private View f22236v;
    /* renamed from: w */
    private TextView f22237w;

    /* renamed from: com.wallet.crypto.trustapp.ui.settings.fragment.SettingsFragment$1 */
    class C15631 implements OnClickListener {
        C15631() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            LockAfterVariants lockAfterVariants;
            switch (i) {
                case 1:
                    lockAfterVariants = LockAfterVariants.ONE_MINUTE;
                    break;
                case 2:
                    lockAfterVariants = LockAfterVariants.FIVE_MINUTES;
                    break;
                case 3:
                    lockAfterVariants = LockAfterVariants.ONE_HOUR;
                    break;
                case 4:
                    lockAfterVariants = LockAfterVariants.FIVE_HOUR;
                    break;
                default:
                    lockAfterVariants = LockAfterVariants.IMMEDIATE;
                    break;
            }
            SettingsFragment.this.f22217c.setLockAfter(lockAfterVariants);
        }
    }

    /* renamed from: h */
    public static /* synthetic */ void m298h(SettingsFragment settingsFragment, View view) {
        SwitchCompat switchCompat = settingsFragment.f22235u;
        switchCompat.setChecked(!switchCompat.isChecked());
        settingsFragment.f22217c.toggleRequestPinOnSending();
    }

    private void hidePushNotifications() {
        if (!"google-play".equalsIgnoreCase("s3")) {
            getView().findViewById(R.id.push_preference).setVisibility(View.GONE);
        }
    }

    private void onCurrency(String str) {
        this.f22221g.setText(str);
    }

    private void onHasPasscode(boolean z) {
        this.f22223i.setChecked(z);
        this.f22236v.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    private void onLockVariant(LockAfterVariants lockAfterVariants) {
        int i;
        switch (lockAfterVariants) {
            case IMMEDIATE:
                i = R.string.wallets_navigation_title_autolock_immediate;
                break;
            case ONE_MINUTE:
                i = R.string.wallets_navigation_title_autolock_one_minute;
                break;
            case FIVE_MINUTES:
                i = R.string.wallets_navigation_title_autolock_five_minutes;
                break;
            case ONE_HOUR:
                i = R.string.wallets_navigation_title_autolock_one_hour;
                break;
            case FIVE_HOUR:
                i = R.string.wallets_navigation_title_autolock_five_hour;
                break;
            default:
                i = 0;
                break;
        }
        this.f22237w.setText(i);
    }

    private void onShouldRequestPinSending(Boolean bool) {
        this.f22235u.setChecked(bool.booleanValue());
    }

    private void onWalletName(String str) {
        this.f22219e.setText(str);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f22217c.walletName().observe(this, s -> onWalletName(s));
        this.f22217c.hasPasscode().observe(this, aBoolean -> onHasPasscode(aBoolean.booleanValue()));
        this.f22217c.shouldRequestPinSending().observe(this, aBoolean -> onShouldRequestPinSending(aBoolean));
        this.f22217c.currency().observe(this, s -> onCurrency(s));
        this.f22217c.f21438i.observe(this, lockAfterVariants -> onLockVariant(lockAfterVariants));
        this.f22218d.setOnClickListener(new C1580u(this));
        this.f22223i.setOnClickListener(new C1570i(this));
        this.f22235u.setOnClickListener(new C1574m(this));
        this.f22224j.setOnClickListener(new C1578s(this));
        this.f22220f.setOnClickListener(new C1567f(this));
        this.f22222h.setOnClickListener(new C1581v(this));
        this.f22225k.setOnClickListener(new C1571j(this));
        this.f22226l.setOnClickListener(new C1566c(this));
        this.f22227m.setOnClickListener(new C1579t(this));
        this.f22228n.setOnClickListener(new C1568g(this));
        this.f22229o.setOnClickListener(new C1569h(this));
        this.f22230p.setOnClickListener(new C1575n(this));
        this.f22231q.setOnClickListener(new C1573l(this));
        this.f22232r.setOnClickListener(new C1576o(this));
        this.f22233s.setOnClickListener(new C1565a(this));
        this.f22234t.setVisibility(View.GONE);
        this.f22234t.setOnClickListener(new C1577p(this));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == ServiceErrorException.USER_NOT_AUTHENTICATED && i2 == -1) {
            ((MainActivity) Objects.requireNonNull(getActivity())).clearBrowserCache();
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.f22217c = (SettingsViewModel) ViewModelProviders.of((Fragment) this, this.f22216b).get(SettingsViewModel.class);
        this.f22217c.init();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        this.f22217c.reinit();
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        setToolbarTitle(getString(R.string.Settings));
        disableDisplayHomeAsUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view = getView();
        this.f22218d = view.findViewById(R.id.wallets_preference);
        this.f22219e = (TextView) view.findViewById(R.id.wallets_subtitle);
        this.f22220f = view.findViewById(R.id.currency_preference);
        this.f22221g = (TextView) view.findViewById(R.id.currency_subtitle);
        this.f22222h = view.findViewById(R.id.browser_preference);
        this.f22223i = (SwitchCompat) view.findViewById(R.id.passcode_switch);
        this.f22224j = getView().findViewById(R.id.push_preference);
        this.f22225k = view.findViewById(R.id.twitter_preference);
        this.f22226l = view.findViewById(R.id.telegram_preference);
        this.f22227m = view.findViewById(R.id.facebook_preference);
        this.f22228n = view.findViewById(R.id.discord_preference);
        this.f22229o = view.findViewById(R.id.reddit_preference);
        this.f22230p = view.findViewById(R.id.youtube_preference);
        this.f22231q = view.findViewById(R.id.share_preference);
        this.f22232r = view.findViewById(R.id.faq_preference);
        this.f22233s = view.findViewById(R.id.about_preference);
        this.f22234t = view.findViewById(R.id.pin_on_sending_layout);
        this.f22235u = (SwitchCompat) view.findViewById(R.id.request_pin_on_sending_switch);
        this.f22236v = view.findViewById(R.id.pin_advanced);
        this.f22237w = (TextView) view.findViewById(R.id.pin_advanced_subtitle);
        this.f22236v.setOnClickListener(view1 -> new AlertDialog.Builder(getContext()).setItems(R.array.lock_variants, new C15631()).create().show());
        hidePushNotifications();
    }
}
