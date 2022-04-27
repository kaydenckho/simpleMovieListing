package com.example.simplecryptolisting.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.simplecryptolisting.viewHolder.NetworkStateItemVH

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
