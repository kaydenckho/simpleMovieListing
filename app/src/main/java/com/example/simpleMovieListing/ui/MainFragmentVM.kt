package com.example.simpleMovieListing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.simpleMovieListing.adapter.PagingSource
import com.example.simpleMovieListing.model.PriceModel
import com.example.simpleMovieListing.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    fun getPrices() = flow<List<PriceModel>> {
        apiRepository.getAllPrices().collect {
            emit(it)
        }
    }

    fun pagingListData() = Pager(PagingConfig(pageSize = 1, prefetchDistance = 5)) {
        PagingSource(this)
    }.flow.cachedIn(viewModelScope)
}