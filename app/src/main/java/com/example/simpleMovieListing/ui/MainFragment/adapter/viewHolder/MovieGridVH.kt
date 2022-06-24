package com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.databinding.MovieViewholderGridBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.databinding.MovieViewholderListBinding
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter

class MovieGridVH(itemView: View, private val binding: MovieViewholderGridBinding) : RecyclerView.ViewHolder(itemView) {

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: Movie){
        binding.avatar.setImageURI(item.poster)
        binding.name.text = item.name
        itemView.setOnClickListener {
            callback?.onMovieClick(item)
        }
    }
}