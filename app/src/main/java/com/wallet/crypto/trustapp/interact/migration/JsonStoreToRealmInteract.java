package com.wallet.crypto.trustapp.interact.migration;

import android.content.Context;
import com.wallet.crypto.trustapp.repository.entity.RealmWallet;
import com.wallet.crypto.trustapp.service.RealmManager;
import io.realm.Realm;
import io.realm.RealmResults;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.inject.Inject;

public class JsonStoreToRealmInteract {
    /* renamed from: a */
    private final File f16629a;
    /* renamed from: b */
    private final RealmManager f16630b;

    @Inject
    public JsonStoreToRealmInteract(Context context, RealmManager realmManager) {
        this.f16629a = new File(context.getFilesDir(), "keystore/keystore");
        this.f16630b = realmManager;
    }

    /* renamed from: a */
    public static /* synthetic */ Void m20a(JsonStoreToRealmInteract jsonStoreToRealmInteract, Realm realm) {
        int i = 0;
        RealmResults findAll = realm.where(RealmWallet.class).equalTo("type", Integer.valueOf(0)).findAll();
        if (findAll == null && findAll.size() == 0) {
            return null;
        }
        int size = findAll.size();
        while (i < size) {
            try {
                realm.beginTransaction();
                RealmWallet realmWallet = (RealmWallet) findAll.get(i);
                if (realmWallet != null) {
                    File file = new File(jsonStoreToRealmInteract.f16629a, new String(realmWallet.getData()));
                    String readFile = jsonStoreToRealmInteract.readFile(file);
                    if (readFile != null) {
                        realmWallet.setData(readFile.getBytes());
                    }
                    realm.commitTransaction();
                    file.delete();
                }
                i++;
            } catch (Exception unused) {
                realm.cancelTransaction();
            }
        }
        return null;
    }

    private String readFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine);
                    stringBuilder.append(10);
                } else {
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public void migrate() {
        this.f16630b.getWallets(realm ->JsonStoreToRealmInteract.m20a(this, realm));
    }
}
