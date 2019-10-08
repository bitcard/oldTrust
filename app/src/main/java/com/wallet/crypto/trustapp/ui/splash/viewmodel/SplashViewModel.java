package com.wallet.crypto.trustapp.ui.splash.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.interact.CheckWalletExportInteract;
import com.wallet.crypto.trustapp.interact.migration.JsonStoreToRealmInteract;
import com.wallet.crypto.trustapp.interact.migration.PinToPasswordStoreInteract;
import com.wallet.crypto.trustapp.interact.migration.ToSlip44Interact;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.entity.Wallet;

public class SplashViewModel extends BaseViewModel {
    /* renamed from: d */
    private final WalletsRepository f21446d;
    /* renamed from: e */
    private final PasscodeRepositoryType f21447e;
    /* renamed from: f */
    private final CheckWalletExportInteract f21448f;
    /* renamed from: g */
    private final ToSlip44Interact f21449g;
    /* renamed from: h */
    private final PinToPasswordStoreInteract f21450h;
    /* renamed from: i */
    private final JsonStoreToRealmInteract f21451i;
    /* renamed from: j */
    private MutableLiveData<Wallet[]> f21452j = new MutableLiveData();

    public SplashViewModel(WalletsRepository walletsRepository, PasscodeRepositoryType passcodeRepositoryType, CheckWalletExportInteract checkWalletExportInteract, ToSlip44Interact toSlip44Interact, PinToPasswordStoreInteract pinToPasswordStoreInteract, JsonStoreToRealmInteract jsonStoreToRealmInteract) {
        this.f21446d = walletsRepository;
        this.f21447e = passcodeRepositoryType;
        this.f21448f = checkWalletExportInteract;
        this.f21449g = toSlip44Interact;
        this.f21450h = pinToPasswordStoreInteract;
        this.f21451i = jsonStoreToRealmInteract;
    }

    /* renamed from: a */
    public static /* synthetic */ void m195a(SplashViewModel splashViewModel) throws Exception {
        splashViewModel.f21450h.migrate();
        splashViewModel.f21451i.migrate();
    }

    private void onWallets(Wallet[] walletArr) {
        this.f21452j.postValue(walletArr);
    }

    public void checkWallets() {
        this.f19422c.add(this.f21448f.checkAllWallets().subscribe(()->{}, throwable -> {}));
    }

    public boolean hasPasscode() {
        return this.f21447e.has();
    }

    public void init() {
        this.f19422c.add(this.f21449g.migrate()
                .subscribeOn(Schedulers.io())
                .andThen(
                        Completable.fromAction(()->SplashViewModel.m195a(this)))
                .subscribeOn(Schedulers.io()).subscribe());
        this.f19422c.add(this.f21446d.fetch().onErrorResumeNext(Single.just(new Wallet[0])).subscribe(this::onWallets));
    }

    public LiveData<Wallet[]> wallets() {
        return this.f21452j;
    }
}
