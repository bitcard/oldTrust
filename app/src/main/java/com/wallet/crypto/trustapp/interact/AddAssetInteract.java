package com.wallet.crypto.trustapp.interact;

import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.PlainAddress;
import trust.blockchain.entity.Unit;

public class AddAssetInteract {
    /* renamed from: a */
    private final SessionRepository f16617a;
    /* renamed from: b */
    private final AssetsController f16618b;

    @Inject
    public AddAssetInteract(SessionRepository sessionRepository, AssetsController assetsController) {
        this.f16617a = sessionRepository;
        this.f16618b = assetsController;
    }

    /* renamed from: a */
    public static /* synthetic */ CompletableSource m8a(AddAssetInteract addAssetInteract, int i, String str, String str2, String str3, Slip slip, Session session) throws Exception {
        Session session2 = session;
        int i2 = i;
        String str4 = str;
        AddAssetInteract addAssetInteract2 = addAssetInteract;
        return Completable.fromAction(() -> addAssetInteract.f16618b.addAsset(session2, new Asset[]{new Asset(2, new Contract(new PlainAddress(str2), str2, str3, new Unit(i, str), slip, str2), session2.wallet.account(slip), true, true)}));
    }

    public Completable add(String str, String str2, String str3, int i, Slip slip) {
        return this.f16617a.getSessionOperator().flatMapCompletable(session -> m8a(this, i, str3, str2, str, slip, session)).observeOn(AndroidSchedulers.mainThread());
    }
}
