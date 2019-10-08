package com.wallet.crypto.trustapp.ui.importwallet.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.WalletAlreadyExistsException;
import com.wallet.crypto.trustapp.interact.PushNotificationsInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportKeystoreListener;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportMnemonicListener;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportPrivateKeyListener;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportWatchWalletListener;
import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import trust.blockchain.Slip;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Wallet;
import trust.blockchain.mnemonic.SimpleExporter;
import trust.blockchain.util.ExtensionsKt;
import wallet.core.jni.PrivateKey;

public class ImportWalletViewModel extends BaseViewModel implements OnImportKeystoreListener, OnImportPrivateKeyListener, OnImportMnemonicListener, OnImportWatchWalletListener {
    /* renamed from: d */
    private final WalletsRepository f21412d;
    /* renamed from: e */
    private final AddDefaultAssetsInteract f21413e;
    /* renamed from: f */
    private final PushNotificationsInteract f21414f;
    /* renamed from: g */
    private final AssetsController f21415g;
    /* renamed from: h */
    private final PreferenceRepositoryType f21416h;
    /* renamed from: i */
    private final MutableLiveData<Integer> f21417i = new MutableLiveData();
    /* renamed from: j */
    private final MutableLiveData<Wallet> f21418j = new MutableLiveData();
    /* renamed from: k */
    private Slip f21419k;

    public ImportWalletViewModel(WalletsRepository walletsRepository, AssetsController assetsController, AddDefaultAssetsInteract addDefaultAssetsInteract, PushNotificationsInteract pushNotificationsInteract, PreferenceRepositoryType preferenceRepositoryType) {
        this.f21412d = walletsRepository;
        this.f21415g = assetsController;
        this.f21413e = addDefaultAssetsInteract;
        this.f21414f = pushNotificationsInteract;
        this.f21416h = preferenceRepositoryType;
    }

    /* renamed from: c */
    public static /* synthetic */ void m185c(ImportWalletViewModel importWalletViewModel, Wallet wallet) throws Exception {
        Completable.fromAction(() -> importWalletViewModel.f21415g.enableNoneEmpty(wallet))
                .subscribeOn(Schedulers.io())
                .subscribe();
        importWalletViewModel.f21416h.setCoinVisibilityUpdated(wallet.id);
    }

    private Single<String> convertPrivateKeyToStore(String str) {
        return Single.fromCallable(() -> new ObjectMapper().writeValueAsString(org.web3j.crypto.Wallet.create("", ECKeyPair.create(new BigInteger(str, 16)), SimpleExporter.N, 1)));
    }

    private void importWallet(Single<Wallet> single) {
        this.f19421b.show();
        AddDefaultAssetsInteract addDefaultAssetsInteract = this.f21413e;
        addDefaultAssetsInteract.getClass();
        Single flatMap = single.flatMap(
                wallet -> addDefaultAssetsInteract.add(wallet))
                .flatMap(wallet -> updateNotifications()
                        .andThen(Completable.fromAction(() -> ImportWalletViewModel.m185c(this, wallet)))
                        .andThen(this.f21412d.setIsSkipBackup(wallet, true))
                        .andThen(Single.just(wallet)));
        ProgressLiveData progressLiveData = this.f19421b;
        progressLiveData.getClass();
        flatMap = flatMap.doAfterTerminate(new C2447j(progressLiveData)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        MutableLiveData mutableLiveData = this.f21418j;
        mutableLiveData.getClass();
        this.f19422c.add(flatMap.subscribe(new C2439a(mutableLiveData), new C2448k(this)));
    }

    private Completable updateNotifications() {
        return this.f21414f.update().onErrorResumeNext(C2440b.f19790a);
    }

    public Slip coin() {
        return this.f21419k;
    }

    public String getScreenTitle(Resources resources) {
        Slip slip = this.f21419k;
        String string = slip == null ? resources.getString(R.string.MultiCoinWallet) : slip.coinName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(resources.getString(R.string.importWallet_import_button_title));
        stringBuilder.append(" ");
        stringBuilder.append(string);
        return stringBuilder.toString();
    }

    public String getWalletName(Context context) {
        Integer num = (Integer) this.f21417i.getValue();
        int intValue = num != null ? num.intValue() : 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getString(isMultiCoin() ? R.string.MultiCoinWallet : R.string.Wallet));
        stringBuilder.append(" ");
        stringBuilder.append(intValue);
        return stringBuilder.toString();
    }

    public void initWithCoin(Slip slip) {
        this.f21419k = slip;
        ListCompositeDisposable listCompositeDisposable = this.f19422c;
        Single nextWalletNumber = this.f21412d.getNextWalletNumber(slip == null ? 3 : -1);
        MutableLiveData mutableLiveData = this.f21417i;
        mutableLiveData.getClass();
        listCompositeDisposable.add(
                nextWalletNumber.subscribe(
                        integer -> mutableLiveData.postValue(integer),
                        throwable -> f21417i.postValue(Integer.valueOf(1))));
    }

    public void initWithName(String str) {
        Slip valueOf = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                valueOf = Slip.valueOf(str);
            } catch (Exception unused) {
            }
        }
        initWithCoin(valueOf);
    }

    public boolean isECPointValid(String str, Slip slip) {
        try {
            return PrivateKey.isValid(ExtensionsKt.toHexByteArray(Numeric.cleanHexPrefix(str)), slip.coinType().curve());
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isMultiCoin() {
        return this.f21419k == null;
    }

    public void onError(Throwable th) {
        if (th instanceof CipherException) {
            this.f19420a.postValue(new ErrorEnvelope(10, th.getMessage()));
            return;
        }
        if (!((th.getCause() instanceof ServiceErrorException) || (th instanceof ServiceErrorException))) {
            if (!(th instanceof WalletAlreadyExistsException)) {
                if (!(th.getCause() == null || TextUtils.isEmpty(th.getCause().getMessage()))) {
                    th = th.getCause();
                }
                this.f19420a.postValue(new ErrorEnvelope(1, TextUtils.isEmpty(th.getLocalizedMessage()) ? th.getMessage() : th.getLocalizedMessage()));
                return;
            }
        }
        if ((th instanceof WalletAlreadyExistsException) || ((ServiceErrorException) th).code == 3 || ((ServiceErrorException) th.getCause()).code == 3) {
            this.f19420a.postValue(new ErrorEnvelope(3, null));
        }
    }

    public int onKeystore(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return R.string.validation_fieldRequired_message;
        }
        importWallet(this.f21412d.addWallet(0, str, str3, str2, new Slip[]{this.f21419k}));
        return -1;
    }

    public int onMnemonic(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return R.string.validation_fieldRequired_message;
        }
        Slip[] available = this.f21419k == null ? Slip.available() : new Slip[]{this.f21419k};
        importWallet(this.f21412d.addWallet(available.length == 1 ? 2 : 3, str, str2, "", available));
        return -1;
    }

    public int onPrivateKey(String str, String str2) {
        if (!TextUtils.isEmpty(str) && Numeric.cleanHexPrefix(str).length() == 64) {
            if (isECPointValid(str, this.f21419k)) {
                importWallet(convertPrivateKeyToStore(Numeric.cleanHexPrefix(str))
                        .flatMap(s -> f21412d.addWallet(0, s, str2, "", new Slip[]{this.f21419k})));
                return -1;
            }
        }
        return R.string.importWallet_invalidPrivateKey_message;
    }

    public int onWatchWallet(String str, String str2) {
        if (!this.f21419k.isValid(str.trim())) {
            return R.string.send_error_invalidAddress;
        }
        String str3 = str;
        String str4 = str2;
        importWallet(this.f21412d.addWallet(1, str3, str4, "", new Slip[]{this.f21419k}));
        return -1;
    }

    public LiveData<Wallet> wallet() {
        return this.f21418j;
    }

    public LiveData<Integer> walletName() {
        return this.f21417i;
    }
}
