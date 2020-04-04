package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recycler_view.items.TitleItem

class TitleViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private var sectionTextView: TextView? = null

    init {
        sectionTextView = itemView.findViewById(R.id.sectionTitle)
    }

    fun bind(item: TitleItem) {
        sectionTextView?.text = item.title
    }
}