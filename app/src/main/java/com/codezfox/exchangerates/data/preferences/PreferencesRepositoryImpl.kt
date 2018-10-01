package com.codezfox.exchangerates.data.preferences

import android.content.Context
import java.util.*


class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    companion object {
        private const val PREF_LAST_DATE_DATA = "PREF_LAST_DATE_DATA"
    }

    private val prefs = context.getSharedPreferences("exchangerates", Context.MODE_PRIVATE)

    override fun saveLastDateData(date: Date) {
        prefs.edit().putLong(PREF_LAST_DATE_DATA, date.time).apply()
    }

    override fun getLastDateData(): Date {
        val time = prefs.getLong(PREF_LAST_DATE_DATA, 0)
        return if (time == 0L) {
            Date()
        } else {
            Date(time)
        }
    }

}