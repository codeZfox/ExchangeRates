package com.codezfox.exchangerates.di;

import com.codezfox.exchangerates.ui.currencies.CurrenciesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract CurrenciesFragment contributeRatesFragment();

}
