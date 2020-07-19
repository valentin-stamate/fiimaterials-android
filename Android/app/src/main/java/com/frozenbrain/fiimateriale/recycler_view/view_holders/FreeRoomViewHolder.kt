package com.frozenbrain.fiimateriale.recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.data.FreeRoom

class FreeRoomViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val classTitleTextView = view.findViewById<TextView>(R.id.classTitle)
    private val classDayTextView = view.findViewById<TextView>(R.id.classDay)
    private val classHourTextView = view.findViewById<TextView>(R.id.classHour)

    fun bind(item: FreeRoom) {
        classTitleTextView.text = item.className
        classDayTextView.text = item.freeday
        classHourTextView.text = item.freeHour
    }
}