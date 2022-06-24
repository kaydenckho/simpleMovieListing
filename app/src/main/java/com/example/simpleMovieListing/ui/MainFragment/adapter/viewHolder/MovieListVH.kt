package com.example.simpleMovieListing.ui.MainFragment.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleMovieListing.databinding.MovieViewholderListBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MovieListVH(itemView: View, private val binding: MovieViewholderListBinding) : RecyclerView.ViewHolder(itemView) {

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: Movie){
        itemView.setOnClickListener {
            callback?.onMovieClick(item)
        }
        binding.run{
            name.text = item.name
            rating.text = ((item.rating*10f).roundToInt()/10f).toString()
            ratingBar.rating = item.rating
            avatar.setImageURI(item.poster)
            like.text = item.likeCount.toString()
            comment.text = item.reviewCount.toString()
            openDate.text = getDate(item.openDate)
        }
    }

    fun getDate(milliSeconds: Long) : String{
        val formatter = SimpleDateFormat("yyyy年M月d日")
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}