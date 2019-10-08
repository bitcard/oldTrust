package com.wallet.crypto.trustapp.ui.settings.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType.LockAfterVariants;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ManageWalletsRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.router.PushNotificationsSettingsRouter;
import com.wallet.crypto.trustapp.router.SelectCurrencyRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.settings.activity.AboutActivity;
import com.wallet.crypto.trustapp.ui.settings.activity.BrowserSettingsActivity;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.ThreadLocalRandom;
import trust.blockchain.entity.ServiceErrorException;

public class SettingsViewModel extends BaseViewModel {
    /* renamed from: d */
    private static int[] f21433d = new int[]{R.string.welldone_viewmodel_sharing_text1, R.string.welldone_viewmodel_sharing_text2, R.string.welldone_viewmodel_sharing_text3, R.string.welldone_viewmodel_sharing_text4};
    /* renamed from: e */
    private final MutableLiveData<String> f21434e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<Boolean> f21435f = new MutableLiveData();
    /* renamed from: g */
    private final MutableLiveData<Boolean> f21436g = new MutableLiveData();
    /* renamed from: h */
    private MutableLiveData<String> f21437h = new MutableLiveData();
    /* renamed from: i */
    public final MutableLiveData<LockAfterVariants> f21438i = new MutableLiveData();
    /* renamed from: j */
    private final SessionRepository f21439j;
    /* renamed from: k */
    private final ManageWalletsRouter f21440k;
    /* renamed from: l */
    private final SelectCurrencyRouter f21441l;
    /* renamed from: m */
    private final PasscodeRepositoryType f21442m;
    /* renamed from: n */
    private final PreferenceRepositoryType f21443n;
    /* renamed from: o */
    private final PushNotificationsSettingsRouter f21444o;
    /* renamed from: p */
    Disposable f21445p;

    public SettingsViewModel(SessionRepository sessionRepository, ManageWalletsRouter manageWalletsRouter, SelectCurrencyRouter selectCurrencyRouter, PasscodeRepositoryType passcodeRepositoryType, PreferenceRepositoryType preferenceRepositoryType, PushNotificationsSettingsRouter pushNotificationsSettingsRouter) {
        this.f21439j = sessionRepository;
        this.f21440k = manageWalletsRouter;
        this.f21441l = selectCurrencyRouter;
        this.f21442m = passcodeRepositoryType;
        this.f21443n = preferenceRepositoryType;
        this.f21444o = pushNotificationsSettingsRouter;
    }

    private void fetchViewData() {
        fetchWalletName();
        PasscodeRepositoryType passcodeRepositoryType = this.f21442m;
        passcodeRepositoryType.getClass();
        Single subscribeOn = Single.fromCallable(new C1583e(passcodeRepositoryType)).subscribeOn(Schedulers.io());
        MutableLiveData mutableLiveData = this.f21435f;
        mutableLiveData.getClass();
        subscribeOn.subscribe(new C2492g(mutableLiveData));
        PreferenceRepositoryType preferenceRepositoryType = this.f21443n;
        preferenceRepositoryType.getClass();
        subscribeOn = Single.fromCallable(new C1584h(preferenceRepositoryType)).subscribeOn(Schedulers.io());
        mutableLiveData = this.f21437h;
        mutableLiveData.getClass();
        subscribeOn.subscribe(new C2490d(mutableLiveData));
        preferenceRepositoryType = this.f21443n;
        preferenceRepositoryType.getClass();
        subscribeOn = Single.fromCallable(new C1582a(preferenceRepositoryType)).subscribeOn(Schedulers.io());
        mutableLiveData = this.f21438i;
        mutableLiveData.getClass();
        subscribeOn.subscribe(new C2491f(mutableLiveData));
    }

    private void fetchWalletName() {
        this.f21445p = this.f21439j.getSessionOperator()
                .subscribeOn(Schedulers.io())
                .subscribe(session -> f21434e.postValue(session.wallet.name), throwable -> {});
    }

    private void openUrl(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public LiveData<String> currency() {
        return this.f21437h;
    }

    public LiveData<Boolean> hasPasscode() {
        return this.f21435f;
    }

    public void init() {
        fetchViewData();
    }

    public void openAbout(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    public void openBrowserSettings(Activity activity) {
        activity.startActivityForResult(new Intent(activity, BrowserSettingsActivity.class), ServiceErrorException.USER_NOT_AUTHENTICATED);
    }

    public void openDiscord(Context context) {
        openUrl(context, "https://discord.gg/ahPWeHk");
    }

    public void openFacebook(Context context) {
        openUrl(context, "https://www.facebook.com/trustwalletapp");
    }

    public void openHelpCenter(Context context) {
        openUrl(context, "https://help.trustwallet.com");
    }

    public void openPushNotificationsSettings(Context context) {
        if ("google-play".equalsIgnoreCase("s3")) {
            this.f21444o.open(context);
        }
    }

    public void openReddit(Context context) {
        openUrl(context, "https://www.reddit.com/r/trustapp");
    }

    public void openSelectCurrency(Context context) {
        this.f21441l.open(context);
    }

    public void openTelegram(Context context) {
        openUrl(context, "https://telegram.me/trust_announcements");
    }

    public void openTwitter(Context context) {
        Intent intent;
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent("android.intent.action.VIEW", Uri.parse("twitter://user?user_id=911011433147654144"));
            intent.addFlags(268435456);
        } catch (Exception unused) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/trustwalletapp"));
        }
        context.startActivity(intent);
    }

    public void openWallets(Context context) {
        this.f21440k.open(context, false);
    }

    public void openYoutube(Context context) {
        openUrl(context, "https://www.youtube.com/channel/UCrYnRK55nYUDd6flEZ2Kf6g/videos");
    }

    public void reinit() {
        fetchViewData();
    }

    public void setLockAfter(LockAfterVariants lockAfterVariants) {
        this.f21443n.setLockAfterTime(lockAfterVariants);
        this.f21438i.setValue(lockAfterVariants);
    }

    public void share(Context context) {
        int[] iArr = f21433d;
        String string = context.getString(iArr.length > 0 ? iArr[ThreadLocalRandom.current().nextInt(0, f21433d.length)] : R.string.welldone_viewmodel_sharing_text1);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", string);
        intent.putExtra("android.intent.extra.TEXT", "https://trustwallet.com");
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.Share)));
    }

    public LiveData<Boolean> shouldRequestPinSending() {
        return this.f21436g;
    }

    public void togglePasscode(Context context) {
        if (this.f21442m.has()) {
            new PasscodeRouter().openToDelete(context);
        } else {
            new PasscodeRouter().openToCreate(context);
        }
    }

    public void toggleRequestPinOnSending() {
        this.f21443n.setShouldRequestPinOnSending(!this.f21443n.getShouldRequestPinOnSending());
    }

    public LiveData<String> walletName() {
        return this.f21434e;
    }
}
