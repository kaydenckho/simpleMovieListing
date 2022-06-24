package com.example.simpleMovieListing.ui.MainFragment.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.R
import com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder.NetworkStateItemVH

class PagingLoadStateAdapter (
    private val adapter: PagingAdapter
) : LoadStateAdapter<NetworkStateItemVH>() {
    override fun onBindViewHolder(holder: NetworkStateItemVH, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemVH {
        return NetworkStateItemVH(parent) { adapter.retry() }
    }
}
