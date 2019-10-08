package com.wallet.crypto.trustapp.ui.transfer.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.router.ConfirmationRouter;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.SendViewModel;
import io.reactivex.annotations.NonNull;
import javax.inject.Inject;

public class SendViewModelFactory implements Factory {
    /* renamed from: a */
    private final ConfirmationRouter f20002a;

    @Inject
    public SendViewModelFactory(ConfirmationRouter confirmationRouter) {
        this.f20002a = confirmationRouter;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> cls) {
        return (T) new SendViewModel(this.f20002a);
    }
}
