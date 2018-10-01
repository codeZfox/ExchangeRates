package com.codezfox.exchangerates.data.repositories

import com.codezfox.exchangerates.data.models.CurrencyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface Api {

    companion object {

        private const val API_URL = "http://www.nbrb.by/"

        @Suppress("DEPRECATION")
        fun buildApi() = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(buildOkHttpClient())
                .build()
                .create(Api::class.java)

        private fun buildOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().also {
                it.addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()
        }
    }

    @GET("Services/XmlExRates.aspx")
    fun getData(): Call<CurrencyResponse>

}
