package com.wallet.crypto.trustapp.ui.assets.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.interact.AddAssetInteract;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;

public class AddAssetViewModel extends BaseViewModel {
    /* renamed from: d */
    private final AddAssetInteract f21276d;
    /* renamed from: e */
    private final ApiService f21277e;
    /* renamed from: f */
    private final Session f21278f;
    /* renamed from: g */
    private final MutableLiveData<Boolean> f21279g = new MutableLiveData();
    /* renamed from: h */
    private final MutableLiveData<Account[]> f21280h = new MutableLiveData();
    /* renamed from: i */
    private final MutableLiveData<Asset> f21281i = new MutableLiveData();
    /* renamed from: j */
    public boolean f21282j = false;

    public AddAssetViewModel(SessionRepository sessionRepository, AddAssetInteract addAssetInteract, ApiService apiService) {
        this.f21276d = addAssetInteract;
        this.f21277e = apiService;
        this.f21278f = (Session) sessionRepository.getSessionOperator().blockingGet();
        this.f19422c.add(sessionRepository.getSessionOperator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(session -> f21280h.postValue(session.wallet.accounts), throwable -> {}));
    }

    private void onSaved() {
        this.f19421b.hide();
        this.f21279g.postValue(Boolean.valueOf(true));
    }

    public LiveData<Account[]> accounts() {
        return this.f21280h;
    }

    public LiveData<Asset> asset() {
        return this.f21281i;
    }

    public void findTokenInfo(String str, Slip slip) {
        if (slip.isValid(str)) {
            this.f19422c.add(Observable
                    .fromCallable(() -> f21277e.searchToken(str, f21278f.wallet, true))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter(assets -> assets.length > 0)
                    .subscribe(assets -> f21281i.postValue(assets[0])));
        }
    }

    public void init(Asset asset) {
        if (asset != null) {
            this.f21282j = true;
        }
    }

    public LiveData<Boolean> result() {
        return this.f21279g;
    }

    public void save(String str, String str2, String str3, int i, Slip slip) {
        this.f19422c.add(this.f21276d.add(str, str2, str3, i, slip)
                .subscribe(() -> onSaved(), throwable -> onError(throwable)));
    }
}
