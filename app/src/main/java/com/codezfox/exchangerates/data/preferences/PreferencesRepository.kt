package com.codezfox.exchangerates.data.preferences

import java.util.*


interface PreferencesRepository {

    fun saveLastDateData(date: Date)

    fun getLastDateData(): Date

}
