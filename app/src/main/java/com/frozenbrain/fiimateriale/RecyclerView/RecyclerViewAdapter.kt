package com.frozenbrain.fiimateriale.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.semester.ClassItem

class RecyclerViewAdapter(private val list: List<ClassItem>,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return RecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item: ClassItem = list[position]
        holder.bind(item, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
