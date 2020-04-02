package com.frozenbrain.fiimateriale.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frozenbrain.fiimateriale.R
import com.frozenbrain.fiimateriale.recycler_view.view_holders.ClassViewHolder
import com.frozenbrain.fiimateriale.recycler_view.items.Data
import com.frozenbrain.fiimateriale.recycler_view.items.ClassItem
import com.frozenbrain.fiimateriale.recycler_view.items.UsefulLinkItem
import com.frozenbrain.fiimateriale.recycler_view.view_holders.UsefulLinkHolder

class RecyclerViewAdapter(private val list: List<Data>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            Data.TYPE_CLASS -> ClassViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.class_item, parent, false)
            )
            else -> UsefulLinkHolder(LayoutInflater.from(parent.context).inflate(R.layout.useful_link_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: Data = list[position]
        if(item.getType() == Data.TYPE_CLASS) {
            (holder as ClassViewHolder).bind(item as ClassItem, onItemClickListener)
        } else {
            (holder as UsefulLinkHolder).bind(item as UsefulLinkItem, onItemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getType()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
