package com.codezfox.exchangerates.data.source

import android.arch.lifecycle.LiveData
import com.codezfox.exchangerates.data.Rate


interface RatesRepository {

    fun getRates(): LiveData<List<Rate>>

}