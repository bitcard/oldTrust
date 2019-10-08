package com.wallet.crypto.trustapp.ui.start.activity;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

//import com.github.javiersantos.appupdater.AppUpdater;
//import com.github.javiersantos.appupdater.enums.Display;
//import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.DappLink;
import com.wallet.crypto.trustapp.entity.DappLink.Type;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment;
import com.wallet.crypto.trustapp.ui.dapp.network.FileChooseListener;
import com.wallet.crypto.trustapp.ui.dapp.view.OnDappLinkClickLister;
import com.wallet.crypto.trustapp.ui.start.factory.MainViewModelFactory;
import com.wallet.crypto.trustapp.ui.start.fragment.MainFragment;
import com.wallet.crypto.trustapp.ui.start.view.AppFollowUs;
import com.wallet.crypto.trustapp.ui.start.viewmodel.MainViewModel;
import com.wallet.crypto.trustapp.ui.start.viewmodel.MainViewModel.DeepUrl;
import com.wallet.crypto.trustapp.util.PermissionUtil;
import com.wallet.crypto.trustapp.util.RootUtil;
import com.wallet.crypto.trustapp.util.SystemUtils;
import com.wallet.crypto.trustapp.widget.FileChooserView;
import com.wallet.crypto.trustapp.widget.FileChooserView.OnChooserClickListener;
import com.wallet.crypto.trustapp.widget.FollowUsView;
import com.wallet.crypto.trustapp.widget.FollowUsView.FollowUsListener;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import hotchemi.android.rate.AppRate;
import trust.blockchain.Slip;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.ServiceErrorException;

/* renamed from: com.wallet.crypto.trustapp.ui.start.activity.MainActivity */
public class MainActivity extends ToolbarActivity implements HasSupportFragmentInjector, FollowUsListener, OnDappLinkClickLister, FileChooseListener {
    @Inject
    /* renamed from: a */
    DispatchingAndroidInjector<Fragment> f23706a;
    @Inject
    /* renamed from: b */
    MainViewModelFactory f23707b;
    /* renamed from: c */
    private MainViewModel f23708c;
    @Inject
    /* renamed from: d */
    SessionRepository f23709d;
    @Inject
    /* renamed from: e */
    PreferenceRepositoryType f23710e;
    /* renamed from: f */
    private Dialog f23711f;
    /* renamed from: g */
    private ValueCallback<Uri[]> f23712g;
    /* renamed from: h */
    private String f23713h;
    /* renamed from: i */
    private MainFragment f23714i;
    /* renamed from: j */
    private BrowserFragment f23715j;

    /* renamed from: com.wallet.crypto.trustapp.ui.start.activity.MainActivity$1 */
    class C25281 implements OnChooserClickListener {
        C25281() {
        }

        public void onCaptureImageClicked() {
            MainActivity.this.checkCameraPermission();
        }

        public void onImportImageFromGalleryClicked() {
            MainActivity.this.checkExternalStoragePermission();
        }
    }

    /* renamed from: b */
    public static /* synthetic */ void m336b(MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        mainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://help.trustwallet.com/hc/en-us/articles/360001267574-How-to-recover-from-Wallet-cannot-be-decrypted-error")));
    }

    private Builder buildDialog() {
        hideDialog();
        return new Builder(this);
    }

    private void checkCameraPermission() {
        PermissionUtil.hasPermission(this, "android.permission.CAMERA", 1, ()->startCameraActivity());
    }

    private void checkExternalStoragePermission() {
        PermissionUtil.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE", 2, ()->startGalleryActivity());
    }

    private void checkRoot() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (RootUtil.isDeviceRooted() && defaultSharedPreferences.getBoolean("should_show_root_warning", true)) {
            defaultSharedPreferences.edit().putBoolean("should_show_root_warning", false).apply();
            new Builder(this)
                    .setTitle(R.string.app_device_jailbreak_title)
                    .setMessage(R.string.app_device_root_description)
                    .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            lambda$checkRoot$4(dialogInterface, i);
                        }
                    }).show();
        }
    }

    private void checkUpdate() {
        if ("s3".equalsIgnoreCase("s3")) {
//            AppUpdater appUpdater = new AppUpdater(this);
//            appUpdater.setUpdateFrom(UpdateFrom.JSON);
//            appUpdater.setUpdateJSON("https://api.trustwallet.com/appcheck/android");
//            appUpdater.setDisplay(Display.DIALOG);
//            appUpdater.showAppUpdated(Boolean.valueOf(false));
//            appUpdater.start();
        }
    }

    private void followUs() {
//        new AppFollowUs().start(this, new FollowUsView(this, this));
        new AppFollowUs().start(this, new FollowUsView(this, this), 0, 12);
    }

    private MenuFragment getCurrentFragment() {
        List fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        int i = 0;
        while (i < size) {
            if (((Fragment) fragments.get(i)).isAdded() && ((Fragment) fragments.get(i)).isVisible()) {
                return (MenuFragment) fragments.get(i);
            }
            i++;
        }
        return null;
    }

    private void handleCapturesImage(String str) {
        if (this.f23713h != null) {
            postFileCallback(Uri.fromFile(new File(str)));
            this.f23713h = null;
        }
    }

    private void hideDialog() {
        Dialog dialog = this.f23711f;
        if (dialog != null && dialog.isShowing()) {
            this.f23711f.dismiss();
            this.f23711f = null;
        }
    }

    private void isSkip(Boolean bool) {
        if (!(bool == null || bool.booleanValue())) {
            if (!((App) getApplication()).isShowedSkipBackup()) {
                new Builder(this)
                        .setTitle(R.string.YourWalletNotBackedUp)
                        .setMessage(R.string.IfYouLoseAccessWarning)
                        .setPositiveButton(R.string.OK, ((dialogInterface, i) -> f23708c.setIsSkipBackup(this)))
                        .setCancelable(false).create().show();
                ((App) getApplication()).setIsShowedSkipBackup(true);
            }
        }
    }

    static void lambda$checkRoot$4(DialogInterface dialogInterface, int i) {
    }

    static void lambda$onWalletNotExportable$2(DialogInterface dialogInterface, int i) {
    }

    private void onDeepLink(DeepUrl deepUrl) {
        onDappLinkClick(deepUrl.f17000a, deepUrl.f17001b);
    }

    private void onIntent(Intent intent) {
        Asset asset = (Asset) intent.getParcelableExtra("asset");
        if (asset != null) {
            this.f23714i.onAssetClick(asset);
            intent.removeExtra("asset");
        }
    }

    private void postFileCallback(Uri uri) {
        if (uri != null) {
            ValueCallback valueCallback = this.f23712g;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(new Uri[]{uri});
                this.f23712g = null;
            }
        }
    }

    private void rateUs() {
        if (!"s3".equalsIgnoreCase("s3")) {
            if (SystemUtils.isGooglePlaySafe(this)) {
                AppRate with = AppRate.with(this);
                with.setInstallDays(0);
                with.setLaunchTimes(7);
                with.setRemindInterval(3);
                with.setShowLaterButton(false);
                with.setTitle(R.string.RateTrustWalletTitle);
                with.setMessage(R.string.RateTrustWalletMessage);
                with.monitor();
                AppRate.showRateDialogIfMeetsConditions(this);
            }
        }
    }

    private <T extends Fragment> T showFragment(T t, String str, boolean z) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        MenuFragment currentFragment = getCurrentFragment();
        List fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        Object obj = null;
        for (int i = 0; i < size; i++) {
            if (t == fragments.get(i)) {
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            beginTransaction.add(R.id.fragment_container, t, str);
            beginTransaction.commit();
            beginTransaction = getSupportFragmentManager().beginTransaction();
        }
        if (currentFragment != null) {
            beginTransaction.hide(currentFragment);
        }
        if (z) {
            beginTransaction.addToBackStack("");
        }
        beginTransaction.setCustomAnimations(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
        beginTransaction.show(t);
        beginTransaction.commit();
        return t;
    }

    private void showImageChooserDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        FileChooserView fileChooserView = new FileChooserView(this);
        fileChooserView.setOnChooserClickListener(new C25281());
        bottomSheetDialog.setOnCancelListener(dialogInterface -> f23712g.onReceiveValue(null));
        bottomSheetDialog.setContentView(fileChooserView);
        bottomSheetDialog.setOnShowListener(new C1588i(BottomSheetBehavior.from((View) fileChooserView.getParent()), fileChooserView));
        bottomSheetDialog.show();
        this.f23711f = bottomSheetDialog;
    }

    private void showMigrationWarning() {
        if (getSharedPreferences("migration", 0).getAll().size() > 1) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!defaultSharedPreferences.getBoolean("is_show_migration_warning", false)) {
                defaultSharedPreferences.edit().putBoolean("is_show_migration_warning", true).apply();
                new Builder(this).setTitle("Great News! Big Update! 🚀").setMessage("We have made a huge progress towards supporting and simplifying management of your tokens across blockchains. \n\nTake a look on how to create Multi-Coin Wallet in Trust!").setPositiveButton(R.string.LearnMore, new C1585b(this)).create().show();
            }
        }
    }

    private void startCameraActivity() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UUID.randomUUID().toString());
            stringBuilder.append(".jpg");
            File file = new File(getFilesDir(), stringBuilder.toString());
            this.f23713h = file.getAbsolutePath();
            Uri uriForFile = FileProvider.getUriForFile(this, "com.wallet.crypto.trustapp.fileProvider", file);
            PermissionUtil.grantUriPermission(this, intent, uriForFile);
            intent.putExtra("output", uriForFile);
            startActivityForResult(intent, ServiceErrorException.KEY_STORE_ERROR);
        }
    }

    private void startGalleryActivity() {
        Intent type = new Intent("android.intent.action.GET_CONTENT").setType("image/*");
        if (type.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(type, getString(R.string.dapp_dialog_choose_source_select_image)), 2001);
        }
    }

    public void clearBrowserCache() {
        showBrowser().clearCache();
    }

    public boolean hasOpenedBrowser() {
        List fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        for (int i = 0; i < size; i++) {
            if (fragments.get(i) == this.f23715j) {
                return true;
            }
        }
        return false;
    }

    public void hideBrowser() {
        getSupportFragmentManager().popBackStack();
        invalidateOptionsMenu();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == ServiceErrorException.KEY_STORE_ERROR) {
            handleCapturesImage(this.f23713h);
        } else if (i != 1023) {
            if (i != 2001) {
                super.onActivityResult(i, i2, intent);
            } else if (intent != null && intent.getData() != null) {
                postFileCallback(intent.getData());
            }
        } else if (hasOpenedBrowser()) {
            this.f23715j.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        MenuFragment currentFragment = getCurrentFragment();
        if (currentFragment == null || !currentFragment.onBack()) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        rateUs();
        followUs();
        toolbar();
        setTitle("");
        setSubtitle("");
        disableDisplayHomeAsUp();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("main-fragment");
        if (findFragmentByTag != null) {
            this.f23714i = (MainFragment) findFragmentByTag;
        } else {
            this.f23714i = new MainFragment();
        }

        this.f23715j = new BrowserFragment();

        showFragment(this.f23714i, "main-fragment", false);
        this.f23708c = (MainViewModel) ViewModelProviders.of(this, this.f23707b).get(MainViewModel.class);
        this.f23708c.walletNotExportable().observe(this, session -> onWalletNotExportable(session));
        this.f23708c.deepLink().observe(this, deepUrl -> onDeepLink((DeepUrl) deepUrl));
        this.f23708c.pendingCount().observe(this, integer -> f23714i.onPending(integer.intValue()));
        this.f23708c.isSkipBackup().observe(this, aBoolean -> isSkip(aBoolean));
        checkUpdate();
        showMigrationWarning();
//        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
//        FirebaseAnalytics.getInstance(this);
        onIntent(getIntent());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        new TWToolbarHelper(toolbar()).reset();
        return super.onCreateOptionsMenu(menu);
    }

    public void onDappLinkClick(String str) {
        onDappLinkClick(str, this.f23709d.getSession().wallet.defaultAccount().coin);
    }

    public void onFacebookClicked() {
        this.f23708c.openFacebook(this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    protected void onPause() {
        super.onPause();
        Dialog dialog = this.f23711f;
        if (dialog != null && dialog.isShowing()) {
            this.f23711f.dismiss();
        }
        this.f23708c.pause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (PermissionUtil.isPermissionGranted(iArr)) {
            switch (i) {
                case 1:
                    startCameraActivity();
                    break;
                case 2:
                    startGalleryActivity();
                    break;
                default:
                    break;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        setSubtitle("");
        this.f23708c.init(this, ((App) getApplication()).popDeepLink());
        checkRoot();
    }

    public boolean onShowFileChooser(ValueCallback<Uri[]> valueCallback) {
        this.f23712g = valueCallback;
        showImageChooserDialog();
        return true;
    }

    public void onTelegramClicked() {
        this.f23708c.openTelegram(this);
    }

    public void onTwitterClicked() {
        this.f23708c.openTwitter(this);
    }

    public void onWalletNotExportable(Session session) {
        this.f23711f = buildDialog()
                .setTitle(R.string.passwordStore_decryptionError_message)
                .setMessage(getString(R.string.decrypt_warningWalletCannotBeDecrypted_dialog_description, new Object[]{session.wallet.name}))
                .setPositiveButton(R.string.OK, C1591l.f16988a)
                .setNegativeButton(R.string.MoreDetails, new C1589j(this)).create();
        this.f23711f.show();
    }

    public BrowserFragment showBrowser() {
        if (getCurrentFragment() instanceof BrowserFragment) {
            return this.f23715j;
        }
        BrowserFragment browserFragment = this.f23715j;
        showFragment(browserFragment, "browser", true);
        return browserFragment;
    }

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.f23706a;
    }

    public void onDappLinkClick(String str, Slip slip) {
        showBrowser().openPage(new DappLink(str, "", 0, Type.bookmark, slip));
    }
}
