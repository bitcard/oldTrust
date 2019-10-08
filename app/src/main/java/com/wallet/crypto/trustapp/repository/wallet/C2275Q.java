package com.wallet.crypto.trustapp.repository.wallet;

import com.wallet.crypto.trustapp.service.RealmManager.WalletsOperation;
import io.realm.Realm;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.wallet.Q */
public final /* synthetic */ class C2275Q implements WalletsOperation {
    /* renamed from: a */
    private final /* synthetic */ RealmWalletStore f19302a;
    /* renamed from: b */
    private final /* synthetic */ Wallet f19303b;

    public /* synthetic */ C2275Q(RealmWalletStore realmWalletStore, Wallet wallet) {
        this.f19302a = realmWalletStore;
        this.f19303b = wallet;
    }

    public final Object execute(Realm realm) {
        return RealmWalletStore.m87a(this.f19302a, this.f19303b, realm);
    }
}
