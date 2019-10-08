package com.wallet.crypto.trustapp.ui.passcode.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.AppComponent;
import com.wallet.crypto.trustapp.di.DaggerAppComponent;
import com.wallet.crypto.trustapp.ui.passcode.factory.PasscodeViewModelFactory;
import com.wallet.crypto.trustapp.ui.passcode.viewmodel.PasscodeViewModel;
import com.wallet.crypto.trustapp.util.FingerprintHelper;
import com.wallet.crypto.trustapp.util.FingerprintHelper.Callback;
import com.wallet.crypto.trustapp.widget.FingerprintInfoView;
import com.wallet.crypto.trustapp.widget.PinKeyboardView;
import com.wallet.crypto.trustapp.widget.PinView;
import com.wallet.crypto.trustapp.widget.PinView.PinCodeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PasscodeFragment.kt */
public final class PasscodeFragment extends DialogFragment implements PinCodeListener, Callback, OnClickListener {
    @Inject
    /* renamed from: j */
    public PasscodeViewModelFactory f22204j;
    /* renamed from: k */
    private PasscodeViewModel f22205k;
    /* renamed from: l */
    private final Handler f22206l = new Handler();
    /* renamed from: m */
    private PinView pinView;    // f22207m
    /* renamed from: n */
    private PinKeyboardView pinKeyboard;    // f22208n
    /* renamed from: o */
    private View f22209o;
    /* renamed from: p */
    private TextView f22210p;
    /* renamed from: q */
    private View showFingerprint;   // f22211q
    /* renamed from: r */
    private FingerprintHelper fingerprintHelper;    // f22212r
    /* renamed from: s */
    private AlertDialog fingerprintWelcomeDialog;   //f22213s
    /* renamed from: t */
    private int f22214t;
    /* renamed from: u */
    private HashMap f22215u;

    public static final /* synthetic */ FingerprintHelper access$getFingerprintHelper$p(PasscodeFragment passcodeFragment) {
        FingerprintHelper fingerprintHelper = passcodeFragment.fingerprintHelper;
        if (fingerprintHelper != null) {
            return fingerprintHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fingerprintHelper");
        throw null;
    }

    public static final /* synthetic */ PinKeyboardView access$getPinKeyboard$p(PasscodeFragment passcodeFragment) {
        PinKeyboardView pinKeyboardView = passcodeFragment.pinKeyboard;
        if (pinKeyboardView != null) {
            return pinKeyboardView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pinKeyboard");
        throw null;
    }

    public static final /* synthetic */ PinView access$getPinView$p(PasscodeFragment passcodeFragment) {
        PinView pinView = passcodeFragment.pinView;
        if (pinView != null) {
            return pinView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pinView");
        throw null;
    }

    public static final /* synthetic */ PasscodeViewModel access$getViewModel$p(PasscodeFragment passcodeFragment) {
        PasscodeViewModel passcodeViewModel = passcodeFragment.f22205k;
        if (passcodeViewModel != null) {
            return passcodeViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    private final Animation createShakeAnimation() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        loadAnimation.setAnimationListener(new PasscodeFragment$createShakeAnimation$1(this));
        Intrinsics.checkExpressionValueIsNotNull(loadAnimation, "shake");
        return loadAnimation;
    }

    private final void onUnlockTime(Long l) {
        if (l != null) {
            if (l.longValue() >= System.currentTimeMillis()) {
                PinKeyboardView pinKeyboardView = this.pinKeyboard;
                if (pinKeyboardView != null) {
                    pinKeyboardView.lock();
                    View view = this.f22209o;
                    if (view != null) {
                        view.setVisibility(View.VISIBLE);
                        String format = new SimpleDateFormat("mm:ss", Locale.getDefault()).format(new Date(l.longValue() - System.currentTimeMillis()));
                        TextView textView = this.f22210p;
                        if (textView != null) {
                            textView.setText(format);
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("unlockTimer");
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("lockContainer");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("pinKeyboard");
                throw null;
            }
        }
        View view2 = this.f22209o;
        if (view2 != null) {
            view2.setVisibility(View.GONE);
            showFingerprintDialog();
            PinKeyboardView pinKeyboardView2 = this.pinKeyboard;
            if (pinKeyboardView2 != null) {
                pinKeyboardView2.unlock();
                PinView pinView = this.pinView;
                if (pinView != null) {
                    pinView.clear();
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("pinView");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("pinKeyboard");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lockContainer");
        throw null;
    }

    @TargetApi(23)
    private final void showFingerprintDialog() {
        Context context = getContext();
        if (context != null) {
            Intrinsics.checkExpressionValueIsNotNull(context, "context ?: return");
            Object systemService = context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (!(systemService instanceof FingerprintManager)) {
                systemService = null;
            }
            FingerprintManager fingerprintManager = (FingerprintManager) systemService;
            if (fingerprintManager != null) {
                this.fingerprintHelper = new FingerprintHelper(context, fingerprintManager, this);
                FingerprintHelper fingerprintHelper = this.fingerprintHelper;
                if (fingerprintHelper != null) {
                    if (fingerprintHelper.isFingerprintAuthAvailable()) {
                        View view = this.showFingerprint;
                        if (view != null) {
                            view.setVisibility(View.VISIBLE);
                            FingerprintInfoView fingerprintInfoView = new FingerprintInfoView(context);
                            fingerprintInfoView.setId(16908312);
                            AlertDialog create = new Builder(context).setTitle(R.string.passcode_authenticationRequired_message)
                                    .setView(fingerprintInfoView)
                                    .setPositiveButton(R.string.pin, new PasscodeFragment$showFingerprintDialog$1(this))
                                    .create();
                            Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(cont…                .create()");
                            this.fingerprintWelcomeDialog = create;
                            create = this.fingerprintWelcomeDialog;
                            if (create != null) {
                                create.show();
                                FingerprintHelper fingerprintHelper2 = this.fingerprintHelper;
                                if (fingerprintHelper2 != null) {
                                    fingerprintHelper2.startListening();
                                } else {
                                    Intrinsics.throwUninitializedPropertyAccessException("fingerprintHelper");
                                    throw null;
                                }
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("fingerprintWelcomeDialog");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("showFingerprint");
                        throw null;
                    }
                    return;
                }
                Intrinsics.throwUninitializedPropertyAccessException("fingerprintHelper");
                throw null;
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22215u;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        AppComponent.Builder builder = DaggerAppComponent.builder();
        FragmentActivity activity = getActivity();
        Application application = activity != null ? activity.getApplication() : null;
        if (application != null) {
            builder.application((App) application);
            builder.build().inject(this);
            Fragment fragment = this;
            PasscodeViewModelFactory passcodeViewModelFactory = this.f22204j;
            if (passcodeViewModelFactory != null) {
                ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) passcodeViewModelFactory).get(PasscodeViewModel.class);
                Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…odeViewModel::class.java)");
                this.f22205k = (PasscodeViewModel) viewModel;
                PasscodeViewModel passcodeViewModel = this.f22205k;
                if (passcodeViewModel != null) {
                    passcodeViewModel.init(3, false);
                    passcodeViewModel = this.f22205k;
                    if (passcodeViewModel != null) {
                        passcodeViewModel.unlockTime().observe(this, l -> onUnlockTime(l));
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        throw null;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
            throw null;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.App");
    }

    public void onAuthenticated() {
        this.f22206l.postDelayed(new Runnable() {
            @Override
            public void run() {
                f22206l.removeCallbacks(this);
                try {
                    LiveData unlockTime = PasscodeFragment.access$getViewModel$p(PasscodeFragment.this).unlockTime();
                    Intrinsics.checkExpressionValueIsNotNull(unlockTime, "viewModel.unlockTime()");
                    Long obj = (Long) unlockTime.getValue();
                    if (obj == null) {
                        obj = Long.valueOf(0);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(obj, "viewModel.unlockTime().value ?: 0");
                    if (obj.longValue() < System.currentTimeMillis()) {
                        dismiss();
                    }
                } catch (Exception unused) {
                    Context context = getContext();
                    if (context != null) {
                        Intrinsics.checkExpressionValueIsNotNull(context, "context ?: return");
                        Toast.makeText(context, "Failed to set PIN code", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, 500);
        AlertDialog alertDialog = this.fingerprintWelcomeDialog;
        if (alertDialog != null) {
            View findViewById = alertDialog.findViewById(16908312);
            if (findViewById instanceof FingerprintInfoView) {
                FingerprintInfoView fingerprintInfoView = (FingerprintInfoView) findViewById;
                String string = getString(R.string.fingerprint_recognized_message);
                Context context = getContext();
                if (context != null) {
                    fingerprintInfoView.showMessage(string, ContextCompat.getColor(context, R.color.green));
                    return;
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fingerprintWelcomeDialog");
        throw null;
    }

    public void onCancel(DialogInterface dialogInterface) {
        Intrinsics.checkParameterIsNotNull(dialogInterface, "dialog");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finishAffinity();
        }
        System.exit(0);
    }

    public void onClick(View view) {
        showFingerprintDialog();
    }

    public void onComplete(String str) {
        PinKeyboardView pinKeyboardView = this.pinKeyboard;
        if (pinKeyboardView != null) {
            pinKeyboardView.lock();
            try {
                PasscodeViewModel passcodeViewModel = this.f22205k;
                if (passcodeViewModel != null) {
                    if (!passcodeViewModel.check(getActivity(), str)) {
                        dismiss();
                    } else {
                        PinView pinView = this.pinView;
                        if (pinView != null) {
                            pinView.startAnimation(createShakeAnimation());
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("pinView");
                            throw null;
                        }
                    }
                    return;
                }
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            } catch (Exception unused) {
                Context context = getContext();
                if (context != null) {
                    Intrinsics.checkExpressionValueIsNotNull(context, "context ?: return");
                    Toast.makeText(context, "Failed to set PIN code", Toast.LENGTH_SHORT).show();
                } else {
                    return;
                }
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("pinKeyboard");
        throw null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finishAffinity();
                }
                System.exit(0);
            }
        };
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.activity_pin, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onDot(String str) {
        try {
            PasscodeViewModel passcodeViewModel = this.f22205k;
            if (passcodeViewModel != null) {
                passcodeViewModel.check(getActivity(), str);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        } catch (Exception unused) {
        }
    }

    public void onError() {
        AlertDialog alertDialog;
        if (this.f22214t < 3) {
            alertDialog = this.fingerprintWelcomeDialog;
            if (alertDialog != null) {
                View findViewById = alertDialog.findViewById(16908312);
                if (findViewById instanceof FingerprintInfoView) {
                    Context context = getContext();
                    if (context != null) {
                        Intrinsics.checkExpressionValueIsNotNull(context, "context ?: return");
                        ((FingerprintInfoView) findViewById).showMessage(context.getString(R.string.fingerprint_notRecognized_message), ContextCompat.getColor(context, R.color.red));
                    } else {
                        return;
                    }
                }
                this.f22206l.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        f22206l.removeCallbacks(this);
                        View view = findViewById;
                        if (view instanceof FingerprintInfoView) {
                            ((FingerprintInfoView) view).showWelcome();
                        }
                    }
                }, 1600);
                this.f22214t++;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("fingerprintWelcomeDialog");
                throw null;
            }
        }
        FingerprintHelper fingerprintHelper = this.fingerprintHelper;
        if (fingerprintHelper != null) {
            fingerprintHelper.stopListening();
            alertDialog = this.fingerprintWelcomeDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("fingerprintWelcomeDialog");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("fingerprintHelper");
        throw null;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog ?: return");
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(-1, -1);
            }
        }
    }

    public void onStop() {
        super.onStop();
        PasscodeFragment passcodeFragment = this;
        if (passcodeFragment.fingerprintHelper != null && passcodeFragment.fingerprintWelcomeDialog != null) {
            FingerprintHelper fingerprintHelper = this.fingerprintHelper;
            if (fingerprintHelper != null) {
                fingerprintHelper.stopListening();
                AlertDialog alertDialog = this.fingerprintWelcomeDialog;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("fingerprintWelcomeDialog");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("fingerprintHelper");
            throw null;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        TextView textView = (TextView) view.findViewById(R.id.title);
        View findViewById = view.findViewById(R.id.pin);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.pin)");
        this.pinView = (PinView) findViewById;
        findViewById = view.findViewById(R.id.lock_container);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<View>(R.id.lock_container)");
        this.f22209o = findViewById;
        findViewById = view.findViewById(R.id.unlock_timer);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.unlock_timer)");
        this.f22210p = (TextView) findViewById;
        findViewById = view.findViewById(R.id.keyboard);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.keyboard)");
        this.pinKeyboard = (PinKeyboardView) findViewById;
        view = view.findViewById(R.id.open_fingerprint);
        Intrinsics.checkExpressionValueIsNotNull(view, "view.findViewById<View>(R.id.open_fingerprint)");
        this.showFingerprint = view;
        view = this.showFingerprint;
        if (view != null) {
            view.setOnClickListener(this);
            PinView pinView = this.pinView;
            if (pinView != null) {
                PinKeyboardView pinKeyboardView = this.pinKeyboard;
                if (pinKeyboardView != null) {
                    pinView.attachPinKeyboard(pinKeyboardView);
                    pinView = this.pinView;
                    if (pinView != null) {
                        pinView.setListener(this);
                        textView.setText(R.string.lock_enter_passcode_view_model_initial);
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("pinView");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("pinKeyboard");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("pinView");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("showFingerprint");
        throw null;
    }
}
