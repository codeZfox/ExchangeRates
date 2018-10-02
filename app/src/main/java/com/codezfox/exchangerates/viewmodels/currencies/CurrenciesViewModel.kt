package com.codezfox.exchangerates.viewmodels.currencies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.data.repositories.currencies.CurrenciesRepository
import com.codezfox.exchangerates.utils.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class CurrenciesViewModel @Inject constructor(

        private val ratesRepository: CurrenciesRepository,

        application: Application

) : AndroidViewModel(application) {

    val items: MutableLiveData<List<Currency>> = MutableLiveData()

    val dataLoading = ObservableBoolean(false)

    val empty = ObservableBoolean(true)
    val errorCause = ObservableField<ErrorCause>()
    val lastDateData = ObservableField<String>()

    val alert = SingleLiveEvent<ErrorCause>()

    init {
        load()
    }

    var simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())

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
                if (!items.value.isNullOrEmpty()) {
                    lastDateData.set("Последнее обновление: " + simpleDateFormat.format(ratesRepository.getLastDateData()))
                    alert.value = errorCause.get()
                }

            }, {

                dataLoading.set(false)

                errorCause.set(it.parseException())
                if (!items.value.isNullOrEmpty()) {
                    alert.value = errorCause.get()
                }

            })
        }
    }
}
