package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.items.ClassItem
import com.frozenbrain.fiimateriale.recycler_view.items.Data

class ClassViewHolder(view: View): RecyclerView.ViewHolder(view) {
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
            val text = "Credits: ${item.credits}"
            classCredits?.text = text
        } else {
            itemView.findViewById<CardView>(R.id.itemCardContainer).visibility = View.GONE
            sectionTitle?.visibility = View.VISIBLE
            sectionTitle?.text = item.name
        }

        itemView.findViewById<CardView>(R.id.itemCardContainer).setOnClickListener {
            clickListenerOn.onItemClicked(item as Data)
            // TODO think of another workflow
        }

    }

}