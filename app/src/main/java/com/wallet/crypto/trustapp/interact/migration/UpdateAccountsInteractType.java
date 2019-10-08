package com.wallet.crypto.trustapp.interact.migration;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Wallet;

public class UpdateAccountsInteractType implements UpdateAccountsInteract {
    /* renamed from: a */
    private final WalletsRepository f19146a;
    /* renamed from: b */
    private final AddDefaultAssetsInteract f19147b;
    /* renamed from: c */
    private final SharedPreferences f19148c;

    @Inject
    public UpdateAccountsInteractType(Context context, WalletsRepository walletsRepository, AddDefaultAssetsInteract addDefaultAssetsInteract) {
        this.f19148c = PreferenceManager.getDefaultSharedPreferences(context);
        this.f19146a = walletsRepository;
        this.f19147b = addDefaultAssetsInteract;
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m60a(UpdateAccountsInteractType updateAccountsInteractType, Wallet wallet) throws Exception {
        if (updateAccountsInteractType.f19148c.getBoolean("should_reimport-1", true)) {
            return true;
        }
        return updateAccountsInteractType.shouldUpdateAccounts(wallet);
    }

    /* renamed from: b */
    public static /* synthetic */ SingleSource m61b(UpdateAccountsInteractType updateAccountsInteractType, Wallet wallet) throws Exception {
        Single reimportWallet = updateAccountsInteractType.f19146a.reimportWallet(wallet);
        AddDefaultAssetsInteract addDefaultAssetsInteract = updateAccountsInteractType.f19147b;
        addDefaultAssetsInteract.getClass();
        return reimportWallet.flatMap(o -> addDefaultAssetsInteract.add((Wallet) o));
    }

    private boolean shouldUpdateAccounts(Wallet wallet) {
        if (wallet.type == 3) {
            Account[] accountArr = wallet.accounts;
            if (accountArr == null || accountArr.length != Slip.available().length) {
                return true;
            }
        }
        return false;
    }

    public Completable update() {
        return this.f19146a.fetch()
                .flatMapObservable(wallets -> Observable.fromArray(wallets))
                .filter(wallet -> UpdateAccountsInteractType.m60a(this, wallet))
                .flatMapSingle(wallet -> m61b(this, wallet))
                .flatMapCompletable(o -> Completable.complete())
                .doOnComplete(()->f19148c.edit().putBoolean("should_reimport-1", false).apply());
    }
}
