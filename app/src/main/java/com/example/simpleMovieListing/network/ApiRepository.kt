package com.example.simpleMovieListing.network

import com.example.simpleMovieListing.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor() {

    var client: Webservice = Webservice.webservice

    suspend fun getMovies() : Flow<List<Movie>> = flow {
        emit(client.getMovies())
    }
}