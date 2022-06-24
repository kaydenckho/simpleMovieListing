package com.example.simpleMovieListing.ui.MainFragment.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simpleMovieListing.ui.MainFragment.MainFragmentVM
import com.example.simpleMovieListing.model.Movie

class PagingSource(
    var vm: MainFragmentVM,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentLoadingPageKey = params.key ?: 0
            val prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey - 1
            var nextKey: Int? = null
            var data = emptyList<Movie>()
            vm.getMovies().collect {
                if (it.isNotEmpty()) {
                    data = it
                    nextKey = currentLoadingPageKey.plus(1)
                }
            }
            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.fillInStackTrace()
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
