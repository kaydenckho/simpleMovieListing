package com.example.simplecryptolisting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.simplecryptolisting.adapter.PagingSource
import com.example.simplecryptolisting.model.PriceModel
import com.example.simplecryptolisting.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    val priceLiveData = MutableLiveData<List<PriceModel>>()

    fun getPrices() = flow<List<PriceModel>> {
        apiRepository.getAllPrices().collect {
            emit(it)
        }
    }

    fun getSymbol(symbol: String) = flow<PriceModel> {
        apiRepository.getSymbol(symbol).collect {
            emit(it)
        }
    }

    fun pagingListData() = Pager(PagingConfig(pageSize = 1, prefetchDistance = 5)) {
        PagingSource(this)
    }.flow.cachedIn(viewModelScope)
}