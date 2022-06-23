package com.example.simpleMovieListing.network

import com.example.simpleMovieListing.model.PriceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor() {

    var client: Webservice = Webservice.webservice

    suspend fun getAllPrices() : Flow<List<PriceModel>> = flow {
        emit(client.getAllPrices())
    }

    suspend fun getSymbol(symbol: String) : Flow<PriceModel> = flow {
        emit(client.getSymbol(symbol))
    }
}