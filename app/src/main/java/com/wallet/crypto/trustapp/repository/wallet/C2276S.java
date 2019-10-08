package com.wallet.crypto.trustapp.repository.wallet;

import com.wallet.crypto.trustapp.service.RealmManager.WalletsOperation;
import io.realm.Realm;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.wallet.S */
public final /* synthetic */ class C2276S implements WalletsOperation {
    /* renamed from: a */
    private final /* synthetic */ RealmWalletStore f19306a;
    /* renamed from: b */
    private final /* synthetic */ String f19307b;

    public /* synthetic */ C2276S(RealmWalletStore realmWalletStore, String str) {
        this.f19306a = realmWalletStore;
        this.f19307b = str;
    }

    public final Object execute(Realm realm) {
        return RealmWalletStore.m91a(this.f19306a, this.f19307b, realm);
    }
}
