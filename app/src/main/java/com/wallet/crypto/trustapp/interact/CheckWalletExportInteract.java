package com.wallet.crypto.trustapp.interact;

import android.text.TextUtils;

import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import trust.blockchain.entity.Wallet;

public class CheckWalletExportInteract {
    /* renamed from: a */
    private final WalletsRepository f16619a;
    /* renamed from: b */
    private final PasswordStore f16620b;

    @Inject
    public CheckWalletExportInteract(WalletsRepository walletsRepository, PasswordStore passwordStore) {
        this.f16619a = walletsRepository;
        this.f16620b = passwordStore;
    }

    public Completable checkAllWallets() {
        Observable filter = this.f16619a
                .fetch()
                .flatMapObservable(Observable::fromArray)
                .filter(wallet -> {
                    int i = wallet.type;
                    if (i != 0) {
                        if (i != 2) {
                            return false;
                        }
                    }
                    return true;
                });
        PasswordStore passwordStore = this.f16620b;
        passwordStore.getClass();
        return filter
                .map(o -> passwordStore.getPassword((Wallet) o))
                .map(o -> TextUtils.isEmpty((String) o))
                .onErrorResumeNext(C2205h.f19131a)
                .toList().toCompletable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    public Single<Boolean> checkWallet(Wallet wallet) {
        if (wallet.type == 1) {
            return Single.just(Boolean.TRUE);
        }
        return Single
                .fromCallable(new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        return f16620b.getPassword(wallet);
                    }
                })
                .map(o->Boolean.valueOf(!TextUtils.isEmpty((String) o)))
                .onErrorResumeNext(throwable->Single.just(Boolean.FALSE))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
