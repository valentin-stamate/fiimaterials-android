package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.data.OnItemClickListener
import com.frozenbrain.fiimateriale.data.Data
import com.frozenbrain.fiimateriale.data.UsefulLinkItem

class UsefulLinkViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private var title: TextView? = null

    init {
        title = view.findViewById(R.id.usefulLinkTitle)
    }

    fun bind(item: UsefulLinkItem, clickListenerOn: OnItemClickListener) {
        title?.text = item.title

        view.findViewById<CardView>(R.id.usefulLinkContainer).setOnClickListener {
            clickListenerOn.onItemClicked( item as Data, -1)
        }
    }
}