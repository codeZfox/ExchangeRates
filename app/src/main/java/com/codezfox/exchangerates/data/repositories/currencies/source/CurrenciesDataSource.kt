package com.codezfox.exchangerates.data.repositories.currencies.source

import com.codezfox.exchangerates.data.models.Currency


interface CurrenciesDataSource {

    fun getData(): List<Currency>

}