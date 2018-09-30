package com.codezfox.exchangerates.rates

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableBoolean
import com.codezfox.exchangerates.data.Rate
import com.codezfox.exchangerates.data.source.RatesRepository
import javax.inject.Inject

fun Collection<Any>?.isNullOrEmpty() = this == null || this.isEmpty()

class RatesViewModel @Inject constructor(

        private var ratesRepository: RatesRepository,

        application: Application

) : AndroidViewModel(application) {

    val ratesListObservable: LiveData<List<Rate>> = ratesRepository.getRates()

    val empty = ObservableBoolean(false)

    private val observeList: (List<Rate>?) -> Unit = { items ->
        empty.set(items.isNullOrEmpty())
    }

    init {
        ratesListObservable.observeForever(observeList)
    }

    override fun onCleared() {
        ratesListObservable.removeObserver(observeList)
    }

    fun load() {

    }
}