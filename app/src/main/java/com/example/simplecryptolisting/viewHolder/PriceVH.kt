package com.example.simplecryptolisting.viewHolder

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecryptolisting.model.PriceModel
import com.example.simplecryptolisting.R

class PriceVH(vm: ViewModel, itemView: View) : RecyclerView.ViewHolder(itemView) {

    val symbol:TextView = itemView.findViewById<TextView>(R.id.symbol)

    fun onBind(item: PriceModel){
        symbol.text = item.symbol
    }
}