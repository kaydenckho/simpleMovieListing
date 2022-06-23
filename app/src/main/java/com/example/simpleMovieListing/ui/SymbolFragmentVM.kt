package com.example.simpleMovieListing.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleMovieListing.model.PriceModel
import com.example.simpleMovieListing.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SymbolFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    val priceLiveData = MutableLiveData<PriceModel>()

    suspend fun getSymbol(symbol: String)  {
        apiRepository.getSymbol(symbol).collect {
            priceLiveData.value = it
        }
    }
}