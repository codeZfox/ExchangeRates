package com.codezfox.exchangerates.data.repositories.currencies

import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.repositories.currencies.source.CurrenciesLocalDataSourceImpl
import com.codezfox.exchangerates.data.repositories.currencies.source.CurrenciesRemoteDataSourceImpl
import com.codezfox.exchangerates.utils.ErrorCause
import com.codezfox.exchangerates.utils.parseException


class CurrenciesRepositoryImpl : CurrenciesRepository {

    private val local = CurrenciesLocalDataSourceImpl()
    private val remote = CurrenciesRemoteDataSourceImpl()

    override fun getData(): Pair<List<Currency>, ErrorCause?> {
        return try {
            remote.getData() to null
        } catch (e: Exception) {
            local.getData() to e.parseException()
        }
    }

}

