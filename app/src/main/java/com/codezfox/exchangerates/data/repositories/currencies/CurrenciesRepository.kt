package com.codezfox.exchangerates.data.repositories.currencies

import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.utils.ErrorCause


interface CurrenciesRepository {

    fun getData(): Pair<List<Currency>, ErrorCause?>

}