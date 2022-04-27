package com.example.simplecryptolisting.network

import com.example.simplecryptolisting.model.PriceModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Webservice {

    companion object{
        val URL = "https://api.wazirx.com/"

        val webservice by lazy {
            Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(Webservice::class.java)
        }
    }

    @GET("sapi/v1/tickers/24hr")
    suspend fun getData(): List<PriceModel>

}