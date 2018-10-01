package com.codezfox.exchangerates.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.codezfox.exchangerates.data.database.RoomDatabase;
import com.codezfox.exchangerates.data.preferences.PreferencesRepository;
import com.codezfox.exchangerates.data.preferences.PreferencesRepositoryImpl;
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

    @Singleton
    @Provides
    RoomDatabase provideDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                RoomDatabase.class, "Currencies.db")
                .build();
    }

    @Singleton
    @Provides
    PreferencesRepository PreferencesRepository(Application context) {
        return new PreferencesRepositoryImpl(context.getApplicationContext());
    }

}

