package com.wallet.crypto.trustapp.ui.importwallet.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.router.QRScannerRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.importwallet.factory.ImportWalletViewModelFactory;
import com.wallet.crypto.trustapp.ui.importwallet.fragment.BaseImportWalletFragment;
import com.wallet.crypto.trustapp.ui.importwallet.fragment.ImportKeystoreFragment;
import com.wallet.crypto.trustapp.ui.importwallet.fragment.ImportMnemonicFragment;
import com.wallet.crypto.trustapp.ui.importwallet.fragment.ImportPrivateKeyFragment;
import com.wallet.crypto.trustapp.ui.importwallet.fragment.ImportWatchWalletFragment;
import com.wallet.crypto.trustapp.ui.importwallet.viewmodel.ImportWalletViewModel;
import com.wallet.crypto.trustapp.util.QRUri;
import com.wallet.crypto.trustapp.widget.TabPagerAdapter;
import dagger.android.AndroidInjection;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.entity.Wallet;

/* compiled from: ImportWalletActivity.kt */
public final class ImportWalletActivity extends BaseActivity {
    /* renamed from: a */
    private final List<Pair<String, Fragment>> f23213a = new ArrayList();
    @Inject
    /* renamed from: b */
    public ImportWalletViewModelFactory f23214b;
    /* renamed from: c f23215c */
    public ImportWalletViewModel viewModel;
    /* renamed from: d */
    private Dialog f23216d;
    /* renamed from: e f23217e */
    private ViewPager viewPager;
    /* renamed from: f f23218f */
    private TabLayout tabLayout;
    /* renamed from: g */
    private final QRScannerRouter f23219g = new QRScannerRouter();

    private final void hideDialog() {
        if (this.f23216d != null) {
            Dialog dialog = this.f23216d;
            if (dialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialog");
                throw null;
            } else if (dialog.isShowing()) {
                dialog = this.f23216d;
                if (dialog != null) {
                    dialog.dismiss();
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("dialog");
                    throw null;
                }
            }
        }
    }

    private final void onError(ErrorEnvelope errorEnvelope) {
        String string;
        hideDialog();
        if (TextUtils.isEmpty(errorEnvelope.message)) {
            string = getString(R.string.importWallet_importKeystoreError_message);
        } else {
            string = errorEnvelope.message;
        }
        if (errorEnvelope.code == 3) {
            string = getString(R.string.importWallet_alreadyAdded_message);
        }
        AlertDialog create = new Builder(this).setTitle(R.string.Error).setMessage(string).setPositiveButton(R.string.OK, null).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…ll)\n            .create()");
        this.f23216d = create;
        Dialog dialog = this.f23216d;
        if (dialog != null) {
            dialog.show();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
            throw null;
        }
    }

    private final void onProgress(boolean z) {
        hideDialog();
        if (z) {
            Context context = this;
            AlertDialog create = new Builder(context).setTitle(R.string.ImportingWallet).setView(new ProgressBar(context)).setCancelable(false).create();
            Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…                .create()");
            this.f23216d = create;
            Dialog dialog = this.f23216d;
            if (dialog != null) {
                dialog.show();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("dialog");
                throw null;
            }
        }
    }

    private final void onWallet(Wallet wallet) {
        Intent intent = new Intent();
        intent.putExtra("wallet", wallet);
        setResult(-1, intent);
        finish();
    }

    private final void onWalletName(int i) {
        invalidateOptionsMenu();
        this.f23213a.clear();
        ImportWalletViewModel importWalletViewModel = this.viewModel;
        if (importWalletViewModel != null) {
            Slip coin = importWalletViewModel.coin();
            ImportWalletViewModel importWalletViewModel2 = this.viewModel;
            if (importWalletViewModel2 != null) {
                boolean isMultiCoin = importWalletViewModel2.isMultiCoin();
                ImportWalletViewModel importWalletViewModel3 = this.viewModel;
                if (importWalletViewModel3 != null) {
                    setTitle(importWalletViewModel3.getScreenTitle(getResources()));
                    importWalletViewModel3 = this.viewModel;
                    if (importWalletViewModel3 != null) {
                        String walletName = importWalletViewModel3.getWalletName(this);
                        ImportMnemonicFragment create = ImportMnemonicFragment.create(walletName, coin);
                        ImportWalletViewModel importWalletViewModel4 = this.viewModel;
                        if (importWalletViewModel4 != null) {
                            create.setOnImportMnemonicListener(importWalletViewModel4);
                            this.f23213a.add(new Pair(getString(R.string.Phrase), create));
                            TabLayout tabLayout = this.tabLayout;
                            if (tabLayout != null) {
                                tabLayout.setVisibility(View.GONE);
                                if (!isMultiCoin) {
                                    ImportWalletViewModel importWalletViewModel5;
                                    Intrinsics.checkExpressionValueIsNotNull(coin, "coin");
                                    if (CoinConfig.supportKeyStoreImport(coin)) {
                                        ImportKeystoreFragment create2 = ImportKeystoreFragment.create(walletName, coin);
                                        importWalletViewModel5 = this.viewModel;
                                        if (importWalletViewModel5 != null) {
                                            create2.setOnImportKeystoreListener(importWalletViewModel5);
                                            this.f23213a.add(new Pair(getString(R.string.KeystoreJSON), create2));
                                        } else {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                            throw null;
                                        }
                                    }
                                    ImportPrivateKeyFragment create3 = ImportPrivateKeyFragment.create(walletName, coin);
                                    importWalletViewModel5 = this.viewModel;
                                    if (importWalletViewModel5 != null) {
                                        create3.setOnImportPrivateKeyListener(importWalletViewModel5);
                                        this.f23213a.add(new Pair(getString(R.string.PrivateKey), create3));
                                        ImportWatchWalletFragment create4 = ImportWatchWalletFragment.create(walletName, coin);
                                        importWalletViewModel2 = this.viewModel;
                                        if (importWalletViewModel2 != null) {
                                            create4.setOnImportWatchWalletListener(importWalletViewModel2);
                                            this.f23213a.add(new Pair(getString(R.string.Address), create4));
                                            TabLayout tabLayout2 = this.tabLayout;
                                            if (tabLayout2 != null) {
                                                tabLayout2.setVisibility(View.VISIBLE);
                                            } else {
                                                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                                                throw null;
                                            }
                                        } else {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                            throw null;
                                        }
                                    } else {
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        throw null;
                                    }
                                }
                                TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), this.f23213a);
                                ViewPager viewPager = this.viewPager;
                                if (viewPager != null) {
                                    viewPager.setAdapter(tabPagerAdapter);
                                    return;
                                } else {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                                    throw null;
                                }
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        QRUri onActivityResult = this.f23219g.onActivityResult(i, i2, intent);
        if (onActivityResult == null) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            BaseImportWalletFragment baseImportWalletFragment = (BaseImportWalletFragment) ((Pair) this.f23213a.get(viewPager.getCurrentItem())).second;
            if (baseImportWalletFragment != null) {
                baseImportWalletFragment.setQRUri(onActivityResult);
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        setContentView((int) R.layout.activity_import_wallet);
        toolbar();
        View findViewById = findViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.viewPager)");
        this.viewPager = (ViewPager) findViewById;
        findViewById = findViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.tabLayout)");
        this.tabLayout = (TabLayout) findViewById;
        TabLayout tabLayout = this.tabLayout;
        if (tabLayout != null) {
            ViewPager viewPager = this.viewPager;
            if (viewPager != null) {
                tabLayout.setupWithViewPager(viewPager);
                FragmentActivity fragmentActivity = this;
                ImportWalletViewModelFactory importWalletViewModelFactory = this.f23214b;
                if (importWalletViewModelFactory != null) {
                    ViewModel viewModel = ViewModelProviders.of(fragmentActivity, (Factory) importWalletViewModelFactory).get(ImportWalletViewModel.class);
                    Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…letViewModel::class.java)");
                    this.viewModel = (ImportWalletViewModel) viewModel;
                    ImportWalletViewModel importWalletViewModel = this.viewModel;
                    if (importWalletViewModel != null) {
                        LifecycleOwner lifecycleOwner = this;
                        importWalletViewModel.progress().observe(lifecycleOwner, it -> {
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            onProgress(it.booleanValue());
                        });
                        importWalletViewModel = this.viewModel;
                        if (importWalletViewModel != null) {
                            importWalletViewModel.error().observe(lifecycleOwner, it -> {
                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                onError(it);
                            });
                            importWalletViewModel = this.viewModel;
                            if (importWalletViewModel != null) {
                                importWalletViewModel.wallet().observe(lifecycleOwner, it -> {
                                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                    onWallet(it);
                                });
                                importWalletViewModel = this.viewModel;
                                if (importWalletViewModel != null) {
                                    importWalletViewModel.walletName().observe(lifecycleOwner, it -> {
                                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                        onWalletName(it.intValue());
                                    });
                                    importWalletViewModel = this.viewModel;
                                    if (importWalletViewModel != null) {
                                        importWalletViewModel.initWithName(getIntent().getStringExtra("coin"));
                                        return;
                                    } else {
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        throw null;
                                    }
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("importWalletViewModelFactory");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
        throw null;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == R.id.action_scan) {
            QRScannerRouter qRScannerRouter = this.f23219g;
            Activity activity = this;
            ImportWalletViewModel importWalletViewModel = this.viewModel;
            if (importWalletViewModel != null) {
                qRScannerRouter.openForResult(activity, importWalletViewModel.coin());
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
        hideDialog();
    }
}
