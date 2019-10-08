package com.wallet.crypto.trustapp.ui.walletconnect.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCState;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WalletConnectViewData;
import com.wallet.crypto.trustapp.ui.walletconnect.factory.WalletConnectViewModelFactory;
import com.wallet.crypto.trustapp.ui.walletconnect.view.WCOperationAdapter;
import com.wallet.crypto.trustapp.ui.walletconnect.viewmodel.WalletConnectViewModel;
import dagger.android.AndroidInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: WalletConnectActivity.kt */
public final class WalletConnectActivity extends BaseActivity implements OnCheckedChangeListener {
    private HashMap _$_findViewCache;
    private Dialog actionDialog;
    private WCOperationAdapter adapter;
    private TextView address;
    private SwitchCompat autoSign;
    private Dialog error;
    private RecyclerView list;
    private View logContainer;
    private ImageView logo;
    private TextView operations;
    private Dialog progress;
    private View root;
    private TextView url;
    public WalletConnectViewModel viewModel;
    @Inject
    public WalletConnectViewModelFactory viewModelFactory;

    public static final /* synthetic */ WCOperationAdapter access$getAdapter$p(WalletConnectActivity walletConnectActivity) {
        WCOperationAdapter wCOperationAdapter = walletConnectActivity.adapter;
        if (wCOperationAdapter != null) {
            return wCOperationAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public static final TextView access$getOperations$p(WalletConnectActivity walletConnectActivity) {
        TextView textView = walletConnectActivity.operations;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("operations");
        throw null;
    }

    private final void bind(WalletConnectViewData walletConnectViewData) {
        View view = this.root;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            GlideRequest centerCrop = GlideApp.with((FragmentActivity) this).load(walletConnectViewData.getLogoUrl()).centerCrop();
            ImageView imageView = this.logo;
            if (imageView != null) {
                centerCrop.into(imageView);
                TextView textView = this.url;
                if (textView != null) {
                    textView.setText(walletConnectViewData.getWcUrl());
                    textView = this.address;
                    if (textView != null) {
                        textView.setText(walletConnectViewData.getAddress());
                        mo35292a(walletConnectViewData.getName());
                        bindAutoSign(walletConnectViewData.isAutoSigned());
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException(Address.TYPE_NAME);
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("url");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("logo");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        throw null;
    }

    private final void bindAutoSign(boolean z) {
        SwitchCompat switchCompat = this.autoSign;
        String str = "autoSign";
        if (switchCompat != null) {
            switchCompat.setOnCheckedChangeListener(null);
            switchCompat = this.autoSign;
            if (switchCompat != null) {
                switchCompat.setChecked(z);
                SwitchCompat switchCompat2 = this.autoSign;
                if (switchCompat2 != null) {
                    switchCompat2.setOnCheckedChangeListener(this);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException(str);
        throw null;
    }

    private final boolean hideDialogs() {
        boolean z;
        Dialog dialog = this.error;
        if (dialog == null || dialog == null || !dialog.isShowing()) {
            z = false;
        } else {
            dialog = this.error;
            if (dialog != null) {
                dialog.dismiss();
            }
            z = true;
        }
        Dialog dialog2 = this.progress;
        if (!(dialog2 == null || dialog2 == null || !dialog2.isShowing())) {
            dialog = this.progress;
            if (dialog != null) {
                dialog.dismiss();
            }
            z = true;
        }
        dialog2 = this.actionDialog;
        if (dialog2 == null || dialog2 == null || !dialog2.isShowing()) {
            return z;
        }
        dialog = this.actionDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        return true;
    }

    private final boolean hideLogs() {
        View view = this.logContainer;
        String str = "logContainer";
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        } else if (view.getVisibility() == View.GONE) {
            return false;
        } else {
            view = this.logContainer;
            if (view != null) {
                view.setVisibility(View.GONE);
                return true;
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
    }

    private final void onOperation(WCOperation wCOperation) {
        hideDialogs();
        if (wCOperation != null) {
            this.actionDialog = new Builder(this)
                    .setTitle(wCOperation.getTitle(this))
                    .setMessage(wCOperation.getDescription(this))
                    .setCancelable(false).setPositiveButton(wCOperation.getApproveLabel(this), new WalletConnectActivity$onOperation$1(wCOperation))
                    .setNegativeButton(wCOperation.getRejectLabel(this), new WalletConnectActivity$onOperation$2(wCOperation)).create();
            Dialog dialog = this.actionDialog;
            if (dialog != null) {
                dialog.show();
            }
        }
    }

    private final void onShowApproveDialog(WalletConnectViewData walletConnectViewData) {
        View view = this.root;
        if (view != null) {
            view.setVisibility(View.GONE);
            this.actionDialog = new Builder(this)
                    .setTitle(walletConnectViewData.getName())
                    .setMessage(walletConnectViewData.getUrl())
                    .setCancelable(false)
                    .setPositiveButton((int) R.string.Approve, new WalletConnectActivity$onShowApproveDialog$1(this, walletConnectViewData))
                    .setNegativeButton((int) R.string.Cancel, new WalletConnectActivity$onShowApproveDialog$2(this)).create();
            Dialog dialog = this.actionDialog;
            if (dialog != null) {
                dialog.show();
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        throw null;
    }

    private final void showError(java.lang.Throwable r3) {
        String message = r3.getMessage();
        message = message == null || message.isEmpty() ? r3.getMessage() : getString(R.string.UnknownError);
        hideDialogs();
        error = new AlertDialog.Builder(this)
                .setTitle(R.string.Error)
                .setMessage(message)
                .setPositiveButton(R.string.OK, WalletConnectActivity$showError$1.INSTANCE)
                .setOnCancelListener(new WalletConnectActivity$showError$2(this))
                .create();
        if (error != null)
            error.show();
    }

    private final void showList() {
        View view = this.logContainer;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("logContainer");
            throw null;
        }
    }

    private final void toggleProgress(boolean z) {
        hideDialogs();
        if (z) {
            ProgressBar progressBar = new ProgressBar(this);
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -2);
            marginLayoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen.big_margin));
            marginLayoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.big_margin);
            marginLayoutParams.setMarginEnd(getResources().getDimensionPixelSize(R.dimen.big_margin));
            marginLayoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.big_margin);
            progressBar.setLayoutParams(marginLayoutParams);
            this.progress = new Builder(this).setTitle((int) R.string.WalletConnect).setView(progressBar).setCancelable(false).create();
            Dialog dialog = this.progress;
            if (dialog != null) {
                dialog.show();
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public final WalletConnectViewModel getViewModel() {
        WalletConnectViewModel walletConnectViewModel = this.viewModel;
        if (walletConnectViewModel != null) {
            return walletConnectViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public final WalletConnectViewModelFactory getViewModelFactory() {
        WalletConnectViewModelFactory walletConnectViewModelFactory = this.viewModelFactory;
        if (walletConnectViewModelFactory != null) {
            return walletConnectViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public void onBackPressed() {
        if (hideLogs() && !hideDialogs()) {
            new Builder(this)
                    .setTitle((int) R.string.Error)
                    .setMessage((int) R.string.DisconnectWarning)
                    .setPositiveButton((int) R.string.OK, (d, i) -> super.onBackPressed())
                    .setNegativeButton((int) R.string.Cancel, (d, i) -> {})
                    .create()
                    .show();
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SwitchCompat switchCompat = this.autoSign;
        String str = "autoSign";
        if (switchCompat != null) {
            switchCompat.setOnCheckedChangeListener(null);
            WalletConnectViewModel walletConnectViewModel = this.viewModel;
            if (walletConnectViewModel != null) {
                walletConnectViewModel.setAutoSign(z);
                switchCompat = this.autoSign;
                if (switchCompat != null) {
                    switchCompat.setOnCheckedChangeListener(this);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException(str);
        throw null;
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_wallet_connect);
        toolbar();
        setTitle(R.string.WalletConnect);
        this.adapter = new WCOperationAdapter();
        View findViewById = findViewById(R.id.root);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.root)");
        this.root = findViewById;
        findViewById = findViewById(R.id.logo);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.logo)");
        this.logo = (ImageView) findViewById;
        findViewById = findViewById(R.id.url);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.url)");
        this.url = (TextView) findViewById;
        findViewById = findViewById(R.id.address);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.address)");
        this.address = (TextView) findViewById;
        findViewById = findViewById(R.id.operations);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.operations)");
        this.operations = (TextView) findViewById;
        findViewById = findViewById(R.id.auto_sign);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.auto_sign)");
        this.autoSign = (SwitchCompat) findViewById;
        SwitchCompat switchCompat = this.autoSign;
        if (switchCompat != null) {
            switchCompat.setOnCheckedChangeListener(this);
            findViewById(R.id.operations_container).setOnClickListener(view -> showList());
            findViewById = findViewById(R.id.operation_log_container);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.operation_log_container)");
            this.logContainer = findViewById;
            findViewById = findViewById(R.id.list);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.list)");
            this.list = (RecyclerView) findViewById;
            RecyclerView recyclerView = this.list;
            String str = "list";
            if (recyclerView != null) {
                ViewCompat.setNestedScrollingEnabled(recyclerView, false);
                recyclerView = this.list;
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView = this.list;
                    if (recyclerView != null) {
                        WCOperationAdapter wCOperationAdapter = this.adapter;
                        if (wCOperationAdapter != null) {
                            recyclerView.setAdapter(wCOperationAdapter);
                            findViewById = this.root;
                            if (findViewById != null) {
                                findViewById.setVisibility(View.GONE);
                                Factory factory = this.viewModelFactory;
                                if (factory != null) {
                                    ViewModel viewModel = ViewModelProviders.of((FragmentActivity) this, factory).get(WalletConnectViewModel.class);
                                    Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ectViewModel::class.java)");
                                    this.viewModel = (WalletConnectViewModel) viewModel;
                                    WalletConnectViewModel walletConnectViewModel = this.viewModel;
                                    str = "viewModel";
                                    if (walletConnectViewModel != null) {
                                        walletConnectViewModel.getProgress().observe(this, it -> {
                                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                            toggleProgress(((Boolean)it).booleanValue());
                                        });
                                        walletConnectViewModel = this.viewModel;
                                        if (walletConnectViewModel != null) {
                                            walletConnectViewModel.getError().observe(this, it -> {
                                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                                showError((Throwable) it);
                                            });
                                            walletConnectViewModel = this.viewModel;
                                            if (walletConnectViewModel != null) {
                                                walletConnectViewModel.getSession().observe(this, it -> {
                                                    switch (it.getState()) {
                                                        case NOT_APPROVED:
                                                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                                            onShowApproveDialog(it);
                                                            break;
                                                        case APPROVED:
                                                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                                            bind(it);
                                                            break;
                                                        case CLOSED:
                                                        case REJECTED:
                                                            finish();
                                                            break;
                                                    }
                                                });
                                                walletConnectViewModel = this.viewModel;
                                                if (walletConnectViewModel != null) {
                                                    walletConnectViewModel.getOperation().observe(this, operation -> onOperation(operation));
                                                    walletConnectViewModel = this.viewModel;
                                                    if (walletConnectViewModel != null) {
                                                        walletConnectViewModel.getLog().observe(this, new WalletConnectActivity$onCreate$6(this));
                                                        walletConnectViewModel = this.viewModel;
                                                        if (walletConnectViewModel != null) {
                                                            walletConnectViewModel.init(getIntent().getStringExtra("bridge_uri"));
                                                            Lifecycle lifecycle = getLifecycle();
                                                            WalletConnectViewModel walletConnectViewModel2 = this.viewModel;
                                                            if (walletConnectViewModel2 != null) {
                                                                lifecycle.addObserver(walletConnectViewModel2);
                                                                return;
                                                            } else {
                                                                Intrinsics.throwUninitializedPropertyAccessException(str);
                                                                throw null;
                                                            }
                                                        }
                                                        Intrinsics.throwUninitializedPropertyAccessException(str);
                                                        throw null;
                                                    }
                                                    Intrinsics.throwUninitializedPropertyAccessException(str);
                                                    throw null;
                                                }
                                                Intrinsics.throwUninitializedPropertyAccessException(str);
                                                throw null;
                                            }
                                            Intrinsics.throwUninitializedPropertyAccessException(str);
                                            throw null;
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException(str);
                                        throw null;
                                    }
                                    Intrinsics.throwUninitializedPropertyAccessException(str);
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("root");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException(str);
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoSign");
        throw null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (!hideLogs()) {
            finish();
        }
        return true;
    }

    protected void onPause() {
        super.onPause();
        hideDialogs();
    }

    public final void setViewModel(WalletConnectViewModel walletConnectViewModel) {
        Intrinsics.checkParameterIsNotNull(walletConnectViewModel, "<set-?>");
        this.viewModel = walletConnectViewModel;
    }

    public final void setViewModelFactory(WalletConnectViewModelFactory walletConnectViewModelFactory) {
        Intrinsics.checkParameterIsNotNull(walletConnectViewModelFactory, "<set-?>");
        this.viewModelFactory = walletConnectViewModelFactory;
    }

    /* renamed from: setTitle */
    public void mo35292a(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(str);
        }
    }
}
