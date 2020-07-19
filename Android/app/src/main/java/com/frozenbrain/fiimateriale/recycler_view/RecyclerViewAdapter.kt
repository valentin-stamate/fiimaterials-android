package com.frozenbrain.fiimateriale.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.data.*
import com.frozenbrain.fiimateriale.recycler_view.view_holders.*

class RecyclerViewAdapter(private val list: List<Data>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            Data.TYPE_CLASS       -> ClassViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false))
            Data.TYPE_USEFUL_LINK -> UsefulLinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_useful_link, parent, false))
            Data.TYPE_TITLE       -> TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false))
            Data.TYPE_HOF_PERSON  -> HofPersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hof_person, parent, false))
            Data.TYPE_FEEDBACK    -> FeedbackListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feedback, parent, false))
            else                  -> FreeRoomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_free_room, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: Data = list[position]

        when(item.getType()) {
            Data.TYPE_CLASS       -> (holder as ClassViewHolder).bind(item as CourseItem, onItemClickListener)
            Data.TYPE_USEFUL_LINK -> (holder as UsefulLinkViewHolder).bind(item as UsefulLinkItem, onItemClickListener)
            Data.TYPE_TITLE       -> (holder as TitleViewHolder).bind(item as TitleItem)
            Data.TYPE_HOF_PERSON  -> (holder as HofPersonViewHolder).bind(item as HofPerson, onItemClickListener)
            Data.TYPE_FEEDBACK    -> (holder as FeedbackListViewHolder).bind(item as Feedback)
            Data.TYPE_FREE_DAY    -> (holder as FreeRoomViewHolder).bind(item as FreeRoom)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getType()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
