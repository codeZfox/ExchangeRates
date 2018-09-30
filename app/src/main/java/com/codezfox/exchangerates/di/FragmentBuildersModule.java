package com.codezfox.exchangerates.di;

import com.codezfox.exchangerates.rates.RatesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract RatesFragment contributeRatesFragment();

}
