package com.codezfox.exchangerates.data.repositories.currencies.source.local

import com.codezfox.exchangerates.data.database.RoomDatabase
import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.repositories.currencies.source.CurrenciesDataSource
import com.codezfox.exchangerates.di.AppInjector
import javax.inject.Inject

internal class CurrenciesLocalDataSourceImpl : CurrenciesDataSource {

    @Inject
    lateinit var database: RoomDatabase

    init {
        AppInjector.appComponent.inject(this)
    }

    override fun getData(): List<Currency> {
        return database.currencyDao().currencies
    }

    override fun saveData(list: List<Currency>) {
        database.currencyDao().deleteCurrencies()
        database.currencyDao().insertCurrency(list)
    }

}