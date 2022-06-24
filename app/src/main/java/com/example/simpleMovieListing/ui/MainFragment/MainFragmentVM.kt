package com.example.simpleMovieListing.ui.MainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingSource
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    fun getMovies() = flow {
        apiRepository.getMovies().collect {
            emit(it)
        }
    }

    fun pagingListData() = Pager(PagingConfig(pageSize = 1, prefetchDistance = 5)) {
        PagingSource(this)
    }.flow.cachedIn(viewModelScope)
}