package com.codezfox.exchangerates.viewmodels.currencies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.repositories.currencies.CurrenciesRepository
import com.codezfox.exchangerates.utils.*
import javax.inject.Inject


class CurrenciesViewModel @Inject constructor(

        private var ratesRepository: CurrenciesRepository,

        application: Application

) : AndroidViewModel(application) {

    val items: MutableLiveData<List<Currency>> = MutableLiveData()

    val dataLoading = ObservableBoolean(false)

    val empty = ObservableBoolean(true)
    val errorCause = ObservableField<ErrorCause>()

    val alert = SingleLiveEvent<ErrorCause>()

    init {
        load()
    }

    fun load() {

        dataLoading.set(true)

        launchUI {
            asyncR {

                ratesRepository.getData()

            }.awaitFold({ (itemsResult, error) ->

                dataLoading.set(false)

                items.value = itemsResult
                empty.set(items.value.isNullOrEmpty())

                errorCause.set(error?.parseException())
                alert.setValue(errorCause.get())

            }, {

                dataLoading.set(false)

                errorCause.set(it.parseException())
                if (!items.value.isNullOrEmpty()) {
                    alert.setValue(errorCause.get())
                }

            })
        }
    }
}
