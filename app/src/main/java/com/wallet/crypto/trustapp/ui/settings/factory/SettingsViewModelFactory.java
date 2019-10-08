package com.wallet.crypto.trustapp.ui.settings.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ManageWalletsRouter;
import com.wallet.crypto.trustapp.router.PushNotificationsSettingsRouter;
import com.wallet.crypto.trustapp.router.SelectCurrencyRouter;
import com.wallet.crypto.trustapp.ui.settings.viewmodel.SettingsViewModel;
import javax.inject.Inject;

public class SettingsViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19908a;
    /* renamed from: b */
    private final ManageWalletsRouter f19909b;
    /* renamed from: c */
    private final SelectCurrencyRouter f19910c;
    /* renamed from: d */
    private final PasscodeRepositoryType f19911d;
    /* renamed from: e */
    private final PreferenceRepositoryType f19912e;
    /* renamed from: f */
    private final PushNotificationsSettingsRouter f19913f;

    @Inject
    public SettingsViewModelFactory(SessionRepository sessionRepository, ManageWalletsRouter manageWalletsRouter, SelectCurrencyRouter selectCurrencyRouter, PasscodeRepositoryType passcodeRepositoryType, PreferenceRepositoryType preferenceRepositoryType, PushNotificationsSettingsRouter pushNotificationsSettingsRouter) {
        this.f19908a = sessionRepository;
        this.f19909b = manageWalletsRouter;
        this.f19910c = selectCurrencyRouter;
        this.f19911d = passcodeRepositoryType;
        this.f19912e = preferenceRepositoryType;
        this.f19913f = pushNotificationsSettingsRouter;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new SettingsViewModel(this.f19908a, this.f19909b, this.f19910c, this.f19911d, this.f19912e, this.f19913f);
    }
}
