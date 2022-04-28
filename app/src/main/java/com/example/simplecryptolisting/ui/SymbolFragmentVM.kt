package com.example.simplecryptolisting.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecryptolisting.model.PriceModel
import com.example.simplecryptolisting.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
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