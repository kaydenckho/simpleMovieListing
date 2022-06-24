package com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.databinding.MovieViewholderGridBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter
import kotlin.math.roundToInt

class MovieGridVH(itemView: View, private val binding: MovieViewholderGridBinding) :
    RecyclerView.ViewHolder(itemView) {

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: Movie) {
        binding.run {
            avatar.setImageURI(item.poster)
            name.text = item.name
            rating.text = ((item.rating*100).roundToInt()/100).toString()
            like.text = item.likeCount.toString()
            comment.text = item.reviewCount.toString()
        }
        itemView.setOnClickListener {
            callback?.onMovieClick(item)
        }
    }
}