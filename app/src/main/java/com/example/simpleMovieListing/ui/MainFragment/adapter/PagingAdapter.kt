package com.example.simpleMovieListing.ui.MainFragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.simpleMovieListing.model.PriceModel
import com.example.simpleMovieListing.ui.MainFragment.viewHolder.PriceVH
import com.example.simpleMovieListing.R

class PagingAdapter(val vm: ViewModel): PagingDataAdapter<PriceModel, PriceVH>(PROFILE_COMPARATOR) {
    companion object{
        val PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<PriceModel>() {
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PriceModel, newItem: PriceModel): Boolean =
                oldItem.symbol == newItem.symbol

            override fun areItemsTheSame(oldItem: PriceModel, newItem: PriceModel): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: PriceModel, newItem: PriceModel): Any? {
                return null
            }
        }
    }

    interface PagingAdapterCallback{
        fun onSymbolClick(symbol: String)
    }

    var callback: PagingAdapterCallback? = null

    override fun onBindViewHolder(holder: PriceVH, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.onBind(it)
            holder.callback = callback
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceVH {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return PriceVH(vm, view)
    }

}