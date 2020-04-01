package com.frozenbrain.fiimateriale.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.semester.ClassItem

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.class_item, parent, false)) {
    private var className: TextView? = null
    private var classCredits: TextView? = null
    private var sectionTitle: TextView? = null

    init {
        className = itemView.findViewById(R.id.classTitle)
        classCredits = itemView.findViewById(R.id.classCredits)
        sectionTitle = itemView.findViewById(R.id.sectionTitle)
    }

    fun bind(item: ClassItem, clickListenerOn: OnItemClickListener) {
        if (item.credits != -1) {
            className?.text = if (item.name.length > 25) item.short else item.name
            classCredits?.text = "Credits: ${item.credits}"
        } else {
            itemView.findViewById<CardView>(R.id.itemCardContainer).visibility = View.GONE
            sectionTitle?.visibility = View.VISIBLE
            sectionTitle?.text = item.name
        }

        itemView.findViewById<CardView>(R.id.itemCardContainer).setOnClickListener {
            clickListenerOn.onItemClicked(item)
        }

    }

}