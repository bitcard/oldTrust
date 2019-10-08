package com.wallet.crypto.trustapp.ui.wallets.viewmodel;

import android.app.Activity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.OpenExtendedKeyRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.entity.Wallet;

public class WalletInfoViewModel extends BaseViewModel {
    /* renamed from: d */
    private final MutableLiveData<Wallet> f21581d = new MutableLiveData();
    /* renamed from: e */
    private final MutableLiveData<String[]> f21582e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<String> f21583f = new MutableLiveData();
    /* renamed from: g */
    private final MutableLiveData<String> f21584g = new MutableLiveData();
    /* renamed from: h */
    private final WalletsRepository f21585h;
    /* renamed from: i */
    private final PasscodeRepositoryType f21586i;
    /* renamed from: j */
    private final PasscodeRouter f21587j;
    /* renamed from: k */
    private final OpenExtendedKeyRouter f21588k;

    public WalletInfoViewModel(WalletsRepository walletsRepository, PasscodeRepositoryType passcodeRepositoryType, PasscodeRouter passcodeRouter, OpenExtendedKeyRouter openExtendedKeyRouter) {
        this.f21585h = walletsRepository;
        this.f21586i = passcodeRepositoryType;
        this.f21587j = passcodeRouter;
        this.f21588k = openExtendedKeyRouter;
    }

    public void delete(Activity activity) {
        ListCompositeDisposable listCompositeDisposable = this.f19422c;
        Completable deleteWallet = this.f21585h.deleteWallet((Wallet) this.f21581d.getValue());
        activity.getClass();
        listCompositeDisposable.add(deleteWallet.subscribe(new C2581b(activity), throwable -> f19420a.postValue(new ErrorEnvelope( R.string.deleteWallet_errorDeletingWallet_message))));
    }

    public void exportExtendedKey(Activity activity) {
        Wallet wallet = (Wallet) this.f21581d.getValue();
        if (wallet != null) {
            this.f21588k.open(activity, wallet);
        }
    }

    public void exportKeystore(String str) {
        Wallet wallet = (Wallet) this.f21581d.getValue();
        ListCompositeDisposable listCompositeDisposable = this.f19422c;
        Single observeOn = this.f21585h.exportKeyStore(wallet, str, wallet.accounts[0]).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        MutableLiveData mutableLiveData = this.f21583f;
        mutableLiveData.getClass();
        listCompositeDisposable.add(observeOn.subscribe(new C2584e(mutableLiveData), throwable -> onError((Throwable) throwable)));
    }

    public void exportPhrase(Activity activity, String str) {
        if (!this.f21586i.has() || "true".equals(str)) {
            this.f19422c.add(this.f21585h.exportPhrase((Wallet) this.f21581d.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> f21582e.postValue(s.split(" ")), throwable -> onError((Throwable) throwable)));
        } else {
            this.f21587j.openToSendConfirm(activity, "phrase");
        }
    }

    public void exportPrivateKey(Activity activity, String str) {
        if (!this.f21586i.has() || "true".equals(str)) {
            Wallet wallet = (Wallet) this.f21581d.getValue();
            ListCompositeDisposable listCompositeDisposable = this.f19422c;
            Single observeOn = this.f21585h.exportPrivateKey(wallet, wallet.accounts[0]).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            MutableLiveData mutableLiveData = this.f21584g;
            mutableLiveData.getClass();
            listCompositeDisposable.add(observeOn.subscribe(new C2584e(mutableLiveData), throwable -> onError((Throwable) throwable)));
            return;
        }
        this.f21587j.openToSendConfirm(activity, "private_key");
    }

    public void init(Wallet wallet) {
        Single observeOn = this.f21585h.findWalletById(wallet.id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        MutableLiveData mutableLiveData = this.f21581d;
        mutableLiveData.getClass();
        this.f19422c.add(observeOn.subscribe(new C2580a(mutableLiveData), throwable -> onError((Throwable) throwable)));
    }

    public LiveData<String> keystore() {
        return this.f21583f;
    }

    public LiveData<String[]> phrase() {
        return this.f21582e;
    }

    public LiveData<String> privateKey() {
        return this.f21584g;
    }

    public void setName(String str) {
        this.f19422c.add(this.f21585h.setName((Wallet) this.f21581d.getValue(), str).subscribe(()->{}, throwable -> {}));
    }

    public LiveData<Wallet> wallet() {
        return this.f21581d;
    }
}
