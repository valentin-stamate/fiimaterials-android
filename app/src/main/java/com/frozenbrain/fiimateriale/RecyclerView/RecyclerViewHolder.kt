package com.frozenbrain.fiimateriale.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_class_item, parent, false)) {
    private var textView: TextView? = null

    init {
        textView = itemView.findViewById(R.id.textViewItem)
    }

    fun bind(item: RecyclerViewItem) {
        textView?.text = item.text
    }

}