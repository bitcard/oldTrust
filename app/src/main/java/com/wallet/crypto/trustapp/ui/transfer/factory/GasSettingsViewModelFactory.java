package com.wallet.crypto.trustapp.ui.transfer.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.GasViewModel;

import javax.inject.Inject;

public class GasSettingsViewModelFactory implements Factory {
    @Inject
    public GasSettingsViewModelFactory() {

    }

    public <T extends ViewModel> T create(Class<T> cls) {
        return (T) new GasViewModel();
    }
}
