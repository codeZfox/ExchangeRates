package com.codezfox.exchangerates.di;

import android.arch.lifecycle.ViewModelProvider;

import com.codezfox.exchangerates.data.source.RatesRepository;
import com.codezfox.exchangerates.data.source.RatesRepositoryImpl;

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
    RatesRepository provideRatesRepository() {
        return new RatesRepositoryImpl();
    }

}

