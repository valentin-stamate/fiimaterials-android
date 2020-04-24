package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recycler_view.OnItemClickListener
import com.frozenbrain.fiimateriale.recycler_view.items.CourseItem
import com.frozenbrain.fiimateriale.recycler_view.items.Data

class ClassViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var className: TextView? = null
    private var classCredits: TextView? = null
    private var sectionTitle: TextView? = null

    private val TYPE_PARENT: Int = 0
    private val TYPE_SITE_PAGE: Int = 1
    private val TYPE_MEGA = 2

    init {
        className = itemView.findViewById(R.id.classTitle)
        classCredits = itemView.findViewById(R.id.classCredits)
        sectionTitle = itemView.findViewById(R.id.sectionTitle)
    }

    fun bind(item: CourseItem, clickListenerOn: OnItemClickListener) {
        className?.text = if (item.name.length > 30) item.short else item.name
        val text = "Credits: ${item.credits}"
        classCredits?.text = text


        if (item.megaLink.isNotEmpty()) {
            itemView.findViewById<ImageView>(R.id.image_button_mega).setOnClickListener {
                clickListenerOn.onItemClicked(item as Data, TYPE_MEGA)
            }
        } else {
            itemView.findViewById<ImageView>(R.id.image_button_mega).visibility = View.GONE
        }

        if (item.sitePage.isNotEmpty()) {
            itemView.findViewById<ImageView>(R.id.image_button_site_page).setOnClickListener {
                clickListenerOn.onItemClicked(item as Data, TYPE_SITE_PAGE)
            }
        } else {
            itemView.findViewById<ImageView>(R.id.image_button_site_page).visibility = View.GONE
        }

    }

}