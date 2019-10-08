package com.wallet.crypto.trustapp.di;

import android.content.Context;
import com.google.gson.Gson;
import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.TrustPasswordStore;
import com.wallet.crypto.trustapp.router.ExportPhraseRouter;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.service.RealmManager;
import com.wallet.crypto.trustapp.util.LogInterceptor;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

@Module
class ToolsModule {
    ToolsModule() {
    }

    @Singleton
    @Provides
    OkHttpClient okHttpClient() {
        return new Builder().addInterceptor(new LogInterceptor()).connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES)).connectTimeout(15, TimeUnit.MINUTES).readTimeout(30, TimeUnit.MINUTES).writeTimeout(30, TimeUnit.MINUTES).build();
    }

    @Singleton
    @Provides
    PasswordStore passwordStore(Context context) {
        return new TrustPasswordStore(context.getFilesDir());
    }

    @Provides
    Context provideContext(App app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    RealmManager provideRealmManager(PasswordStore passwordStore) {
        return new RealmManager(passwordStore);
    }

}
