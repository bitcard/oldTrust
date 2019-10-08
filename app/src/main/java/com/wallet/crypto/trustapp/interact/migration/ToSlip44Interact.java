package com.wallet.crypto.trustapp.interact.migration;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.entity.RealmWalletInfo;
import com.wallet.crypto.trustapp.repository.wallet.WalletStore;
import com.wallet.crypto.trustapp.service.RealmManager;
import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import com.wallet.crypto.trustapp.util.CryptoUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.realm.Realm;
import java.io.File;
import java.util.Iterator;
import javax.inject.Inject;
import org.web3j.utils.Numeric;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Wallet;

public class ToSlip44Interact {
    /* renamed from: a */
    private final Context f16633a;
    /* renamed from: b */
    private final PasswordStore f16634b;
    /* renamed from: c */
    private final WalletStore f16635c;
    /* renamed from: d */
    private final RealmManager f16636d;
    /* renamed from: e */
    private final SharedPreferences f16637e;
    /* renamed from: f */
    private final AddDefaultAssetsInteract f16638f;
    /* renamed from: g */
    private int f16639g = 1;

    @Inject
    public ToSlip44Interact(Context context, PasswordStore passwordStore, WalletStore walletStore, RealmManager realmManager, AddDefaultAssetsInteract addDefaultAssetsInteract) {
        this.f16633a = context;
        this.f16634b = passwordStore;
        this.f16635c = walletStore;
        this.f16636d = realmManager;
        this.f16638f = addDefaultAssetsInteract;
        this.f16637e = context.getSharedPreferences("migration", 0);
    }

    /* renamed from: a */
    public static /* synthetic */ void m23a(ToSlip44Interact toSlip44Interact) throws Exception {
        if (!toSlip44Interact.f16637e.getBoolean("all", false)) {
            toSlip44Interact.migrateKeyStore();
            toSlip44Interact.migratePhrase();
            toSlip44Interact.f16637e.edit().putBoolean("all", true).apply();
        }
    }

    private Completable add(int i, String str, byte[] bArr, Slip slip, String str2, String str3) {
        Single add = this.f16635c.add(i, str3, bArr, new Account(slip, "", str));
        AddDefaultAssetsInteract addDefaultAssetsInteract = this.f16638f;
        addDefaultAssetsInteract.getClass();
        return add
                .flatMap(wallet->addDefaultAssetsInteract.add((Wallet) wallet))
                .flatMapCompletable(wallet->Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        f16634b.setPassword((Wallet) wallet, str2);
                    }
                }));
    }

    private String getKey(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("=");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private String getPassword(String str, int i) {
        String password;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(i);
        try {
            password = this.f16634b.getPassword(stringBuilder.toString());
        } catch (Exception unused) {
            password = null;
        }
        try {
            if (TextUtils.isEmpty(password)) {
                password = this.f16634b.getPassword(str);
            }
            return password;
        } catch (Exception unused2) {
            return null;
        }
    }

    private void migrateKeyStore() {
        File file = new File(this.f16633a.getFilesDir(), "keystore/keystore");
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    String[] split = file2.getName().split("--");
                    split = split[split.length - 1].split("\\.");
                    if (split.length != 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("0x");
                        stringBuilder.append(split[0]);
                        String stringBuilder2 = stringBuilder.toString();
                        String key = getKey(stringBuilder2, 0);
                        if (!this.f16637e.getBoolean(key, false)) {
                            String password = getPassword(stringBuilder2, 0);
                            if (!TextUtils.isEmpty(password)) {
                                try {
                                    StringBuilder stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("Wallet #");
                                    stringBuilder3.append(this.f16639g);
                                    String stringBuilder4 = stringBuilder3.toString();
                                    byte[] bytes = file2.getName().getBytes("UTF-8");
                                    Slip slip = Slip.ETH;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(stringBuilder4);
                                    stringBuilder3.append(" ETH");
                                    String str = stringBuilder4;
                                    Throwable blockingGet = add(0, stringBuilder2, bytes, slip, password, stringBuilder3.toString()).blockingGet();
                                    CompletableSource[] completableSourceArr = new CompletableSource[3];
                                    bytes = file2.getName().getBytes("UTF-8");
                                    slip = Slip.POA;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(str);
                                    stringBuilder3.append(" POA");
                                    CompletableSource[] completableSourceArr2 = completableSourceArr;
                                    completableSourceArr2[0] = add(0, stringBuilder2, bytes, slip, password, stringBuilder3.toString());
                                    bytes = file2.getName().getBytes("UTF-8");
                                    slip = Slip.CLO;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(str);
                                    stringBuilder3.append(" CLO");
                                    completableSourceArr2[1] = add(0, stringBuilder2, bytes, slip, password, stringBuilder3.toString());
                                    bytes = file2.getName().getBytes("UTF-8");
                                    slip = Slip.ETC;
                                    StringBuilder stringBuilder5 = new StringBuilder();
                                    stringBuilder5.append(str);
                                    stringBuilder5.append(" ETC");
                                    completableSourceArr2[2] = add(0, stringBuilder2, bytes, slip, password, stringBuilder5.toString());
                                    Throwable blockingGet2 = Completable.mergeArray(completableSourceArr2).blockingGet();
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("Err: ");
                                    stringBuilder.append(blockingGet2);
                                    Log.d("TAG_MIGRATION", stringBuilder.toString());
                                    if (blockingGet == null) {
                                        this.f16637e.edit().putBoolean(key, true).apply();
                                    }
                                    this.f16639g++;
                                } catch (Exception e) {
                                    Log.d("MIGRATION_TO_SLIP44", "Ex", e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void migratePhrase() {
        this.f16636d.getWallets((RealmManager.WalletsOperation) realm -> ToSlip44Interact.m22a(ToSlip44Interact.this, realm));
    }

    public Completable migrate() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                ToSlip44Interact.m23a(ToSlip44Interact.this);
            }
        });
    }

    /* renamed from: a */
    public static /* synthetic */ Void m22a(ToSlip44Interact toSlip44Interact, Realm realm) {
        ToSlip44Interact toSlip44Interact2 = toSlip44Interact;
        Realm realm2 = realm;
        Iterator it = realm2.where(RealmWalletInfo.class).findAll().iterator();
        while (it.hasNext()) {
            RealmWalletInfo realmWalletInfo = (RealmWalletInfo) it.next();
            String address = realmWalletInfo.getAddress();
            String key = toSlip44Interact2.getKey(address, 2);
            if (!toSlip44Interact2.f16637e.getBoolean(key, false)) {
                String password = toSlip44Interact2.getPassword(address, 2);
                if (!TextUtils.isEmpty(password)) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Wallet #");
                        stringBuilder.append(toSlip44Interact2.f16639g);
                        String stringBuilder2 = stringBuilder.toString();
                        byte[] encrypt = CryptoUtils.encrypt(new String(CryptoUtils.decrypt(Numeric.hexStringToByteArray(realmWalletInfo.getData()), password)), password);
                        Slip slip = Slip.ETH;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(stringBuilder2);
                        stringBuilder3.append(" ETH");
                        Throwable blockingGet = toSlip44Interact.add(2, address, encrypt, slip, password, stringBuilder3.toString()).blockingGet();
                        CompletableSource[] completableSourceArr = new CompletableSource[3];
                        slip = Slip.POA;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(stringBuilder2);
                        stringBuilder3.append(" POA");
                        CompletableSource[] completableSourceArr2 = completableSourceArr;
                        completableSourceArr2[0] = toSlip44Interact.add(2, address, encrypt, slip, password, stringBuilder3.toString());
                        slip = Slip.CLO;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(stringBuilder2);
                        stringBuilder3.append(" CLO");
                        completableSourceArr2[1] = toSlip44Interact.add(2, address, encrypt, slip, password, stringBuilder3.toString());
                        slip = Slip.ETC;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(stringBuilder2);
                        stringBuilder3.append(" ETC");
                        completableSourceArr2[2] = toSlip44Interact.add(2, address, encrypt, slip, password, stringBuilder3.toString());
                        Throwable blockingGet2 = Completable.mergeArray(completableSourceArr2).blockingGet();
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("Err: ");
                        stringBuilder4.append(blockingGet2);
                        Log.d("TAG_MIGRATION", stringBuilder4.toString());
                        if (blockingGet == null) {
                            toSlip44Interact2.f16637e.edit().putBoolean(key, true).apply();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        try {
            realm.beginTransaction();
            realm2.delete(RealmWalletInfo.class);
            realm.commitTransaction();
        } catch (Exception unused2) {
            realm.cancelTransaction();
        }
        return null;
    }
}
