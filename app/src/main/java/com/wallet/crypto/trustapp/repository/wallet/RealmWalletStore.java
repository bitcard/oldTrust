package com.wallet.crypto.trustapp.repository.wallet;

import com.wallet.crypto.trustapp.repository.entity.RealmAccount;
import com.wallet.crypto.trustapp.repository.entity.RealmWallet;
import com.wallet.crypto.trustapp.service.RealmManager;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import trust.blockchain.AccountIndicesService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Wallet;
import trust.blockchain.entity.WalletDescriptor;

public class RealmWalletStore implements WalletStore {
    /* renamed from: a */
    private final RealmManager f19304a;
    /* renamed from: b */
    private final AccountIndicesService f19305b;

    public RealmWalletStore(RealmManager realmManager, AccountIndicesService accountIndicesService) {
        this.f19304a = realmManager;
        this.f19305b = accountIndicesService;
    }

    private Wallet[] convert(RealmResults<RealmWallet> realmResults) {
        int size = realmResults.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            RealmWallet realmWallet = (RealmWallet) realmResults.get(i);
            if (realmWallet != null) {
                arrayList.add(convert(realmWallet));
            }
        }
        return (Wallet[]) arrayList.toArray(new Wallet[0]);
    }

    private String createId(int i) {
        int i2;
        if (i == 3) {
            i = 1;
            i2 = 2;
        } else {
            i2 = i;
            i = 0;
        }
        String str = "%s-%s-%s";
        Object[] objArr = new Object[3];
        objArr[0] = i != 0 ? "m" : "";
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Long.valueOf(System.currentTimeMillis());
        return String.format(str, objArr);
    }

    public Single<Wallet> add(int i, String str, byte[] bArr, Account[] accountArr) {
        return Single.fromCallable(() -> ((Wallet) f19304a.getWallets(new C2299u(RealmWalletStore.this, i, bArr, str, accountArr))));
    }

    public Completable delete(Wallet wallet) {
        return Completable.fromAction(()->f19304a.getWallets(new C2275Q(RealmWalletStore.this, wallet)));
    }

    public Single<Wallet[]> fetch() {
        return Single.fromCallable(()->((Wallet[]) f19304a.getWallets(realm -> convert(realm.where(RealmWallet.class).findAll()))))
                .flatMapObservable(wallets -> Observable.fromArray(wallets))
                .map(wallet -> {
                    f19305b.get(wallet);
                    return wallet;
                })
                .toList()
                .map(C2266F.f19284a);
    }

    public Single<Wallet> findMainWallet() {
        return Single.fromCallable(()->((Wallet) f19304a.getWallets(realm -> RealmWalletStore.m102c(this, realm))))
                .map(wallet -> {
                    f19305b.get(wallet);
                    return wallet;
                });
    }

    public Single<Wallet> findWalletById(String str) {
        return Single.fromCallable(()->((Wallet) f19304a.getWallets(realm -> RealmWalletStore.m99b(this, str, realm))))
                .map(wallet -> {
                    f19305b.get(wallet);
                    return wallet;
                });
    }

    public Wallet[] getAllWallets() {
        Wallet[] walletArr = (Wallet[]) this.f19304a.getWallets(realm -> convert(realm.where(RealmWallet.class).findAll()));
        int i = 0;
        while (walletArr != null && i < walletArr.length) {
            this.f19305b.get(walletArr[i]);
            i++;
        }
        return walletArr;
    }

    public Wallet getWalletById(String str) {
        return (Wallet) this.f19304a.getWallets(new C2276S(this, str));
    }

    public Single<byte[]> getWalletData(Wallet wallet) {
        return Single.fromCallable(()->((byte[]) f19304a.getWallets(realm -> {
            RealmWallet realmWallet = (RealmWallet) realm.where(RealmWallet.class).equalTo("id", wallet.id).findFirst();
            if (realmWallet != null) {
                return realmWallet.getData();
            }
            return new byte[0];
        })));
    }

    public Single<Boolean> isSkipBackup(Wallet wallet) {
        return Single.fromCallable(()->((Boolean) f19304a.getWallets(realm -> {
            RealmWallet realmWallet = (RealmWallet) realm.where(RealmWallet.class).equalTo("id", wallet.id).findFirst();
            if (realmWallet != null) {
                return Boolean.valueOf(realmWallet.getSkipBackup());
            }
            return Boolean.valueOf(false);
        })));
    }

    public Completable setIsSkipBackup(Wallet wallet, boolean z) {
        return Completable.fromAction(()->f19304a.getWallets(realm -> {
            RealmWallet realmWallet = realm.where(RealmWallet.class).equalTo("id", wallet.id).findFirst();
            if (realmWallet == null) {
                return null;
            }
            try {
                realm.beginTransaction();
                realmWallet.setSkipBackup(z);
                realm.commitTransaction();
            } catch (Exception unused) {
                realm.cancelTransaction();
            }
            return null;
        }));
    }

    public Completable setName(Wallet wallet, String str) {
        return Completable.fromAction(()->f19304a.getWallets(realm -> {
            RealmWallet realmWallet = (RealmWallet) realm.where(RealmWallet.class).equalTo("id", wallet.id).findFirst();
            if (realmWallet == null) {
                return null;
            }
            try {
                realm.beginTransaction();
                realmWallet.setName(str);
                realm.commitTransaction();
            } catch (Exception unused) {
                realm.cancelTransaction();
            }
            return null;
        }));
    }

    public Single<Wallet> update(Wallet wallet, WalletDescriptor walletDescriptor) {
        return Single.fromCallable(()->((Wallet) f19304a.getWallets(realm -> {
            try {
                RealmWallet realmWallet = (RealmWallet) realm.where(RealmWallet.class).equalTo("id", wallet.id).findFirst();
                if (realmWallet == null) {
                    return wallet;
                }
                realm.beginTransaction();
                realmWallet.getAccounts().deleteAllFromRealm();
                for (Account account : walletDescriptor.accounts) {
                    RealmAccount realmAccount = (RealmAccount) realm.createObject(RealmAccount.class);
                    realmAccount.setAddress(account.address().toString());
                    realmAccount.setCoinType(account.coin.coinType().value());
                    realmAccount.setExtendedPublicKey(account.extendedPublicKey);
                    realmWallet.getAccounts().add(realmAccount);
                }
                realm.commitTransaction();
                return new Wallet(wallet.id, wallet.type, wallet.name, walletDescriptor.accounts);
            } catch (Exception e) {
                if (realm != null) {
                    realm.cancelTransaction();
                }
                throw new IllegalStateException("Can't save to store", e);
            }
        })));
    }

    /* renamed from: b */
    public static /* synthetic */ Wallet m99b(RealmWalletStore realmWalletStore, String str, Realm realm) {
        RealmWallet realmWallet = (RealmWallet) realm.where(RealmWallet.class).equalTo("id", str).findFirst();
        return realmWallet != null ? realmWalletStore.convert(realmWallet) : null;
    }

    private Wallet convert(RealmWallet realmWallet) {
        return new Wallet(realmWallet.getId(), realmWallet.getType(), realmWallet.getName(), convert(realmWallet.getAccounts()));
    }

    /* renamed from: a */
    public static /* synthetic */ Wallet m89a(RealmWalletStore realmWalletStore, int i, byte[] bArr, String str, Account[] accountArr, Realm realm) {
        try {
            realm.beginTransaction();
            RealmWallet realmWallet = (RealmWallet) realm.createObject(RealmWallet.class, realmWalletStore.createId(i));
            realmWallet.setData(bArr);
            realmWallet.setName(str);
            realmWallet.setType(i);
            for (Account account : accountArr) {
                RealmAccount realmAccount = (RealmAccount) realm.createObject(RealmAccount.class);
                realmAccount.setExtendedPublicKey(account.extendedPublicKey);
                realmAccount.setAddress(account.address().data());
                realmAccount.setCoinType(account.coin.coinType().value());
                realmWallet.getAccounts().add(realmAccount);
            }
            realm.commitTransaction();
            return new Wallet(realmWallet.getId(), i, str, accountArr);
        } catch (Exception e) {
            if (realm != null) {
                realm.cancelTransaction();
            }
            throw new IllegalStateException("Can't save to store", e);
        }
    }

    private Account[] convert(RealmList<RealmAccount> realmList) {
        int size = realmList.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            RealmAccount realmAccount = (RealmAccount) realmList.get(i);
            if (realmAccount != null) {
                arrayList.add(new Account(Slip.find(realmAccount.getCoinType()), realmAccount.getExtendedPublicKey(), realmAccount.getAddress()));
            }
        }
        return (Account[]) arrayList.toArray(new Account[0]);
    }

    public static /* synthetic */ java.lang.Object m87a(com.wallet.crypto.trustapp.repository.wallet.RealmWalletStore r3, trust.blockchain.entity.Wallet r4, io.realm.Realm r5) {
        RealmWallet r0 = r5.where(RealmWallet.class).equalTo("id", r4.id).findFirst();
        if (r0 == null)
            return null;
        try {
            r5.beginTransaction();
            r0.deleteFromRealm();
            r5.commitTransaction();
            r3.f19304a.deleteCache(new com.wallet.crypto.trustapp.entity.Session(r4));
        } catch (Exception e) {
            r5.cancelTransaction();
        }
        return null;
    }

    public static /* synthetic */ trust.blockchain.entity.Wallet m102c(com.wallet.crypto.trustapp.repository.wallet.RealmWalletStore r2, io.realm.Realm r3) {
        RealmWallet rwR3 = r3.where(RealmWallet.class)
                                .equalTo("isMainWallet", true)
                                .or()
                                .equalTo("type", 3)
                                .findFirst();
        if (rwR3 == null)
            return null;
        return r2.convert(rwR3);
    }

    public static Wallet m91a(com.wallet.crypto.trustapp.repository.wallet.RealmWalletStore r1, java.lang.String r2, io.realm.Realm r3) {
        RealmWallet rwR2 = r3.where(RealmWallet.class).equalTo("id", r2).findFirst();
        if (rwR2 == null)
            return null;
        Wallet wallet = r1.convert(rwR2);
        r1.f19305b.get(wallet);
        return wallet;
    }
}
