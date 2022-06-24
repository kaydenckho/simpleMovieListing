package com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.databinding.MovieViewholderBinding
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter

class MovieVH(itemView: View, private val binding: MovieViewholderBinding) : RecyclerView.ViewHolder(itemView) {

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: Movie){
        binding.name.text = item.name
        binding.name.setOnClickListener {
            callback?.onMovieClick(item.uuid)
        }
    }
}