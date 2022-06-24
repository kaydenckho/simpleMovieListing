package com.example.simpleMovieListing.ui.MainFragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.databinding.MovieViewholderGridBinding
import com.example.simpleMovieListing.databinding.MovieViewholderListBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder.MovieGridVH
import com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder.MovieListVH

class PagingAdapter(private val vm: ViewModel) :
    PagingDataAdapter<Movie, RecyclerView.ViewHolder>(PROFILE_COMPARATOR) {
    companion object {
        val PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.uuid == newItem.uuid

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
                return null
            }
        }
    }

    private val layoutManager: GridLayoutManager? = null

    enum class ViewType {
        LIST,
        GRID
    }

    interface PagingAdapterCallback {
        fun onMovieClick(uuid: String)
    }

    var callback: PagingAdapterCallback? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            if (holder is MovieGridVH) {
                holder.onBind(it)
                holder.callback = callback
            }
            else if (holder is MovieListVH){
                holder.onBind(it)
                holder.callback = callback
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.GRID.ordinal -> {
                val binding = MovieViewholderGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MovieGridVH(binding.root, binding)
            }
            else -> {
                val binding = MovieViewholderListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MovieListVH(binding.root, binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }

}