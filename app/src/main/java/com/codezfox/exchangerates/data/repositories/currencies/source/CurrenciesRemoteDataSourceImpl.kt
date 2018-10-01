package com.codezfox.exchangerates.data.repositories.currencies.source

import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.repositories.Api
import com.codezfox.exchangerates.utils.bodyOrError

internal class CurrenciesRemoteDataSourceImpl : CurrenciesDataSource {

    private val api = Api.buildApi()

    override fun getData(): List<Currency> {
        return api.getData().bodyOrError().courses!!
    }

}