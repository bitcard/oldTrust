package com.wallet.crypto.trustapp.repository.wallet;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import trust.blockchain.Slip;
import trust.blockchain.WalletAdapter;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Wallet;
import trust.blockchain.entity.WalletDescriptor;

public class WalletsRepositoryType implements WalletsRepository {
    /* renamed from: a */
    private static final Pattern f19314a = Pattern.compile("(?![№#\\s])\\d+$");
    /* renamed from: b */
    private final WalletStore f19315b;
    /* renamed from: c */
    private final WalletAdapter[] f19316c;
    /* renamed from: d */
    private final int f19317d;
    /* renamed from: e */
    private final PasswordStore f19318e;

    public WalletsRepositoryType(WalletStore walletStore, PasswordStore passwordStore, int i, WalletAdapter... walletAdapterArr) {
        this.f19315b = walletStore;
        this.f19318e = passwordStore;
        this.f19317d = i;
        this.f19316c = walletAdapterArr;
    }

    private WalletAdapter getAdapterByType(int i) {
        for (WalletAdapter walletAdapter : this.f19316c) {
            if (walletAdapter.getType() == i) {
                return walletAdapter;
            }
        }
        throw new IllegalArgumentException("Wallet adapter not found");
    }

    private Slip[] getCoins() {
        return Slip.available();
    }

    private String getPassword(String str) {
        str = this.f19318e.getPassword(str);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalStateException("An error occurred during the export. No access.");
    }

    private Single<Wallet> save(WalletDescriptor walletDescriptor, String str) {
        return this.f19315b.add(walletDescriptor.type, str, walletDescriptor.data, walletDescriptor.accounts);
    }

    private SingleSource<? extends Wallet> savePassword(Wallet wallet, String str) {
        return Single.fromCallable(()->WalletsRepositoryType.m116a(this, wallet, str));
    }

    public Single<Wallet> addWallet(int i, String str, String str2, String str3, Slip[] slipArr) {
        WalletAdapter adapterByType = getAdapterByType(i);
        String generatePassword = this.f19318e.generatePassword();
        return adapterByType.importWallet(str.getBytes(), str3, generatePassword, slipArr)
                .flatMap(walletDescriptor -> save(walletDescriptor, str2))
                .flatMap(wallet -> savePassword((Wallet) wallet, generatePassword));
    }

    public Completable deleteWallet(Wallet wallet) {
        return this.f19315b.delete(wallet).andThen(fetch().flatMapCompletable(wallets -> Completable.fromAction(()->WalletsRepositoryType.m120b(this, wallet, wallets))));
    }

    public Single<String> exportKeyStore(Wallet wallet, String str, Account account) {
        return this.f19315b.getWalletData(wallet)
                .flatMap(o -> getAdapterByType(wallet.type).exportKeyStore((byte[]) o, getPassword(wallet.id), str, account))
                .map(oa.f19383a);
    }

    public Single<String> exportPhrase(Wallet wallet) {
        return this.f19315b.getWalletData(wallet)
                .flatMap(o -> getAdapterByType(wallet.type).exportPhrase((byte[]) o, getPassword(wallet.id)))
                .map(oa.f19383a);
    }

    public Single<String> exportPrivateKey(Wallet wallet, Account account) {
        return this.f19315b.getWalletData(wallet)
                .flatMap(bytes -> getAdapterByType(wallet.type).exportPrivateKey(bytes, this.getPassword(wallet.id), null, account))
                .flatMap(o -> getAdapterByType(wallet.type).exportPrivateKey((byte[]) o, getPassword(wallet.id), null, account))
                .map(oa.f19383a);
    }

    public Single<Wallet[]> fetch() {
        return this.f19315b.fetch();
    }

    public Single<Wallet> findMainWallet() {
        return this.f19315b.findMainWallet();
    }

    public Single<Wallet> findWalletById(String str) {
        return this.f19315b.findWalletById(str);
    }

    public Wallet[] getAllWallet() {
        return this.f19315b.getAllWallets();
    }

    public int getDefaultType() {
        return this.f19317d;
    }

    public Single<Integer> getNextWalletNumber(int i) {
        return fetch()
                .flatMapObservable(wallets -> Observable.fromArray(wallets))
                .filter(wallet -> {
                    boolean z = true;
                    if (i == 3) {
                        if (wallet.type != i) {
                            z = false;
                        }
                        return z;
                    }
                    if (wallet.type == 3) {
                        z = false;
                    }
                    return z;
                })
                .map(wallet -> {
                    Matcher matcher = f19314a.matcher(wallet.name);
                    if (matcher.find()) {
                        return Integer.valueOf(matcher.group(0));
                    }
                    return Integer.valueOf(0);
                })
                .map(C2282Y.f19321a).toList()
                .map(o->{
                    int ii = 1;
                    for (Integer intValue : (List<Integer>)o) {
                        ii = Math.max(intValue.intValue(), ii);
                    }
                    return Integer.valueOf(ii);
                });
    }

    public Wallet getWalletById(String str) {
        return this.f19315b.getWalletById(str);
    }

    public Single<Boolean> isSkipBackup(Wallet wallet) {
        return this.f19315b.isSkipBackup(wallet);
    }

    public Single<Wallet> newWallet(String str) {
        WalletAdapter adapterByType = getAdapterByType(this.f19317d);
        String generatePassword = this.f19318e.generatePassword();
        return adapterByType.create(generatePassword, getCoins())
                .flatMap(walletDescriptor -> save(walletDescriptor, str))
                .flatMap(wallet -> savePassword(wallet, generatePassword));
    }

    public Single<Wallet> reimportWallet(Wallet wallet) {
        return this.f19315b.getWalletData(wallet).flatMap(bytes->WalletsRepositoryType.m119b(this, getAdapterByType(wallet.type), wallet, bytes))
                .flatMap(o -> f19315b.update(wallet, (WalletDescriptor) o));
    }

    public Completable setIsSkipBackup(Wallet wallet, boolean z) {
        return this.f19315b.setIsSkipBackup(wallet, z);
    }

    public Completable setName(Wallet wallet, String str) {
        return this.f19315b.setName(wallet, str);
    }

    public Single<byte[]> signMessage(Wallet wallet, Account account, byte[] bArr, boolean z) {
        return this.f19315b.getWalletData(wallet)
                .flatMap(bytes -> getAdapterByType(wallet.type).sign(bytes, getPassword(wallet.id), account, bArr, z));
    }

    public Single<TransactionSign> signTransaction(Wallet wallet, TransactionUnsigned transactionUnsigned) {
        return this.f19315b.getWalletData(wallet)
                .flatMap(bytes -> getAdapterByType(wallet.type).signTransaction(bytes, getPassword(wallet.id), transactionUnsigned));
    }

    /* renamed from: b */
    public static /* synthetic */ SingleSource m119b(WalletsRepositoryType walletsRepositoryType, WalletAdapter walletAdapter, Wallet wallet, byte[] bArr) throws Exception {
        Slip[] available;
        String password = walletsRepositoryType.getPassword(wallet.id);
        if (wallet.type == 3) {
            available = Slip.available();
        } else {
            available = new Slip[]{wallet.accounts[0].coin};
        }
        return walletAdapter.reimportWallet(bArr, password, available);
    }

    public static /* synthetic */ void m120b(WalletsRepositoryType r1, Wallet r2, Wallet[] r3) throws Exception {
        r1.f19318e.deletePassword(r2.id);
        if (r3.length == 0) {
            r1.f19318e.deletePassword("pin");
            r1.f19318e.deletePassword("pass");
            r1.f19318e.deletePassword("salt");
            r1.f19318e.deletePassword("lock");
            r1.f19318e.deletePassword("dbkey");
        }
    }

    public static /* synthetic */ trust.blockchain.entity.Wallet m116a(com.wallet.crypto.trustapp.repository.wallet.WalletsRepositoryType r2, trust.blockchain.entity.Wallet r3, java.lang.String r4) throws java.lang.Exception {
        if (r2.f19318e.setPassword(r3.id, r4) == false) {
            r2.f19315b.delete(r3);
            throw new IllegalStateException();
        }
        return r3;
    }


}
