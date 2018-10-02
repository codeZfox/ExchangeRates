package com.codezfox.exchangerates.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.codezfox.exchangerates.data.models.Currency;

import java.util.List;


@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM Currency")
    List<Currency> getCurrencies();

    @Query("SELECT * FROM Currency WHERE id = :id")
    Currency getCurrencyById(String id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrency(Currency currency);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrency(List<Currency> currency);

    @Update
    int updateCurrency(Currency currency);


    @Query("DELETE FROM Currency WHERE id = :id")
    int deleteCurrencyById(String id);

    @Query("DELETE FROM Currency")
    void deleteCurrencies();


}
