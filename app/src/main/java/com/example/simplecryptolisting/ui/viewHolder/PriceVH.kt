package com.example.simplecryptolisting.ui.viewHolder

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecryptolisting.model.PriceModel
import com.example.simplecryptolisting.R
import com.example.simplecryptolisting.adapter.PagingAdapter

class PriceVH(vm: ViewModel, itemView: View) : RecyclerView.ViewHolder(itemView) {

    val symbol:TextView = itemView.findViewById<TextView>(R.id.symbol)

    var callback: PagingAdapter.PagingAdapterCallback? = null

    fun onBind(item: PriceModel){
        symbol.text = item.symbol
        symbol.setOnClickListener {
            if (it is TextView){
                callback?.onSymbolClick(it.text.toString())
            }
        }
    }
}