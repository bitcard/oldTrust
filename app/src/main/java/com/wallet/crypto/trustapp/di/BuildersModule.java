package com.wallet.crypto.trustapp.di;

import com.wallet.crypto.trustapp.ui.assets.activity.AddAssetActivity;
import com.wallet.crypto.trustapp.ui.assets.activity.BuyCryptoActivity;
import com.wallet.crypto.trustapp.ui.assets.fragment.AssetsFragment;
import com.wallet.crypto.trustapp.ui.currency.CurrencySelectionActivity;
import com.wallet.crypto.trustapp.ui.dapp.fragment.DappDashboardFragment;
import com.wallet.crypto.trustapp.ui.importwallet.activity.ImportWalletActivity;
import com.wallet.crypto.trustapp.ui.market.activity.AssetMarketInfoActivity;
import com.wallet.crypto.trustapp.ui.passcode.activity.PasscodeActivity;
import com.wallet.crypto.trustapp.ui.receive.activity.ReceiveActivity;
import com.wallet.crypto.trustapp.ui.settings.activity.BrowserSettingsActivity;
import com.wallet.crypto.trustapp.ui.settings.activity.ChooseSearchEngineActivity;
import com.wallet.crypto.trustapp.ui.settings.activity.PushNotificationsSettingsActivity;
import com.wallet.crypto.trustapp.ui.settings.fragment.SettingsFragment;
import com.wallet.crypto.trustapp.ui.splash.activity.HandleLinkActivity;
import com.wallet.crypto.trustapp.ui.splash.activity.SplashActivity;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.ui.start.fragment.AssetsScreenFragment;
import com.wallet.crypto.trustapp.ui.start.fragment.MainFragment;
import com.wallet.crypto.trustapp.ui.start.viewmodel.MainViewModel;
import com.wallet.crypto.trustapp.ui.transfer.activity.ConfirmationActivity;
import com.wallet.crypto.trustapp.ui.transfer.activity.SendActivity;
import com.wallet.crypto.trustapp.ui.transfer.activity.TransactionDetailActivity;
import com.wallet.crypto.trustapp.ui.walletconnect.activity.WalletConnectActivity;
import com.wallet.crypto.trustapp.ui.wallets.activity.AddWalletActivity;
import com.wallet.crypto.trustapp.ui.wallets.activity.ExportPhraseActivity;
import com.wallet.crypto.trustapp.ui.wallets.activity.WalletInfoActivity;
import com.wallet.crypto.trustapp.ui.wallets.activity.WalletsActivity;

import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.wallet.crypto.trustapp.di.BuildersModule */
abstract class BuildersModule {


    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract AddWalletActivity bindAddWalletActivity();

    @ContributesAndroidInjector(modules = {HomePageFragmentInjectorModule.class, ConfirmTransactionInjectorModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract ExportPhraseActivity bindExportPhraseActivity();

    @ContributesAndroidInjector
    abstract ImportWalletActivity bindImportWalletActivity();

    @ContributesAndroidInjector
    abstract SendActivity bindSendActivity();

    @ContributesAndroidInjector (modules = {ConfirmTransactionInjectorModule.class})
    abstract ConfirmationActivity bindConfirmationActivity();

    @ContributesAndroidInjector
    abstract ReceiveActivity bindReceiveActivity();

    @ContributesAndroidInjector
    abstract TransactionDetailActivity bindTransactionDetailActivity();

    @ContributesAndroidInjector
    abstract WalletInfoActivity bindWalletInfoActivity();

    @ContributesAndroidInjector
    abstract WalletsActivity bindWalletsActivity();

    @ContributesAndroidInjector
    abstract PasscodeActivity bindPasscodeActivity();

    @ContributesAndroidInjector (modules = {CurrencySelectionModule.class})
    abstract CurrencySelectionActivity bindCurrencySelectionActivity();

    @ContributesAndroidInjector
    abstract BrowserSettingsActivity bindBrowserSettingsActivity();

    @ContributesAndroidInjector
    abstract ChooseSearchEngineActivity bindChooseSearchEngineActivity();

    @ContributesAndroidInjector
    abstract PushNotificationsSettingsActivity bindPushNotificationsSettingsActivity();

    @ContributesAndroidInjector
    abstract AssetMarketInfoActivity bindAssetMarketInfoActivity();

    @ContributesAndroidInjector
    abstract BuyCryptoActivity bindBuyCryptoActivity();

    @ContributesAndroidInjector
    abstract AddAssetActivity bindAddAssetActivity();

    @ContributesAndroidInjector
    abstract WalletConnectActivity bindWalletConnectActivity();
//    @Subcomponent
//    /* renamed from: com.wallet.crypto.trustapp.di.BuildersModule_BindChooseAssetsActivity$ChooseAssetActivitySubcomponent */
//    public interface ChooseAssetActivitySubcomponent extends AndroidInjector<ChooseAssetActivity> {
//
//        @dagger.Subcomponent.Builder
//        public static abstract class Builder extends dagger.android.AndroidInjector.Builder<ChooseAssetActivity> {
//        }
//    }
//
//    @Subcomponent
//    /* renamed from: com.wallet.crypto.trustapp.di.BuildersModule_BindHandleLinkModule$HandleLinkActivitySubcomponent */
//    public interface HandleLinkActivitySubcomponent extends AndroidInjector<HandleLinkActivity> {
//
//        @dagger.Subcomponent.Builder
//        public static abstract class Builder extends dagger.android.AndroidInjector.Builder<HandleLinkActivity> {
//        }
//    }
//
}
