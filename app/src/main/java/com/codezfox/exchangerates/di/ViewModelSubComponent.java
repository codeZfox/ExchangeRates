package com.codezfox.exchangerates.di;


import com.codezfox.exchangerates.viewmodels.currencies.CurrenciesViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link ViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    CurrenciesViewModel ratesViewModel();

}
