package com.wallet.crypto.trustapp.ui.importwallet.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.RegisterPushNotificationsInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.importwallet.viewmodel.ImportWalletViewModel;
import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import javax.inject.Inject;

public class ImportWalletViewModelFactory implements Factory {
    /* renamed from: a */
    private final WalletsRepository f19780a;
    /* renamed from: b */
    private final AssetsController f19781b;
    /* renamed from: c */
    private final AddDefaultAssetsInteract f19782c;
    /* renamed from: d */
    private final RegisterPushNotificationsInteract f19783d;
    /* renamed from: e */
    private final PreferenceRepositoryType f19784e;

    @Inject
    public ImportWalletViewModelFactory(WalletsRepository walletsRepository, AssetsController assetsController, AddDefaultAssetsInteract addDefaultAssetsInteract, RegisterPushNotificationsInteract registerPushNotificationsInteract, PreferenceRepositoryType preferenceRepositoryType) {
        this.f19780a = walletsRepository;
        this.f19781b = assetsController;
        this.f19782c = addDefaultAssetsInteract;
        this.f19783d = registerPushNotificationsInteract;
        this.f19784e = preferenceRepositoryType;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new ImportWalletViewModel(this.f19780a, this.f19781b, this.f19782c, this.f19783d, this.f19784e);
    }
}
