package com.example.simpleMovieListing.ui.MainFragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.simpleMovieListing.databinding.MovieViewholderBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder.MovieVH

class PagingAdapter(private val vm: ViewModel): PagingDataAdapter<Movie, MovieVH>(PROFILE_COMPARATOR) {
    companion object{
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

    interface PagingAdapterCallback{
        fun onMovieClick(uuid: String)
    }

    var callback: PagingAdapterCallback? = null

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.onBind(it)
            holder.callback = callback
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val binding = MovieViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieVH(binding.root, binding)
    }

}