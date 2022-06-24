package com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.databinding.MovieViewholderListBinding
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter
import kotlin.math.roundToInt

class MovieListVH(itemView: View, private val binding: MovieViewholderListBinding) : RecyclerView.ViewHolder(itemView) {

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: Movie){
        itemView.setOnClickListener {
            callback?.onMovieClick(item)
        }
//        binding.name.text = item.
        binding.rating.text = ((item.rating*100f).roundToInt()/100f).toString()
        binding.ratingBar.rating = item.rating
        binding.avatar.setImageURI(item.poster)
    }
}