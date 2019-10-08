package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import android.app.Activity;
import android.util.SparseArray;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.interact.PushNotificationsInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.MainScreenRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.wallets.entity.WalletGroupTitleViewData;
import com.wallet.crypto.trustapp.ui.wallets.entity.WalletViewData;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Value;
import trust.blockchain.entity.Wallet;

public class WalletsViewModel extends BaseViewModel {
    /* renamed from: d */
    private final SessionRepository f21589d;
    /* renamed from: e */
    private final WalletsRepository f21590e;
    /* renamed from: f */
    private final AssetsController f21591f;
    /* renamed from: g */
    private final PreferenceRepositoryType f21592g;
    /* renamed from: h */
    private final PushNotificationsInteract f21593h;
    /* renamed from: i */
    private final MutableLiveData<ViewData[]> f21594i = new MutableLiveData();
    /* renamed from: j */
    private final MutableLiveData<Throwable> f21595j = new MutableLiveData();
    /* renamed from: k */
    private final String f21596k;

    public WalletsViewModel(SessionRepository sessionRepository, WalletsRepository walletsRepository, AssetsController assetsController, PreferenceRepositoryType preferenceRepositoryType, PushNotificationsInteract pushNotificationsInteract) {
        this.f21589d = sessionRepository;
        this.f21590e = walletsRepository;
        this.f21591f = assetsController;
        this.f21592g = preferenceRepositoryType;
        this.f21593h = pushNotificationsInteract;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        decimalFormat.setMinimumFractionDigits(2);
        this.f21596k = decimalFormat.format(0.0d);
    }

    private ViewData[] convert(Wallet[] r11, Session r12) {
        SparseArray<List<ViewData>> r0 = new SparseArray<>();
        boolean r1 = r11.length == 1;
        for (Wallet wallet : r11) {
            WalletViewData r7 = convert(r12.wallet.id, r1, wallet);
            int r6 = (wallet.type == 1 || wallet.type == 3) ? wallet.type : 0;
            List<ViewData> r8 = r0.get(r6);
            if (r8 == null) {
                r8 = new ArrayList();
                r0.append(r6, r8);
            }
            r8.add(r7);
        }

        List<ViewData> temp = new ArrayList<>();
        for (int i = 0; i < r0.size(); i++) {
            int key = r0.keyAt(i);
            temp.add(new WalletGroupTitleViewData(key));
            temp.addAll(r0.get(key));
        }
        return temp.toArray(new ViewData[0]);
    }

    static /* synthetic */ void lambda$setDefaultWallet$9(Activity activity) throws Exception {
        ((App) activity.getApplication()).setIsShowedSkipBackup(false);
        new MainScreenRouter().open(activity.getApplicationContext(), true);
        activity.finish();
    }

    static /* synthetic */ boolean lambda$updateBalances$3(ViewData viewData) throws Exception {
        return viewData.getViewType() == 2001 && ((WalletViewData) viewData).f20116b != 3;
    }

    static /* synthetic */ ViewData[] lambda$updateBalances$8(ViewData[] viewDataArr, List<WalletViewData> list) throws Exception {
        ViewData[] viewDataArr2 = new ViewData[viewDataArr.length];
        int i = 0;
        System.arraycopy(viewDataArr, 0, viewDataArr2, 0, viewDataArr.length);
        while (i < viewDataArr2.length) {
            if (viewDataArr2[i].getViewType() == 2001) {
                WalletViewData walletViewData = (WalletViewData) viewDataArr2[i];
                for (WalletViewData walletViewData2 : list) {
                    if (walletViewData2.f20115a.equals(walletViewData.f20115a)) {
                        viewDataArr2[i] = walletViewData2;
                        break;
                    }
                }
            }
            i++;
        }
        return viewDataArr2;
    }

    private void loadBalances(ViewData[] viewDataArr, boolean z) {
        if (z) {
            Single observeOn = updateBalances(viewDataArr, true).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            MutableLiveData mutableLiveData = this.f21594i;
            mutableLiveData.getClass();
            this.f19422c.add(observeOn.subscribe(v -> mutableLiveData.postValue(v)));
        }
    }

    private Single<ViewData[]> updateBalances(ViewData[] viewDataArr, boolean z) {
        return Observable.fromArray(viewDataArr)
                .filter(viewData -> lambda$updateBalances$3(viewData))
                .map(viewData -> (WalletViewData)viewData)
                .flatMapSingle(walletViewData -> Observable.fromCallable(() -> {
                                                                Account wallet = walletViewData.f20121g.accounts[0];
                                                                return f21591f.updateBalances(new Session(walletViewData.f20121g), new Asset[]{wallet.coin.coinAsset(wallet, true)}, z);
                                                            })
                                                            .map(assets -> zipWalletAsset(walletViewData, assets[0]))
                                                            .lastElement()
                                                            .toSingle()
                                                            .onErrorResumeNext(Single.just(walletViewData.clone())))
                .toList()
                .map(list -> WalletsViewModel.lambda$updateBalances$8(viewDataArr, list));
    }

    private WalletViewData zipWalletAsset(WalletViewData walletViewData, Asset asset) {
        walletViewData = walletViewData.clone();
        Value value = asset.balance;
        if (value != null) {
            walletViewData.f20120f = new SubunitValue(value, asset.contract.unit).mediumFormat(this.f21596k, -1);
        }
        return walletViewData;
    }

    public void deleteWallet(Wallet wallet) {
        Completable observeOn = this.f21590e.deleteWallet(wallet)
                .andThen(this.f21590e.fetch())
                .flatMapCompletable(wallets -> WalletsViewModel.m220a(this, wallets))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        C2599u c2599u = new C2599u(this);
        MutableLiveData mutableLiveData = this.f21595j;
        mutableLiveData.getClass();
        this.f19422c.add(observeOn.subscribe(c2599u, new C2582c(mutableLiveData)));
    }

    public LiveData<Throwable> deleteWalletError() {
        return this.f21595j;
    }

    public void fetch(boolean z) {
        this.f19421b.show();
        Single doOnSuccess = Single.zip(this.f21590e.fetch(), this.f21589d.getSessionOperator(), ((wallets, session) -> convert(wallets, session)))
                .flatMap(viewData -> updateBalances(viewData, false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(viewData -> loadBalances(viewData, z));
        ProgressLiveData progressLiveData = this.f19421b;
        progressLiveData.getClass();
        doOnSuccess = doOnSuccess.doAfterTerminate(new C2583d(progressLiveData));
        MutableLiveData mutableLiveData = this.f21594i;
        mutableLiveData.getClass();
        this.f19422c.add(doOnSuccess.subscribe(v -> mutableLiveData.postValue(v), throwable -> f21594i.postValue(new WalletViewData[0])));
    }

    public void setDefaultWallet(Activity activity, Wallet wallet) {
        this.f19422c.add(this.f21589d.setWallet(wallet).subscribe(() -> WalletsViewModel.lambda$setDefaultWallet$9(activity), throwable -> onError(throwable)));
    }

    public LiveData<ViewData[]> wallets() {
        return this.f21594i;
    }

    private WalletViewData convert(String str, boolean z, Wallet wallet) {
        WalletViewData walletViewData = new WalletViewData();
        String str2 = wallet.id;
        walletViewData.f20115a = str2;
        walletViewData.f20116b = wallet.type;
        walletViewData.f20119e = str.equals(str2);
        walletViewData.f20118d = z;
        walletViewData.f20117c = wallet.name;
        walletViewData.f20121g = wallet;
        walletViewData.f20120f = "";
        return walletViewData;
    }

    public static CompletableSource m220a(WalletsViewModel r0, Wallet[] r1) throws Exception {
        if (r1.length == 0) {
            r0.f21592g.clear();
            return r0.f21593h.unregister();
        } else {
            return Completable.complete();
        }
    }
}
