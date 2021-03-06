package com.codezfox.exchangerates.di;

import android.app.Application;

import com.codezfox.exchangerates.App;
import com.codezfox.exchangerates.data.repositories.currencies.CurrenciesRepositoryImpl;
import com.codezfox.exchangerates.data.repositories.currencies.source.local.CurrenciesLocalDataSourceImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App application);

    void inject(CurrenciesRepositoryImpl currenciesRepository);

    void inject(CurrenciesLocalDataSourceImpl currenciesLocalDataSource);

}
