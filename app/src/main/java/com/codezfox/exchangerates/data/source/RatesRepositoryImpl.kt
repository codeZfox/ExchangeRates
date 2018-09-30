package com.codezfox.exchangerates.data.source

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.codezfox.exchangerates.data.Rate

class RatesRepositoryImpl : RatesRepository {

    override fun getRates(): LiveData<List<Rate>> {

        val data = MutableLiveData<List<Rate>>()

        data.value = listOf(Rate(), Rate())

        return data
    }
}