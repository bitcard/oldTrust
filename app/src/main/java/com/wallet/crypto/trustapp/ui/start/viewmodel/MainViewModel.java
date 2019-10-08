package com.wallet.crypto.trustapp.ui.start.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.interact.CheckWalletExportInteract;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.AddAssetRouter;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.Slip;
import trust.blockchain.entity.Transaction;

public class MainViewModel extends BaseViewModel implements OnSessionChangeListener {
    /* renamed from: d */
    private static String f21462d = "link.trustwallet.com";
    /* renamed from: e */
    private static String f21463e = "link.tw.co";
    /* renamed from: f */
    private final MutableLiveData<Session> f21464f = new MutableLiveData();
    /* renamed from: g */
    private final MutableLiveData<Pair<String, String>> f21465g = new MutableLiveData();
    /* renamed from: h */
    private final MutableLiveData<Session> f21466h = new MutableLiveData();
    /* renamed from: i */
    private final SessionRepository f21467i;
    /* renamed from: j */
    private final WalletsRepository f21468j;
    /* renamed from: k */
    private final TransactionsRepository f21469k;
    /* renamed from: l */
    private final AssetsController f21470l;
    /* renamed from: m */
    private final CheckWalletExportInteract f21471m;
    /* renamed from: n */
    private final ExternalBrowserRouter f21472n;
    /* renamed from: o */
    private final MutableLiveData<DeepUrl> f21473o = new MutableLiveData();
    /* renamed from: p */
    private final MutableLiveData<Integer> f21474p = new MutableLiveData();
    /* renamed from: q */
    private final MutableLiveData<Boolean> f21475q = new MutableLiveData();
    /* renamed from: r */
    private Handler f21476r = new Handler(Looper.getMainLooper());
    /* renamed from: s */
    private final Runnable f21477s = () -> MainViewModel.this.f19422c.add(
            Single.fromCallable(() -> MainViewModel.this.f21469k.fetchPendingTransactions(MainViewModel.this.f21467i.getSession()))
                    .doAfterTerminate(() -> MainViewModel.this.autoUpdate())
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(transactions -> MainViewModel.this.f21474p.postValue(Integer.valueOf(transactions.length)), throwable -> MainViewModel.this.f21474p.postValue(Integer.valueOf(0))));

    enum Action {
        OPEN_URL,
        ACTIVATE_COIN,
        ADD_TOKEN
    }

    public static class DeepUrl {
        /* renamed from: a */
        public String f17000a;
        /* renamed from: b */
        public Slip f17001b;

        public DeepUrl(String str, Slip slip) {
            this.f17000a = str;
            this.f17001b = slip;
        }
    }

    enum Scheme {
        TRUST,
        HTTPS,
        HTTP
    }

    public MainViewModel(SessionRepository sessionRepository, WalletsRepository walletsRepository, AssetsController assetsController, TransactionsRepository transactionsRepository, CheckWalletExportInteract checkWalletExportInteract, ExternalBrowserRouter externalBrowserRouter) {
        this.f21467i = sessionRepository;
        this.f21468j = walletsRepository;
        this.f21470l = assetsController;
        this.f21469k = transactionsRepository;
        this.f21471m = checkWalletExportInteract;
        this.f21472n = externalBrowserRouter;
        sessionRepository.addOnSessionChangeListener(this);
    }

    private void autoUpdate() {
        this.f21476r.removeCallbacks(this.f21477s);
        if (this.f21474p.hasActiveObservers()) {
            this.f21476r.postDelayed(this.f21477s, 1000);
        }
    }

    private void checkIsExported() {
        this.f21475q.setValue(Boolean.valueOf(true));
        Single observeOn = this.f21467i.getSessionOperator()
                .flatMap(session -> f21468j.isSkipBackup(session.wallet).map(aBoolean -> MainViewModel.lambda$null$6(session, aBoolean)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        MutableLiveData mutableLiveData = this.f21475q;
        mutableLiveData.getClass();
        this.f19422c.add(observeOn.subscribe(o -> mutableLiveData.postValue(o)));
    }

    private void checkWallet() {
        this.f19422c.add(this.f21467i.getSessionOperator()
                .flatMap(session -> f21471m.checkWallet(session.wallet))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aBoolean -> {
                            if (aBoolean)
                                checkIsExported();
                            else {
                                f19422c.add(f21467i.getSessionOperator().subscribe(session -> {
                                    f21466h.getClass();
                                    f21466h.postValue(session);
                                }));
                            }

                        },
                        throwable -> MainViewModel.lambda$checkWallet$5(throwable)));
    }

    private void handleDeepLink(Activity activity, Uri uri) {
        if (this.f21473o != null) {
            try {
                Slip find = Slip.find(Integer.valueOf(uri.getQueryParameter("coin_id")).intValue());
                Action enumR = null;
                switch (Scheme.valueOf(uri.getScheme().toUpperCase())) {
                    case TRUST:
                        enumR = Action.valueOf(uri.getHost().toUpperCase());
                        break;
                    case HTTPS:
                    case HTTP:
                        if (f21462d.equalsIgnoreCase(uri.getHost()) || f21463e.equalsIgnoreCase(uri.getHost())) {
                            enumR = Action.valueOf(uri.getLastPathSegment().toUpperCase());
                            break;
                        }
                    default:
                        break;
                }
                if (enumR != null) {
                    switch (enumR) {
                        case OPEN_URL:
                            this.f21473o.setValue(new DeepUrl(uri.getQueryParameter("url"), find));
                            break;
                        case ACTIVATE_COIN:
                            this.f21467i.getSessionOperator()
                                    .flatMapCompletable(session -> Completable.fromAction(() -> f21470l.setEnable(session, find.coinAsset(session.wallet.account(find), true), false)))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();
                            break;
                        case ADD_TOKEN:
                            new AddAssetRouter().open(activity, uri.getQueryParameter("token_id"), find);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    static /* synthetic */ void lambda$checkWallet$5(Throwable th) throws Exception {
    }

    static /* synthetic */ Boolean lambda$null$6(Session session, Boolean bool) throws Exception {
        return Boolean.valueOf(session.wallet.type == 3 ? bool.booleanValue() : true);
    }

    public LiveData<DeepUrl> deepLink() {
        return this.f21473o;
    }

    public void init(Activity activity, Uri uri) {
        checkWallet();
        handleDeepLink(activity, uri);
        this.f21476r.post(this.f21477s);
    }

    public LiveData<Boolean> isSkipBackup() {
        return this.f21475q;
    }

    protected void onCleared() {
        super.onCleared();
        pause();
    }

    protected void onError(Throwable th) {
        this.f19421b.hide();
        super.onError(th);
    }

    public void onSessionChanged(Session session) {
        this.f21464f.postValue(session);
    }

    public void openFacebook(Context context) {
        this.f21472n.open(context, Uri.parse("https://www.facebook.com/trustwalletapp"));
    }

    public void openTelegram(Context context) {
        this.f21472n.open(context, Uri.parse("https://telegram.me/trust_announcements"));
    }

    public void openTwitter(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("twitter://user?user_id=911011433147654144"));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception unused) {
            this.f21472n.open(context, Uri.parse("https://twitter.com/trustwalletapp"));
        }
    }

    public void pause() {
        this.f19422c.clear();
        this.f21476r.removeCallbacks(this.f21477s);
    }

    public LiveData<Integer> pendingCount() {
        return this.f21474p;
    }

    public void setIsSkipBackup(Activity activity) {
        this.f19422c.add(this.f21467i.getSessionOperator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new C2516i(activity)));
    }

    public LiveData<Session> walletNotExportable() {
        return this.f21466h;
    }
}
