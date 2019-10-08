package com.wallet.crypto.trustapp.interact.migration;

import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import io.reactivex.functions.Function;
import trust.blockchain.entity.Wallet;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.migration.b */
public final /* synthetic */ class C2212b implements Function {
    /* renamed from: a */
    private final /* synthetic */ AddDefaultAssetsInteract f19150a;

    public /* synthetic */ C2212b(AddDefaultAssetsInteract addDefaultAssetsInteract) {
        this.f19150a = addDefaultAssetsInteract;
    }

    public final Object apply(Object obj) {
        return this.f19150a.add((Wallet) obj);
    }
}
