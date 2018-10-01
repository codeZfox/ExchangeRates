package com.codezfox.exchangerates.di;

import android.arch.lifecycle.ViewModelProvider;

import com.codezfox.exchangerates.data.repositories.currencies.CurrenciesRepository;
import com.codezfox.exchangerates.data.repositories.currencies.CurrenciesRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new ViewModelFactory(viewModelSubComponent.build());
    }

    @Singleton
    @Provides
    CurrenciesRepository provideCurrenciesRepository() {
        return new CurrenciesRepositoryImpl();
    }

}

