package com.wallet.crypto.trustapp.di;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings.Secure;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.entity.DappCategory;
import com.wallet.crypto.trustapp.entity.Timeout;
import com.wallet.crypto.trustapp.entity.TimeoutAdapter;
import com.wallet.crypto.trustapp.entity.TokenTicker;
import com.wallet.crypto.trustapp.interact.UrlHandlerInteract;
import com.wallet.crypto.trustapp.interact.nonce.EthTransactionsNonceInteract;
import com.wallet.crypto.trustapp.repository.DappLinksRepositoryType;
import com.wallet.crypto.trustapp.repository.PasscodeRepository;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.RealmDappLinksRepository;
import com.wallet.crypto.trustapp.repository.SharedPreferenceRepository;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.assets.AssetsLocalSource;
import com.wallet.crypto.trustapp.repository.assets.AssetsRealmSource;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesLocalSource;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRealmSource;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRepository;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.TWAssetsController;
import com.wallet.crypto.trustapp.repository.dapp.DappLocalStore;
import com.wallet.crypto.trustapp.repository.dapp.DappRepository;
import com.wallet.crypto.trustapp.repository.dapp.DappRepositoryType;
import com.wallet.crypto.trustapp.repository.dapp.RealmDappStore;
import com.wallet.crypto.trustapp.repository.dex.LotRepository;
import com.wallet.crypto.trustapp.repository.dex.MemoryLotCache;
import com.wallet.crypto.trustapp.repository.dex.TrustMarketLotRepository;
import com.wallet.crypto.trustapp.repository.market.MarketRepository;
import com.wallet.crypto.trustapp.repository.market.MarketRepositoryType;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepositoryType;
import com.wallet.crypto.trustapp.repository.session.PreferenceSessionRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionLocalSource;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRealmCache;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.KeyStoreAdapter;
import com.wallet.crypto.trustapp.repository.wallet.RealmWalletStore;
import com.wallet.crypto.trustapp.repository.wallet.TrustWalletCryptographer;
import com.wallet.crypto.trustapp.repository.wallet.WalletStore;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WatchAdapter;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.service.AssetsCurrencyInfoService;
import com.wallet.crypto.trustapp.service.CurrencyInfoService;
import com.wallet.crypto.trustapp.service.RealmAccountIndices;
import com.wallet.crypto.trustapp.service.RealmManager;
import com.wallet.crypto.trustapp.service.TransactionsService;
import com.wallet.crypto.trustapp.service.TransactionsServiceAdapter;
import com.wallet.crypto.trustapp.service.market.ApiMarketService;
import com.wallet.crypto.trustapp.service.market.TrustApiMarketService;
import com.wallet.crypto.trustapp.service.rpc.binance.BinanceChainRpcService;
import com.wallet.crypto.trustapp.service.rpc.binance.BinanceRpcClient;
import com.wallet.crypto.trustapp.service.rpc.bitcoin.BitcoinLikeRpcService;
import com.wallet.crypto.trustapp.service.rpc.bitcoin.BitcoinRpcClient;
import com.wallet.crypto.trustapp.service.rpc.bitcoin.BitcoinTransactionsService;
import com.wallet.crypto.trustapp.service.rpc.cosmos.CosmosRpcClient;
import com.wallet.crypto.trustapp.service.rpc.cosmos.CosmosRpcService;
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientEnum;
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientType;
import com.wallet.crypto.trustapp.service.rpc.ethereum.EthereumClient;
import com.wallet.crypto.trustapp.service.rpc.ethereum.EthereumRpcService;
import com.wallet.crypto.trustapp.service.rpc.ethereum.ThorRpcService;
import com.wallet.crypto.trustapp.service.rpc.ethereum.WanchainRpcService;
import com.wallet.crypto.trustapp.service.rpc.ethereum.Web3jEthereumClient;
import com.wallet.crypto.trustapp.service.rpc.icon.IconRpcService;
import com.wallet.crypto.trustapp.service.rpc.iotex.IotexRpcClient;
import com.wallet.crypto.trustapp.service.rpc.iotex.IotexRpcService;
import com.wallet.crypto.trustapp.service.rpc.iotex.IotexTransactionSigner;
import com.wallet.crypto.trustapp.service.rpc.kin.KinRpcService;
import com.wallet.crypto.trustapp.service.rpc.nimiq.NimiqRpcClient;
import com.wallet.crypto.trustapp.service.rpc.nimiq.NimiqRpcService;
import com.wallet.crypto.trustapp.service.rpc.ontology.OntologyRpcClient;
import com.wallet.crypto.trustapp.service.rpc.ontology.OntologyRpcService;
import com.wallet.crypto.trustapp.service.rpc.ripple.RippleRpcClient;
import com.wallet.crypto.trustapp.service.rpc.ripple.RippleRpcService;
import com.wallet.crypto.trustapp.service.rpc.stellar.StellarRpcClient;
import com.wallet.crypto.trustapp.service.rpc.stellar.StellarRpcService;
import com.wallet.crypto.trustapp.service.rpc.tezos.TezosRpcClient;
import com.wallet.crypto.trustapp.service.rpc.tezos.TezosRpcService;
import com.wallet.crypto.trustapp.service.rpc.theta.ThetaRpcClient;
import com.wallet.crypto.trustapp.service.rpc.theta.ThetaRpcService;
import com.wallet.crypto.trustapp.service.rpc.tron.TronRpcClient;
import com.wallet.crypto.trustapp.service.rpc.tron.TronRpcService;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.BlockDeserializer;
import com.wallet.crypto.trustapp.service.rpc.waves.WavesRpcClient;
import com.wallet.crypto.trustapp.service.rpc.waves.WavesRpcService;
import com.wallet.crypto.trustapp.service.rpc.waves.WavesTransactionSigner;
import com.wallet.crypto.trustapp.service.rpc.zilliqa.ZilliqaRpcClient;
import com.wallet.crypto.trustapp.service.rpc.zilliqa.ZilliqaRpcService;
import com.wallet.crypto.trustapp.service.rpc.zilliqa.ZilliqaTransactionSigner;
import com.wallet.crypto.trustapp.service.tick.HandlerTickCoordinatorService;
import com.wallet.crypto.trustapp.service.tick.TickCoordinatorService;
import com.wallet.crypto.trustapp.service.trustapi.ApiClient;
import com.wallet.crypto.trustapp.service.trustapi.BlockatlasClient;
import com.wallet.crypto.trustapp.service.trustapi.TrustApiService;
import com.wallet.crypto.trustapp.service.trustapi.entity.ContractDeserializer;
import com.wallet.crypto.trustapp.service.trustapi.entity.DappCategoryDeserializer;
import com.wallet.crypto.trustapp.service.trustapi.entity.TokenTickersDeserializer;
import com.wallet.crypto.trustapp.ui.dapp.entity.ExternalIntentUrlHandler;
import com.wallet.crypto.trustapp.ui.dapp.entity.TrustUrlHandler;
import com.wallet.crypto.trustapp.util.LogInterceptor;
import com.wallet.crypto.trustapp.util.SystemUtils;

import dagger.Module;
import dagger.Provides;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trust.blockchain.AccountFactory;
import trust.blockchain.AccountIndicesService;
import trust.blockchain.Signer;
import trust.blockchain.Slip;
import trust.blockchain.TransactionSigner;
import trust.blockchain.WalletCoreAccountFactory;
import trust.blockchain.blockchain.aion.AionTransactionSigner;
import trust.blockchain.blockchain.binance.BinanceTransactionSigner;
import trust.blockchain.blockchain.bitcoin.BitcoinRpcService;
import trust.blockchain.blockchain.bitcoin.BitcoinTransactionSigner;
import trust.blockchain.blockchain.cosmos.CosmosTransactionSigner;
import trust.blockchain.blockchain.ethereum.EthereumSigner;
import trust.blockchain.blockchain.ethereum.ThorSigner;
import trust.blockchain.blockchain.groestl.GroestlTransactionSigner;
import trust.blockchain.blockchain.icon.IconSigner;
import trust.blockchain.blockchain.nimiq.NimiqTransactionSigner;
import trust.blockchain.blockchain.ontology.OntologyTransactionSigner;
import trust.blockchain.blockchain.ripple.RippleTransactionSigner;
import trust.blockchain.blockchain.stellar.StellarTransactionSigner;
import trust.blockchain.blockchain.tezos.TezosTransactionSigner;
import trust.blockchain.blockchain.theta.ThetaTransactionSigner;
import trust.blockchain.blockchain.tron.TronTransactionSigner;
import trust.blockchain.blockchain.zcash.ZcashTransactionSigner;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Contract;
import trust.blockchain.mnemonic.MnemonicAdapter;
import trust.blockchain.mnemonic.SecureRandomEntropy;

@Module
public class RepositoriesModule {
    private String getClientId(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return "";
        }
    }

    private OkHttpClient getOkHttpClient(Slip slip) {
        Timeout connectTimeout = TimeoutAdapter.Companion.getINSTANCE().timeoutFor(slip).getConnectTimeout();
        Timeout readTimeout = TimeoutAdapter.Companion.getINSTANCE().timeoutFor(slip).getReadTimeout();
        Timeout writeTimeout = TimeoutAdapter.Companion.getINSTANCE().timeoutFor(slip).getWriteTimeout();
        return new Builder().addInterceptor(new LogInterceptor()).connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES)).connectTimeout(connectTimeout.getTime(), connectTimeout.getUnit()).readTimeout(readTimeout.getTime(), readTimeout.getUnit()).writeTimeout(writeTimeout.getTime(), writeTimeout.getUnit()).build();
    }

    private OkHttpClient getTrustHttpClient(Context context) {
        return new Builder().addInterceptor(new LogInterceptor())
                .connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES))
                .connectTimeout(15, TimeUnit.MINUTES).readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader("client", SystemUtils.getPackage(context)).addHeader("client-build", String.valueOf(SystemUtils.getVersionCode(context))).addHeader("client-version", String.valueOf(SystemUtils.getVersionName(context))).addHeader("client-locale", Locale.getDefault().getLanguage()).addHeader("client-id", this.getClientId(context)).build()))
                .build();
    }

    @Singleton
    @Provides
    DappLinksRepositoryType dappLinksRepository(RealmManager realmManager) {
        return new RealmDappLinksRepository(realmManager);
    }

    @Singleton
    @Provides
    DappLocalStore dappLocalStore(RealmManager realmManager) {
        return new RealmDappStore(realmManager);
    }

    @Singleton
    @Provides
    DappRepository dappRepository(ApiService apiService, DappLocalStore dappLocalStore) {
        return new DappRepositoryType(apiService, dappLocalStore);
    }

    @Singleton
    @Provides
    MarketRepositoryType marketRepositoryType(ApiMarketService apiMarketService) {
        return new MarketRepository(apiMarketService);
    }

    @Singleton
    @Provides
    AccountIndicesService provideAccountIndicesService(RealmManager realmManager) {
        return new RealmAccountIndices(realmManager);
    }

    @Singleton
    @Provides
    AionTransactionSigner provideAionTransactionSigner() {
        return new AionTransactionSigner();
    }

    @Singleton
    @Provides
    ApiService provideApiClientService(Context context) {
        Gson create = new GsonBuilder().registerTypeAdapter(Contract.class, new ContractDeserializer()).registerTypeAdapter(TokenTicker[].class, new TokenTickersDeserializer()).registerTypeAdapter(DappCategory.class, new DappCategoryDeserializer()).create();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://api.trustwallet.com");
        builder.client(getTrustHttpClient(context));
        builder.addConverterFactory(GsonConverterFactory.create(create));
        ApiClient apiClient = (ApiClient) builder.build().create(ApiClient.class);

        Retrofit.Builder builder2 = new Retrofit.Builder();
        builder2.baseUrl("https://blockatlas.trustwalletapp.com");
        builder2.client(getTrustHttpClient(context));
        builder2.addConverterFactory(GsonConverterFactory.create(create));
        return new TrustApiService(apiClient, (BlockatlasClient) builder2.build().create(BlockatlasClient.class));
    }

    @Singleton
    @Provides
    ApiMarketService provideApiMarketService(OkHttpClient okHttpClient) {
        return new TrustApiMarketService(okHttpClient);
    }

    @Singleton
    @Provides
    AssetsController provideAssetsConrtoller(BlockchainRepository blockchainRepository, ApiService apiService, AssetsLocalSource assetsLocalSource) {
        return new TWAssetsController(apiService, blockchainRepository, assetsLocalSource);
    }

    @Singleton
    @Provides
    AssetsLocalSource provideAssetsRealmSource(RealmManager realmManager) {
        return new AssetsRealmSource(realmManager);
    }

    @Singleton
    @Provides
    BinanceRpcClient provideBinanceRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.BINANCE);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.BINANCE));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (BinanceRpcClient) builder.build().create(BinanceRpcClient.class);
    }

    @Singleton
    @Provides
    BinanceTransactionSigner provideBinanceTransactionSigner(BinanceChainRpcService binanceChainRpcService) {
        return new BinanceTransactionSigner(binanceChainRpcService);
    }

    @Singleton
    @Provides
    BitcoinRpcClient provideBitcoinRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.BTC);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.BTC));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (BitcoinRpcClient) builder.build().create(BitcoinRpcClient.class);
    }

    @Singleton
    @Provides
    BitcoinRpcService provideBitcoinRpcService(BitcoinRpcClient bitcoinRpcClient, AccountIndicesService accountIndicesService) {
        return new BitcoinLikeRpcService(bitcoinRpcClient, accountIndicesService);
    }

    @Singleton
    @Provides
    BitcoinTransactionSigner provideBitcoinTransactionSigner(BitcoinLikeRpcService bitcoinLikeRpcService) {
        return new BitcoinTransactionSigner(bitcoinLikeRpcService);
    }

    @Singleton
    @Provides
    BitcoinTransactionsService provideBitcoinTransactionsService(BitcoinRpcClient bitcoinRpcClient) {
        return new BitcoinTransactionsService(bitcoinRpcClient);
    }

    @Singleton
    @Provides
    CollectiblesLocalSource provideCollectiblesLocalSource(RealmManager realmManager) {
        return new CollectiblesRealmSource(realmManager);
    }

    @Singleton
    @Provides
    CollectiblesRepository provideCollectiblesRepository(ApiService apiService, CollectiblesLocalSource collectiblesLocalSource) {
        return new CollectiblesRepositoryType(apiService, collectiblesLocalSource);
    }

    @Singleton
    @Provides
    CosmosRpcClient provideCosmosRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.COSMOS);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.COSMOS));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (CosmosRpcClient) builder.build().create(CosmosRpcClient.class);
    }

    @Singleton
    @Provides
    CosmosTransactionSigner provideCosmosTransactionSigner(CosmosRpcService cosmosRpcService) {
        return new CosmosTransactionSigner(cosmosRpcService);
    }

    @Singleton
    @Provides
    CurrencyInfoService provideCurrencyInfoService(Context context) {
        return new AssetsCurrencyInfoService(context.getAssets(), "currencies.json");
    }

    @Singleton
    @Provides
    EthereumRpcService provideEthRpcService(EthereumClient ethereumClient, EthTransactionsNonceInteract ethTransactionsNonceInteract) {
        return new EthereumRpcService(ethereumClient, ethTransactionsNonceInteract);
    }

    @Singleton
    @Provides
    EthereumClient provideEthereumClient(OkHttpClient okHttpClient) {
        return new Web3jEthereumClient(okHttpClient);
    }

    @Singleton
    @Provides
    GroestlTransactionSigner provideGroestlTransactionSigner(BitcoinLikeRpcService bitcoinLikeRpcService) {
        return new GroestlTransactionSigner(bitcoinLikeRpcService);
    }

    @Singleton
    @Provides
    IotexRpcClient provideIotexRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.IOTEX);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.IOTEX));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (IotexRpcClient) builder.build().create(IotexRpcClient.class);
    }

    @Singleton
    @Provides
    IotexTransactionSigner provideIotexTransactionSigner(IotexRpcService iotexRpcService) {
        return new IotexTransactionSigner(iotexRpcService);
    }

    @StellarClientType(StellarClientEnum.KIN)
    @Singleton
    @Provides
    StellarRpcClient provideKinRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.KIN);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.KIN));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (StellarRpcClient) builder.build().create(StellarRpcClient.class);
    }

    @Singleton
    @Provides
    LotRepository provideLotRepository(BlockchainRepository blockchainRepository, ApiService apiService) {
        return new TrustMarketLotRepository(blockchainRepository, apiService, new MemoryLotCache());
    }

    @Singleton
    @Provides
    NimiqRpcClient provideNimiqRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.NIMIQ);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.NIMIQ));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (NimiqRpcClient) builder.build().create(NimiqRpcClient.class);
    }

    @Singleton
    @Provides
    NimiqTransactionSigner provideNimiqTransactionSigner(NimiqRpcService nimiqRpcService) {
        return new NimiqTransactionSigner(nimiqRpcService);
    }

    @Singleton
    @Provides
    OntologyRpcClient provideOntologyRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.ONTOLOGY);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.ONTOLOGY));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (OntologyRpcClient) builder.build().create(OntologyRpcClient.class);
    }

    @Singleton
    @Provides
    OntologyTransactionSigner provideOntologyTransactionSigner(OntologyRpcService ontologyRpcService) {
        return new OntologyTransactionSigner(ontologyRpcService);
    }

    @Singleton
    @Provides
    PasscodeRepositoryType providePasscodeRepository(RealmManager realmManager, PasswordStore passwordStore) {
        return new PasscodeRepository(realmManager, passwordStore);
    }

    @Singleton
    @Provides
    PreferenceRepositoryType providePreferenceRepository(Context context) {
        return new SharedPreferenceRepository(context);
    }

    @Singleton
    @Provides
    RippleRpcClient provideRippleRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.RIPPLE);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.RIPPLE));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (RippleRpcClient) builder.build().create(RippleRpcClient.class);
    }

    @Singleton
    @Provides
    RippleTransactionSigner provideRippleTransactionSigner(RippleRpcService rippleRpcService) {
        return new RippleTransactionSigner(rippleRpcService);
    }

    @Singleton
    @Provides
    SessionRepository provideSessionRepository(PreferenceRepositoryType preferenceRepositoryType, WalletsRepository walletsRepository) {
        return new PreferenceSessionRepository(preferenceRepositoryType, walletsRepository);
    }

    @StellarClientType(StellarClientEnum.STELLAR)
    @Singleton
    @Provides
    StellarRpcClient provideStellarRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.STELLAR);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.STELLAR));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (StellarRpcClient) builder.build().create(StellarRpcClient.class);
    }

    @Singleton
    @Provides
    StellarTransactionSigner provideStellarTransactionSigner(StellarRpcService stellarRpcService) {
        return new StellarTransactionSigner(stellarRpcService);
    }

    @Singleton
    @Provides
    TezosRpcClient provideTezosRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.TEZOS);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.TEZOS));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (TezosRpcClient) builder.build().create(TezosRpcClient.class);
    }

    @Singleton
    @Provides
    TezosTransactionSigner provideTezosTransactionSigner(TezosRpcService tezosRpcService) {
        return new TezosTransactionSigner(tezosRpcService);
    }

    @Singleton
    @Provides
    ThetaRpcClient provideThetaRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.THETA);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.THETA));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (ThetaRpcClient) builder.build().create(ThetaRpcClient.class);
    }

    @Singleton
    @Provides
    ThetaTransactionSigner provideThetaTransactionSigner(ThetaRpcService thetaRpcService) {
        return new ThetaTransactionSigner(thetaRpcService);
    }

    @Singleton
    @Provides
    TransactionLocalSource provideTransactionInDiskCache(RealmManager realmManager) {
        return new TransactionsRealmCache(realmManager);
    }

    @Singleton
    @Provides
    TransactionsRepository provideTransactionRepository(SessionRepository sessionRepository, ApiService apiService, TransactionsServiceAdapter transactionsServiceAdapter, TransactionLocalSource transactionLocalSource) {
        return new TransactionsRepositoryType(sessionRepository, transactionLocalSource, transactionsServiceAdapter, apiService);
    }

    @Singleton
    @Provides
    TransactionsServiceAdapter provideTransactionsServicesAdapter(BitcoinTransactionsService bitcoinTransactionsService) {
        return new TransactionsServiceAdapter(new TransactionsService[]{bitcoinTransactionsService});
    }

    @Singleton
    @Provides
    TronTransactionSigner provideTronTransactionSigner(TronRpcService tronRpcService) {
        return new TronTransactionSigner(tronRpcService);
    }

    @Singleton
    @Provides
    WalletsRepository provideWalletRepository(
            PasswordStore passwordStore,
            WalletStore walletStore,
            BitcoinTransactionSigner bitcoinTransactionSigner,
            ZcashTransactionSigner zcashTransactionSigner,
            BinanceTransactionSigner binanceTransactionSigner,
            RippleTransactionSigner rippleTransactionSigner,
            StellarTransactionSigner stellarTransactionSigner,
            AionTransactionSigner aionTransactionSigner,
            NimiqTransactionSigner nimiqTransactionSigner,
            TronTransactionSigner tronTransactionSigner,
            TezosTransactionSigner tezosTransactionSigner,
            ThetaTransactionSigner thetaTransactionSigner,
            CosmosTransactionSigner cosmosTransactionSigner,
            OntologyTransactionSigner ontologyTransactionSigner,
            GroestlTransactionSigner groestlTransactionSigner,
            ZilliqaTransactionSigner zilliqaTransactionSigner,
            IotexTransactionSigner iotexTransactionSigner,
            WavesTransactionSigner wavesTransactionSigner) {
        Signer[] signerArr = new Signer[]{new EthereumSigner(), new ThorSigner(), new IconSigner()};
        TransactionSigner[] transactionSignerArr = new TransactionSigner[]{bitcoinTransactionSigner, zcashTransactionSigner, binanceTransactionSigner, rippleTransactionSigner, stellarTransactionSigner, aionTransactionSigner, nimiqTransactionSigner, tronTransactionSigner, tezosTransactionSigner, thetaTransactionSigner, cosmosTransactionSigner, ontologyTransactionSigner, groestlTransactionSigner, zilliqaTransactionSigner, iotexTransactionSigner, wavesTransactionSigner};
        AccountFactory[] accountFactoryArr = new AccountFactory[]{new WalletCoreAccountFactory()};
        WatchAdapter watchAdapter = new WatchAdapter();
        KeyStoreAdapter keyStoreAdapter = new KeyStoreAdapter(accountFactoryArr, signerArr, transactionSignerArr);
        Signer[] signerArr2 = signerArr;
        TransactionSigner[] transactionSignerArr2 = transactionSignerArr;
        AccountFactory[] accountFactoryArr2 = accountFactoryArr;
        MnemonicAdapter mnemonicAdapter = new MnemonicAdapter(2, new SecureRandomEntropy(), new TrustWalletCryptographer(), C2189a.f19095a, signerArr2, transactionSignerArr2, accountFactoryArr2);
        MnemonicAdapter mnemonicAdapter2 = new MnemonicAdapter(3, new SecureRandomEntropy(), new TrustWalletCryptographer(), C2190b.f19096a, signerArr2, transactionSignerArr2, accountFactoryArr2);
        return new WalletsRepositoryType(walletStore, passwordStore, 3, watchAdapter, keyStoreAdapter, mnemonicAdapter, mnemonicAdapter2);
    }

    @Singleton
    @Provides
    WalletStore provideWalletStore(RealmManager realmManager, AccountIndicesService accountIndicesService) {
        return new RealmWalletStore(realmManager, accountIndicesService);
    }

    @Singleton
    @Provides
    WanchainRpcService provideWanRpcService(EthereumClient ethereumClient, EthTransactionsNonceInteract ethTransactionsNonceInteract) {
        return new WanchainRpcService(ethereumClient, ethTransactionsNonceInteract);
    }

    @Singleton
    @Provides
    WavesRpcClient provideWavesRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.WAVES);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.WAVES));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (WavesRpcClient) builder.build().create(WavesRpcClient.class);
    }

    @Singleton
    @Provides
    WavesRpcService provideWavesRpcService(WavesRpcClient wavesRpcClient) {
        return new WavesRpcService(wavesRpcClient);
    }

    @Singleton
    @Provides
    WavesTransactionSigner provideWavesTransactionSigner() {
        return new WavesTransactionSigner();
    }

    @Singleton
    @Provides
    ZcashTransactionSigner provideZcashTransactionSigner(BitcoinLikeRpcService bitcoinLikeRpcService) {
        return new ZcashTransactionSigner(bitcoinLikeRpcService);
    }

    @Singleton
    @Provides
    ZilliqaRpcClient provideZilliqaRpcClient() {
        OkHttpClient okHttpClient = getOkHttpClient(Slip.ZILLIQA);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.ZILLIQA));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        return (ZilliqaRpcClient) builder.build().create(ZilliqaRpcClient.class);
    }

    @Singleton
    @Provides
    ZilliqaRpcService provideZilliqaRpcService(ZilliqaRpcClient zilliqaRpcClient, EthTransactionsNonceInteract ethTransactionsNonceInteract) {
        return new ZilliqaRpcService(zilliqaRpcClient, ethTransactionsNonceInteract);
    }

    @Singleton
    @Provides
    ZilliqaTransactionSigner provideZilliqaTransactionSigner() {
        return new ZilliqaTransactionSigner();
    }

    @Singleton
    @Provides
    BlockchainRepository providesNodeRepository(SessionRepository sessionRepository,
                                                TransactionsRepository transactionsRepository,
                                                TransactionsServiceAdapter transactionsServiceAdapter,
                                                EthereumRpcService ethereumRpcService,
                                                ThorRpcService thorRpcService,
                                                WanchainRpcService wanchainRpcService,
                                                TronRpcService tronRpcService,
                                                IconRpcService iconRpcService,
                                                BitcoinLikeRpcService bitcoinLikeRpcService,
                                                BinanceChainRpcService binanceChainRpcService,
                                                RippleRpcService rippleRpcService,
                                                StellarRpcService stellarRpcService,
                                                KinRpcService kinRpcService,
                                                NimiqRpcService nimiqRpcService,
                                                TezosRpcService tezosRpcService,
                                                CosmosRpcService cosmosRpcService,
                                                ThetaRpcService thetaRpcService,
                                                OntologyRpcService ontologyRpcService,
                                                ZilliqaRpcService zilliqaRpcService,
                                                IotexRpcService iotexRpcService,
                                                WavesRpcService wavesRpcService) {
        return new BlockchainRepositoryType(new Handler(), sessionRepository, transactionsRepository, transactionsServiceAdapter, ethereumRpcService, thorRpcService, wanchainRpcService, tronRpcService, iconRpcService, bitcoinLikeRpcService, binanceChainRpcService, rippleRpcService, stellarRpcService, kinRpcService, nimiqRpcService, tezosRpcService, cosmosRpcService, thetaRpcService, ontologyRpcService, zilliqaRpcService, iotexRpcService, wavesRpcService);
    }

    @Singleton
    @Provides
    TronRpcClient providesTronRpcClient() {
        Gson create = new GsonBuilder().registerTypeAdapter(BlockInfo.class, new BlockDeserializer()).create();
        OkHttpClient okHttpClient = getOkHttpClient(Slip.TRX);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(C.rpcUrl(Slip.TRX));
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create(create));
        return (TronRpcClient) builder.build().create(TronRpcClient.class);
    }

    @Singleton
    @Provides
    TickCoordinatorService tickCoordinatorService(SessionRepository sessionRepository, AssetsController assetsController) {
        return new HandlerTickCoordinatorService(sessionRepository, assetsController);
    }

    @Singleton
    @Provides
    UrlHandlerInteract urlHandlerInteract() {
        return new UrlHandlerInteract(new TrustUrlHandler(), new ExternalIntentUrlHandler());
    }
}
