package com.wallet.crypto.trustapp.di;

import com.wallet.crypto.trustapp.ui.assets.fragment.AssetItemDetailsFragment;
import com.wallet.crypto.trustapp.ui.assets.fragment.AssetsFragment;
import com.wallet.crypto.trustapp.ui.assets.fragment.SearchFragment;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesCategoriesFragment;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesItemsFragment;
import com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment;
import com.wallet.crypto.trustapp.ui.dapp.fragment.DappCategoryFragment;
import com.wallet.crypto.trustapp.ui.dapp.fragment.DappDashboardFragment;
import com.wallet.crypto.trustapp.ui.dex.fragment.DexFragment;
import com.wallet.crypto.trustapp.ui.dex.fragment.LotsFragment;
import com.wallet.crypto.trustapp.ui.settings.fragment.SettingsFragment;
import com.wallet.crypto.trustapp.ui.start.fragment.AssetsScreenFragment;
import com.wallet.crypto.trustapp.ui.start.fragment.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface HomePageFragmentInjectorModule {

    @ContributesAndroidInjector
    abstract AssetItemDetailsFragment bindAssetItemDetailsFragment();

    @ContributesAndroidInjector
    abstract CollectiblesItemsFragment bindCollectiblesItemsFragment();

    @ContributesAndroidInjector
    abstract AssetsFragment bindAssetsFragment();

    @ContributesAndroidInjector
    abstract AssetsScreenFragment bindAssetsScreenFragment();

    @ContributesAndroidInjector
    abstract CollectiblesCategoriesFragment bindCollectiblesCategoriesFragment();

    @ContributesAndroidInjector
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector
    abstract SearchFragment bindSearchFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment bindSettingsFragment();

    @ContributesAndroidInjector
    abstract BrowserFragment bindBrowserFragment();

    @ContributesAndroidInjector
    abstract DappDashboardFragment bindDappDashboardFragment();

    @ContributesAndroidInjector
    abstract DexFragment bindDexFragment();

    @ContributesAndroidInjector
    abstract LotsFragment bindLotsFragment();

    @ContributesAndroidInjector
    abstract DappCategoryFragment bindDappCategoryFragment();
}
