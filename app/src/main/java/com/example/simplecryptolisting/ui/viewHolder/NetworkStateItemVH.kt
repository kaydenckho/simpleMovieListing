package com.example.simplecryptolisting.ui.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecryptolisting.R

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemVH(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {

    fun bindTo(loadState: LoadState) {
        itemView.findViewById<ProgressBar>(R.id.progress_bar).isVisible = loadState is LoadState.Loading
//        retry.isVisible = loadState is LoadState.Error
        itemView.findViewById<TextView>(R.id.error_msg).isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
        itemView.findViewById<TextView>(R.id.error_msg).text = (loadState as? LoadState.Error)?.error?.message
    }
}