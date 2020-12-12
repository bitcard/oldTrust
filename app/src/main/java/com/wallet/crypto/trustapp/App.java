// completed
package com.wallet.crypto.trustapp;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.wallet.crypto.trustapp.di.AppComponent.Builder;
import com.wallet.crypto.trustapp.di.DaggerAppComponent;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.service.tick.TickCoordinatorService;
import com.wallet.crypto.trustapp.util.LockLifecycleListener;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import javax.inject.Inject;

public class App extends Application implements HasActivityInjector {
    @Inject
    /* renamed from: a */
    DispatchingAndroidInjector<Activity> f18843a;
//    @Inject
    /* renamed from: b */
    TransactionsRepository f18844b;
    @Inject
    /* renamed from: c */
    PreferenceRepositoryType f18845c;
    @Inject
    /* renamed from: d */
    PasscodeRepositoryType f18846d;
//    @Inject
    /* renamed from: e */
    TickCoordinatorService f18847e;
    /* renamed from: f */
    private final LockLifecycleListener f18848f = new LockLifecycleListener();
    /* renamed from: g */
    private Uri f18849g;
    /* renamed from: h */
    private boolean f18850h;

    static {
        System.loadLibrary("TrustWalletCore");
    }

    /* renamed from: a */
    public static void m58a(App app, LifecycleOwner lifecycleOwner, Event event) {
        if (event == Event.ON_RESUME) {
            app.f18847e.start();
        } else if (event == Event.ON_PAUSE) {
            app.f18847e.stop();
        }
    }

    public AndroidInjector<Activity> activityInjector() {
        return this.f18843a;
    }

    public boolean isScreenLocked() {
        long j;
        long startBackgroundTime = this.f18848f.getStartBackgroundTime();
        switch (this.f18845c.getLockAfterTime()) {
            case ONE_MINUTE:
                j = 60000;
                break;
            case FIVE_MINUTES:
                j = 300000;
                break;
            case ONE_HOUR:
                j = 3600000;
                break;
            case FIVE_HOUR:
                j = 18000000;
                break;
            default:
                j = 0;
                break;
        }
        return startBackgroundTime > 0 && (System.currentTimeMillis() - startBackgroundTime) - j > 0 && this.f18846d.has();
    }

    public boolean isShowedSkipBackup() {
        return this.f18850h;
    }

    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.d("TRUST_ERROR_HANDLER", "TrustWallet handled error", (Throwable) throwable);
        });
        Realm.init(this);
        Builder builder = DaggerAppComponent.builder();
        builder.application(this);
        builder.build().inject(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this.f18848f);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new C2801b(this));
    }

    public Uri popDeepLink() {
        Uri uri = this.f18849g;
        this.f18849g = null;
        return uri;
    }

    public void setDeepLink(Uri uri) {
        this.f18849g = uri;
    }

    public void setIsShowedSkipBackup(boolean z) {
        this.f18850h = z;
    }
}
