package com.example.simplecryptolisting.network

import com.example.simplecryptolisting.model.PriceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor() {

    var client: Webservice = Webservice.webservice
    suspend fun getAllData() : Flow<List<PriceModel>> = flow { emit(client.getData()) }
}