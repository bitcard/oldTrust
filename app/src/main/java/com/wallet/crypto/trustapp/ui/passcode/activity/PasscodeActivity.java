package com.wallet.crypto.trustapp.ui.passcode.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.passcode.factory.PasscodeViewModelFactory;
import com.wallet.crypto.trustapp.ui.passcode.viewmodel.PasscodeViewModel;
import com.wallet.crypto.trustapp.util.FingerprintHelper;
import com.wallet.crypto.trustapp.util.FingerprintHelper.Callback;
import com.wallet.crypto.trustapp.widget.FingerprintInfoView;
import com.wallet.crypto.trustapp.widget.PinKeyboardView;
import com.wallet.crypto.trustapp.widget.PinView;
import com.wallet.crypto.trustapp.widget.PinView.PinCodeListener;
import com.wallet.crypto.trustapp.widget.SimpleAnimationListener;
import dagger.android.AndroidInjection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

public class PasscodeActivity extends BaseActivity implements PinCodeListener, Callback, OnClickListener {
    @Inject
    /* renamed from: a */
    protected PasscodeViewModelFactory f23230a;
    /* renamed from: b */
    private PasscodeViewModel f23231b;
    /* renamed from: c */
    private final Handler f23232c = new Handler();
    /* renamed from: d */
    private PinView f23233d;
    /* renamed from: e */
    private PinKeyboardView f23234e;
    /* renamed from: f */
    private View f23235f;
    /* renamed from: g */
    private TextView f23236g;
    /* renamed from: h */
    private View f23237h;
    /* renamed from: i */
    private FingerprintHelper f23238i;
    /* renamed from: j */
    private AlertDialog f23239j;
    /* renamed from: k */
    private int f23240k;

    /* renamed from: com.wallet.crypto.trustapp.ui.passcode.activity.PasscodeActivity$2 */
    class C15422 implements Runnable {
        C15422() {
        }

        public void run() {
            PasscodeActivity.this.f23232c.removeCallbacks(this);
            try {
                PasscodeActivity.this.f23231b.fingerprintAuthenticated(PasscodeActivity.this);
            } catch (Exception unused) {
                Toast.makeText(PasscodeActivity.this, "Failed to set PIN code", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.passcode.activity.PasscodeActivity$1 */
    class C24651 extends SimpleAnimationListener {
        C24651() {
        }

        public void onAnimationEnd(Animation animation) {
            PasscodeActivity.this.f23233d.clear();
            PasscodeActivity.this.f23234e.unlock();
        }
    }

    private Animation createShakeAnimation() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        loadAnimation.setAnimationListener(new C24651());
        return loadAnimation;
    }

    private void onUnlockTime(Long l) {
        if (l != null) {
            if (l.longValue() >= System.currentTimeMillis()) {
                this.f23234e.lock();
                this.f23235f.setVisibility(View.VISIBLE);
                this.f23236g.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(new Date(l.longValue() - System.currentTimeMillis())));
                return;
            }
        }
        this.f23235f.setVisibility(View.GONE);
        showFingerprintDialog();
        this.f23234e.unlock();
        this.f23233d.clear();
    }

    @TargetApi(23)
    private void showFingerprintDialog() {
        if (!(VERSION.SDK_INT < 23 || this.f23231b.action() == 1 || this.f23231b.action() == 4)) {
            if (this.f23231b.unlockTime().getValue() == null) {
                FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
                if (fingerprintManager != null) {
                    this.f23238i = new FingerprintHelper(this, fingerprintManager, this);
                    if (this.f23238i.isFingerprintAuthAvailable()) {
                        this.f23237h.setVisibility(View.VISIBLE);
                        FingerprintInfoView fingerprintInfoView = new FingerprintInfoView(this);
                        fingerprintInfoView.setId(16908312);
                        this.f23239j = new Builder(this).setTitle(R.string.passcode_authenticationRequired_message)
                                .setView(fingerprintInfoView)
                                .setPositiveButton(R.string.pin, ((dialogInterface, i) -> f23238i.stopListening()))
                                .create();
                        this.f23239j.show();
                        this.f23238i.startListening();
                    }
                    return;
                }
                return;
            }
        }
        this.f23237h.setVisibility(8);
    }

    public void onAuthenticated() {
        this.f23232c.postDelayed(new C15422(), 500);
        View findViewById = this.f23239j.findViewById(16908312);
        if (findViewById instanceof FingerprintInfoView) {
            ((FingerprintInfoView) findViewById).showMessage(getString(R.string.fingerprint_recognized_message), ContextCompat.getColor(this, R.color.green));
        }
    }

    public void onClick(View view) {
        showFingerprintDialog();
    }

    public void onComplete(String str) {
        this.f23234e.lock();
        try {
            if (this.f23231b.check(this, str)) {
                this.f23233d.startAnimation(createShakeAnimation());
            } else if (this.f23231b.action() == 1) {
                overridePendingTransition(R.anim.translation_in, R.anim.translation_out);
            } else if (this.f23231b.action() == 3) {
                overridePendingTransition(0, 0);
            }
        } catch (Exception unused) {
            Toast.makeText(this, "Failed to set PIN code", 0).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_pin);
        this.f23231b = (PasscodeViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23230a).get(PasscodeViewModel.class);
        this.f23231b.init(getIntent());
        TextView textView = (TextView) findViewById(R.id.title);
        this.f23233d = (PinView) findViewById(R.id.pin);
        this.f23235f = findViewById(R.id.lock_container);
        this.f23236g = (TextView) findViewById(R.id.unlock_timer);
        this.f23234e = (PinKeyboardView) findViewById(R.id.keyboard);
        this.f23237h = findViewById(R.id.open_fingerprint);
        this.f23237h.setOnClickListener(this);
        this.f23233d.attachPinKeyboard(this.f23234e);
        this.f23233d.setListener(this);
        int action = this.f23231b.action();
        if (action == 1) {
            textView.setText(R.string.lock_create_passcode_view_model_initial);
        } else if (action != 4) {
            textView.setText(R.string.lock_enter_passcode_view_model_initial);
        } else {
            textView.setText(R.string.lock_create_passcode_view_model_confirm);
        }
        this.f23231b.unlockTime().observe(this, aLong -> onUnlockTime(aLong));
    }

    public void onDot(String str) {
        if (this.f23231b.action() == 3 || this.f23231b.action() == 2) {
            try {
                this.f23231b.check(this, str);
            } catch (Exception unused) {
            }
        }
    }

    public void onError() {
        if (this.f23240k < 3) {
            final View findViewById = this.f23239j.findViewById(16908312);
            if (findViewById instanceof FingerprintInfoView) {
                ((FingerprintInfoView) findViewById).showMessage(getString(R.string.fingerprint_notRecognized_message), ContextCompat.getColor(this, R.color.red));
            }
            this.f23232c.postDelayed(new Runnable() {
                public void run() {
                    PasscodeActivity.this.f23232c.removeCallbacks(this);
                    View view = findViewById;
                    if (view instanceof FingerprintInfoView) {
                        ((FingerprintInfoView) view).showWelcome();
                    }
                }
            }, 1600);
            this.f23240k++;
            return;
        }
        this.f23238i.stopListening();
        this.f23239j.dismiss();
    }

    protected void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.f23239j;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (VERSION.SDK_INT >= 23) {
            FingerprintHelper fingerprintHelper = this.f23238i;
            if (fingerprintHelper != null) {
                fingerprintHelper.stopListening();
            }
        }
    }
}
