package com.wallet.crypto.trustapp.repository.wallet;

import com.wallet.crypto.trustapp.service.RealmManager.WalletsOperation;
import io.realm.Realm;
import trust.blockchain.entity.Account;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.wallet.u */
public final /* synthetic */ class C2299u implements WalletsOperation {
    /* renamed from: a */
    private final /* synthetic */ RealmWalletStore f19388a;
    /* renamed from: b */
    private final /* synthetic */ int f19389b;
    /* renamed from: c */
    private final /* synthetic */ byte[] f19390c;
    /* renamed from: d */
    private final /* synthetic */ String f19391d;
    /* renamed from: e */
    private final /* synthetic */ Account[] f19392e;

    public /* synthetic */ C2299u(RealmWalletStore realmWalletStore, int i, byte[] bArr, String str, Account[] accountArr) {
        this.f19388a = realmWalletStore;
        this.f19389b = i;
        this.f19390c = bArr;
        this.f19391d = str;
        this.f19392e = accountArr;
    }

    public final Object execute(Realm realm) {
        return RealmWalletStore.m89a(this.f19388a, this.f19389b, this.f19390c, this.f19391d, this.f19392e, realm);
    }
}
