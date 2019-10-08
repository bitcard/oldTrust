package com.wallet.crypto.trustapp.di;

import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment;
import com.wallet.crypto.trustapp.ui.transfer.fragment.GasSettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ConfirmTransactionInjectorModule {
    @ContributesAndroidInjector
    abstract ConfirmTransactionFragment bindConfirmTransactionFragment();

    @ContributesAndroidInjector
    abstract GasSettingsFragment bindGasSettingsFragment();
}
