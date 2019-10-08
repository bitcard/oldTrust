package com.wallet.crypto.trustapp.repository;

import com.wallet.crypto.trustapp.service.RealmManager;
import javax.inject.Inject;

public class RealmDappLinksRepository implements DappLinksRepositoryType {
    /* renamed from: a */
    private final RealmManager f19200a;

    @Inject
    public RealmDappLinksRepository(RealmManager realmManager) {
        this.f19200a = realmManager;
    }
}
