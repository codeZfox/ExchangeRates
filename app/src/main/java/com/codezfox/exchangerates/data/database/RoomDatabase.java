package com.codezfox.exchangerates.data.database;

import android.arch.persistence.room.Database;

import com.codezfox.exchangerates.data.models.Currency;


@Database(entities = {Currency.class}, version = 1)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract CurrencyDao currencyDao();

}
