package com.wallet.crypto.trustapp.interact.migration;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import javax.inject.Inject;

public class PinToPasswordStoreInteract {
    /* renamed from: a */
    private final PreferenceRepositoryType f16631a;
    /* renamed from: b */
    private final PasswordStore f16632b;

    @Inject
    public PinToPasswordStoreInteract(PreferenceRepositoryType preferenceRepositoryType, PasswordStore passwordStore) {
        this.f16631a = preferenceRepositoryType;
        this.f16632b = passwordStore;
    }

    public void migrate() {
        String passcode = this.f16631a.getPasscode();
        if (!TextUtils.isEmpty(passcode)) {
            this.f16632b.setPassword("salt", "#4f$fd4%3f+");
            this.f16632b.setPassword("pin", passcode);
            this.f16631a.setPasscode(null);
        }
    }
}
