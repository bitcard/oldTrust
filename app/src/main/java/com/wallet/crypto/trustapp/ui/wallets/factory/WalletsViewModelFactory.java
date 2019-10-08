package com.wallet.crypto.trustapp.ui.wallets.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.interact.PushNotificationsInteract;
import com.wallet.crypto.trustapp.interact.RegisterPushNotificationsInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.wallets.viewmodel.WalletsViewModel;
import javax.inject.Inject;

public class WalletsViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f20126a;
    /* renamed from: b */
    private final WalletsRepository f20127b;
    /* renamed from: c */
    private final AssetsController f20128c;
    /* renamed from: d */
    private final PreferenceRepositoryType f20129d;
    /* renamed from: e */
    private final PushNotificationsInteract f20130e;

    @Inject
    public WalletsViewModelFactory(SessionRepository sessionRepository, WalletsRepository walletsRepository, AssetsController assetsController, PreferenceRepositoryType preferenceRepositoryType, RegisterPushNotificationsInteract registerPushNotificationsInteract) {
        this.f20126a = sessionRepository;
        this.f20127b = walletsRepository;
        this.f20128c = assetsController;
        this.f20129d = preferenceRepositoryType;
        this.f20130e = registerPushNotificationsInteract;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new WalletsViewModel(this.f20126a, this.f20127b, this.f20128c, this.f20129d, this.f20130e);
    }
}
