package com.wallet.crypto.trustapp.di;

import com.wallet.crypto.trustapp.App;
import com.wallet.crypto.trustapp.ui.passcode.fragment.PasscodeFragment;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ToolsModule.class, RepositoriesModule.class, BuildersModule.class})
public interface AppComponent {

    @dagger.Component.Builder
    public interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }

    void inject(App app);

    void inject(PasscodeFragment passcodeFragment);
}
