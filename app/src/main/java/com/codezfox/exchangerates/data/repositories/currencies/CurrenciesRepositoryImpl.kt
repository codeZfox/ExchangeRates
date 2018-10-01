package com.codezfox.exchangerates.data.repositories.currencies

import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.preferences.PreferencesRepository
import com.codezfox.exchangerates.data.repositories.currencies.source.CurrenciesDataSource
import com.codezfox.exchangerates.data.repositories.currencies.source.local.CurrenciesLocalDataSourceImpl
import com.codezfox.exchangerates.data.repositories.currencies.source.remote.CurrenciesRemoteDataSourceImpl
import com.codezfox.exchangerates.di.AppInjector
import com.codezfox.exchangerates.utils.ErrorCause
import com.codezfox.exchangerates.utils.parseException
import java.util.*
import javax.inject.Inject


class CurrenciesRepositoryImpl : CurrenciesRepository {

    @Inject
    lateinit var preferences: PreferencesRepository

    private val local: CurrenciesDataSource = CurrenciesLocalDataSourceImpl()
    private val remote: CurrenciesDataSource = CurrenciesRemoteDataSourceImpl()

    init {
        AppInjector.appComponent.inject(this)
    }

    override fun getData(): Pair<List<Currency>, ErrorCause?> {
        return try {
            val list = remote.getData()
            local.saveData(list)
            preferences.saveLastDateData(Date())
            list to null
        } catch (e: Exception) {
            local.getData() to e.parseException()
        }
    }

    override fun getLastDateData(): Date {
        return preferences.getLastDateData()
    }

}

