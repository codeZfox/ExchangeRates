package com.codezfox.exchangerates.data.repositories.currencies.source

import com.codezfox.exchangerates.data.models.Currency

internal class CurrenciesLocalDataSourceImpl : CurrenciesDataSource {

    override fun getData(): List<Currency> {
        return listOf(Currency().also { it.name = "asd" })
    }

}