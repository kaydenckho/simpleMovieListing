package com.example.simpleMovieListing.network

import com.example.simpleMovieListing.BASE_URL
import com.example.simpleMovieListing.MOVIE_LIST
import com.example.simpleMovieListing.model.Movie
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Webservice {

    companion object{
        val webservice: Webservice by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(Webservice::class.java)
        }
    }

    @GET(MOVIE_LIST)
    suspend fun getMovies(): List<Movie>

}